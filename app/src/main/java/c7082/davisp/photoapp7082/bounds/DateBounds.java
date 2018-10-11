package c7082.davisp.photoapp7082.bounds;

import java.io.Serializable;
import java.util.Calendar;

public class DateBounds implements Serializable {

    /**
     * Min range
     */
    private Calendar min;

    /**
     * Max range
     */
    private Calendar max;

    /**
     * Constructor
     * @param min
     * @param max
     */
    public DateBounds(Calendar min, Calendar max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Checks if the given date is within the bounds
     * @param cal
     * @return
     */
    public boolean Contains(Calendar cal) {
        return cal.after(min) && cal.before(max);
    }

    @Override
    public String toString() {
        return min.toString() + " TO " + max.toString();
    }
}
