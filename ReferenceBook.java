import java.util.Date;

public class ReferenceBook extends LibraryItem {
    public ReferenceBook(String title, double value) {
        super(title, value);
    }

    @Override
    public Date getDueDate() {
        return null; // Reference books cannot be checked out
    }
}
