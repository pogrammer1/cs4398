import java.util.Calendar;
import java.util.Date;

public class AudioVideoMaterial extends LibraryItem {
    public AudioVideoMaterial(String title, double value) {
        super(title, value);
    }

    @Override
    public Date getDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCheckoutDate());
        calendar.add(Calendar.DAY_OF_YEAR, 14);
        return calendar.getTime();
    }
}
