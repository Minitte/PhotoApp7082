package c7082.davisp.photoapp7082.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import c7082.davisp.photoapp7082.data.ImageData;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    /**
     * Date picker listeners
     */
    public interface DatePickerListener {
        public void onSetDate(int year, int month, int day);
    }

    public DatePickerListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (listener != null) {
            listener.onSetDate(year, month, day);
        }
    }
}