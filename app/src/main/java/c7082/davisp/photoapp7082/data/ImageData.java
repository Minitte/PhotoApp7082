package c7082.davisp.photoapp7082.data;

import android.net.Uri;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ImageData implements Serializable {

    public static final String DATE_FORMAT = "MM-dd-yyyy HH:mm:ss";

    /**
     * Path/Uri of the image
     */
    private String imagePath = "";

    /**
     * The date of when the image was taken
     */
    private String dateTaken = "";

    /**
     * The caption of the image
     */
    private String caption = "";

    /**
     * Location of where the image was taken
     */
    private LocationData Location;

    /**
     * Constructor on an ImageData
     * @param imagePath
     */
    public ImageData(String imagePath)
    {
        this.imagePath = imagePath;
    }

    /**
     * Constructor on an ImageData
     * @param imageUri
     */
    public ImageData(Uri imageUri)
    {
        this.imagePath = imageUri.toString();
    }

    /**
     * Gets the value of imagePath
     *
     * @return a java.lang.String
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Gets the value of imagePath
     *
     * @return a android.net.Uri
     */
    public Uri getImagePathAsUri() {
        return Uri.parse(imagePath);
    }

    /**
     * Sets the imagePath
     *
     * @param imagePath set imagePath to this value
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets the value of dateTaken
     *
     * @return a java.lang.String
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     * Gets the value of dateTaken
     *
     * @return a java.util.date
     */
    public Date getDateTakenAsDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        try {
            Date d = sdf.parse(dateTaken);
            return d;
        } catch (ParseException pe) {
            Log.e("PE", pe.getMessage());
        }

        return null;
    }

    /**
     * Gets the value of dateTaken
     * @return
     */
    public GregorianCalendar getDateTakenAsGC() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        try {
            Date d = sdf.parse(dateTaken);
            GregorianCalendar gc = new GregorianCalendar();

            gc.setTime(d);

            return gc;
        } catch (ParseException pe) {
            Log.e("PE", pe.getMessage());
        }

        return null;
    }

    /**
     * Sets the dateTaken
     *
     * @param dateTaken set dateTaken to this value
     */
    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    /**
     * Sets the dateTaken
     *
     * @param dateTaken set dateTaken to this value
     */
    public void setDateTaken(SimpleDateFormat dateTaken) {
        this.dateTaken = dateTaken.toString();
    }

    /**
     * Gets the value of caption
     *
     * @return a java.lang.String
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the caption
     *
     * @param caption set caption to this value
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Gets the value of Location
     *
     * @return a c7082.davisp.photoapp7082.data.LocationData
     */
    public LocationData getLocation() {
        return Location;
    }

    /**
     * Sets the Location
     *
     * @param location set Location to this value
     */
    public void setLocation(LocationData location) {
        Location = location;
    }
}
