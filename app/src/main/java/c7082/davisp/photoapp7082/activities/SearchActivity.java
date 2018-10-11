package c7082.davisp.photoapp7082.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import c7082.davisp.photoapp7082.R;
import c7082.davisp.photoapp7082.bounds.DateBounds;
import c7082.davisp.photoapp7082.data.ImageData;
import c7082.davisp.photoapp7082.bounds.LocationBounds;
import c7082.davisp.photoapp7082.dialog.DatePickerFragment;

public class SearchActivity extends AppCompatActivity implements DatePickerFragment.DatePickerListener{

    private boolean pickingTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
    }

    /**
     * Begins the search
     * @param view
     */
    public void submitSearch(View view) {
        Intent intent = new Intent(this, SearchResultActivity.class);

        SearchResultActivity.captionSearch = getCaptionSearch();

        SearchResultActivity.dateBounds = getDateSearch();

        SearchResultActivity.locationBounds = getLocationSearch();

        if (SearchResultActivity.captionSearch == null &&
                SearchResultActivity.dateBounds == null &&
                SearchResultActivity.locationBounds == null) {
            return;
        }

        startActivity(intent);
    }

    /**
     * onClick function for setting to date
     * @param v
     */
    public void setDateTo(View v) {
        pickingTo = true;

        showDatePickerDialog();
    }

    /**
     * onClick function for setting from date
     * @param v
     */
    public void setDateFrom(View v) {
        pickingTo = false;

        showDatePickerDialog();
    }

    /**
     * Shows the date dialog picker
     */
    public void showDatePickerDialog() {
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.listener = this;
        datepicker.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Retrieves the caption search values.
     * @return Returns the serach value for captions. If empty string, returns null
     */
    private String getCaptionSearch() {
        TextView captionText = findViewById(R.id.captionInput);
        String captionSearch = captionText.getText().toString();

        if (captionSearch.equals("")) {
            return null;
        }

        return captionSearch;
    }

    /**
     * Retrieves the date search values.
     * @return Returns the serach value for date. If empty string, returns null
     */
    private DateBounds getDateSearch() {
        Button dateTobtn = findViewById(R.id.dateToBtn);
        Button dateFrombtn = findViewById(R.id.dateFromBtn);
        String dateTo = dateTobtn.getText().toString();
        String dateFrom = dateFrombtn.getText().toString();

        if (dateTo.equals("") || dateFrom.equals("")) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(ImageData.DATE_FORMAT);

        try {
            GregorianCalendar gcMin = new GregorianCalendar();
            gcMin.setTime(sdf.parse(dateTo));

            GregorianCalendar gcMax = new GregorianCalendar();
            gcMax.setTime(sdf.parse(dateFrom));

            return new DateBounds(gcMin, gcMax);
        } catch (ParseException pe) {
            Log.e("PE", pe.getMessage());
        }

        return null;
    }

    /**
     * Retrieves the location search values.
     * @return Returns the serach value for location. If empty string, returns null
     */
    private LocationBounds getLocationSearch() {
        TextView minLat = findViewById(R.id.minLat);
        TextView minLong = findViewById(R.id.minLong);
        TextView maxLat = findViewById(R.id.maxLat);
        TextView maxLong = findViewById(R.id.maxLong);

        try {
            double minLatParsed = Double.parseDouble(minLat.getText().toString());
            double minLongParsed = Double.parseDouble(minLong.getText().toString());
            double maxLatParsed = Double.parseDouble(maxLat.getText().toString());
            double maxLongParsed = Double.parseDouble(maxLong.getText().toString());

            if (minLatParsed > maxLatParsed) {
                double tmp = maxLatParsed;
                maxLatParsed = minLatParsed;
                minLatParsed = tmp;
            }

            if (minLongParsed > maxLongParsed) {
                double tmp = maxLongParsed;
                maxLongParsed = minLongParsed;
                minLongParsed = tmp;
            }

            LocationBounds bounds = new LocationBounds(minLatParsed, maxLatParsed, minLongParsed, maxLongParsed);
            return bounds;
        } catch(NumberFormatException nfe) {

        }

        return null;
    }

    @Override
    public void onSetDate(int year, int month, int day) {
        GregorianCalendar gc = new GregorianCalendar();

        gc.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat(ImageData.DATE_FORMAT);

        String date = sdf.format(gc.getTime());

        Button dateToBtn = findViewById(R.id.dateToBtn);
        Button dateFromBtn = findViewById(R.id.dateFromBtn);
        if (pickingTo) {
            dateToBtn.setText(date);

            if (dateFromBtn.getText().toString().equals("")) {
                dateFromBtn.setText(date);
            }
        } else {
            dateFromBtn.setText(date);

            if (dateToBtn.getText().toString().equals("")) {
                dateToBtn.setText(date);
            }
        }
    }
}
