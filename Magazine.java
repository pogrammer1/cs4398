import java.util.Date;

public class Magazine extends LibraryItem {
    public Magazine(String title, double value) {
        super(title, value);
    }

    @Override
    public Date getDueDate() {
        return null; // Magazines cannot be checked out
    }
}
