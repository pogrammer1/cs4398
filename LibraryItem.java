import java.util.Date;

public abstract class LibraryItem {
    private String title;
    private double value;
    private Date checkoutDate;
    private boolean renewed;

    public LibraryItem(String title, double value) {
        this.title = title;
        this.value = value;
        this.renewed = false;
    }

    public String getTitle() {
        return title;
    }

    public double getValue() {
        return value;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public boolean isRenewed() {
        return renewed;
    }

    public void setRenewed(boolean renewed) {
        this.renewed = renewed;
    }

    public abstract Date getDueDate();
}
