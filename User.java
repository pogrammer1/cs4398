import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private String address;
    private String phone;
    private String cardNumber;
    private int age;
    private ArrayList<LibraryItem> checkedOutItems;
    private ArrayList<LibraryItem> requestedItems;
    private double outstandingFines;
    //
    private List<Book> checkedOutBooks;

    public User(String name, String address, String phone, String cardNumber, int age) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cardNumber = cardNumber;
        this.age = age;
        this.checkedOutItems = new ArrayList<>();
        this.requestedItems = new ArrayList<>();
        this.outstandingFines = 0.0;
        //
        this.checkedOutBooks = new ArrayList<>();
    }
    
    //
    public void checkOutBook(Book book) {
        checkedOutBooks.add(book);
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }
    //

    public String getName() {
        return name;
    }

    public void checkoutItem(Book book) {
        checkedOutBooks.add(book);
    }
    
    // prob changes to this function later when get checkoutbook working
    // public void checkoutItem(LibraryItem item) {
    //     if (age <= 12 && checkedOutItems.size() >= 5) {
    //         System.out.println("Children can only check out up to 5 items.");
    //         return;
    //     }
    //     checkedOutItems.add(item);
    //     item.setCheckoutDate(new Date());
    // }

    public void renewItem(LibraryItem item) {
        if (item.isRenewed()) {
            System.out.println("Item cannot be renewed more than once.");
        } else if (requestedItems.contains(item)) {
            System.out.println("Item cannot be renewed due to outstanding request.");
        } else {
            item.setRenewed(true);
            item.setCheckoutDate(new Date());
        }
    }

    public void requestItem(LibraryItem item) {
        requestedItems.add(item);
    }

    public void calculateOverdueFines() {
        Date currentDate = new Date();
        for (LibraryItem item : checkedOutItems) {
            if (item.getDueDate().before(currentDate)) {
                long diff = currentDate.getTime() - item.getDueDate().getTime();
                long overdueDays = diff / (1000 * 60 * 60 * 24);
                double fine = Math.min(overdueDays * 0.1, item.getValue());
                outstandingFines += fine;
            }
        }
    }

    public double getOutstandingFines() {
        return outstandingFines;
    }

    public ArrayList<LibraryItem> getCheckedOutItems() {
        return checkedOutItems;
    }
}
