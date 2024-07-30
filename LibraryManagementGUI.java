import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List; // Add this import statement

public class LibraryManagementGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField cardNumberField;
    private JTextField ageField;
    private JTextArea displayArea;
    private ArrayList<User> users = new ArrayList<>();
    

    public LibraryManagementGUI() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new SpringLayout());
        JLabel nameLabel = new JLabel("Name:", JLabel.TRAILING);
        nameField = new JTextField(10);

        JLabel addressLabel = new JLabel("Address:", JLabel.TRAILING);
        addressField = new JTextField(10);

        JLabel phoneLabel = new JLabel("Phone:", JLabel.TRAILING);
        phoneField = new JTextField(10);

        JLabel cardNumberLabel = new JLabel("Library Card Number:", JLabel.TRAILING);
        cardNumberField = new JTextField(10);

        JLabel ageLabel = new JLabel("Age:", JLabel.TRAILING);
        ageField = new JTextField(10);

        JButton addButton = new JButton("Add User");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(cardNumberLabel);
        inputPanel.add(cardNumberField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(addButton);


        //
        List<Book> books = new ArrayList<>(); // Use the generic List type
        books.add(new Book("The Great Gatsby", 1, false));
        books.add(new Book("1984", 2, true));
        books.add(new Book("Inception", 3, false));
        books.add(new Book("The Beatles: Abbey Road", 4, false));

        JComboBox<Book> bookComboBox = new JComboBox<>();
        for (Book book : books) {
            bookComboBox.addItem(book);
        }
        inputPanel.add(bookComboBox);

        SpringUtilities.makeCompactGrid(inputPanel, 6, 2, 6, 6, 6, 6);

        frame.add(inputPanel, BorderLayout.NORTH);
        displayArea = new JTextArea();
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String cardNumber = cardNumberField.getText();
                int age = Integer.parseInt(ageField.getText());

                User newUser = new User(name, address, phone, cardNumber, age);
                users.add(newUser);

                displayArea.append("Added User: " + name + "\n");
                nameField.setText("");
                addressField.setText("");
                phoneField.setText("");
                cardNumberField.setText("");
                ageField.setText("");
            }
        });

        JButton checkoutButton = new JButton("Checkout Item");
        JButton renewButton = new JButton("Renew Item");
        JButton calculateFinesButton = new JButton("Calculate Fines");
        JButton requestButton = new JButton("Request Item");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(checkoutButton);
        buttonPanel.add(renewButton);
        buttonPanel.add(calculateFinesButton);
        buttonPanel.add(requestButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!users.isEmpty()) {
                    User user = users.get(0); // For simplicity, take the first user
                    Book selectedBook = (Book) bookComboBox.getSelectedItem();
                    AudioVideoMaterial audioVideo = new AudioVideoMaterial("Sample Audio", 15.0);
                    user.checkoutItem(selectedBook);
                    //user.checkoutItem(audioVideo);
                    displayArea.append("Checked out items for user " + user.getName() + ":\n" + user.getCheckedOutBooks() + "\n" );
                } else {
                    displayArea.append("No users available to check out books.\n");
                }
            }
        });

        renewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!users.isEmpty()) {
                    User user = users.get(0); // For simplicity, take the first user
                    if (!user.getCheckedOutItems().isEmpty()) {
                        user.renewItem(user.getCheckedOutItems().get(0));
                        displayArea.append("Renewed item for user " + user.getName() + "\n");
                    }
                }
            }
        });

        calculateFinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!users.isEmpty()) {
                    User user = users.get(0); // For simplicity, take the first user
                    user.calculateOverdueFines();
                    displayArea.append("Outstanding fines for user " + user.getName() + ": $" + user.getOutstandingFines() + "\n");
                }
            }
        });

        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!users.isEmpty()) {
                    User user = users.get(0); // For simplicity, take the first user
                    Book requestedBook = new Book("Requested Book", 25.0, false);
                    AudioVideoMaterial requestedAudioVideo = new AudioVideoMaterial("Requested Audio", 20.0);
                    user.requestItem(requestedBook);
                    user.requestItem(requestedAudioVideo);
                    displayArea.append("Requested items for user " + user.getName() + "\n");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementGUI());
    }
}
