import java.util.Calendar;
import java.util.Date;

public class Book extends LibraryItem {
    private boolean isBestSeller;

    public Book(String title, double value, boolean isBestSeller) {
        super(title, value);
        this.isBestSeller = isBestSeller;
    }

    @Override
    public Date getDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCheckoutDate());
        if (isBestSeller) {
            calendar.add(Calendar.DAY_OF_YEAR, 14);
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, 21);
        }
        return calendar.getTime();
    }

    // Override toString method
    @Override
    public String toString() {
        return getTitle();
    }
}
