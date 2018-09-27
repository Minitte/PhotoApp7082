package c7082.davisp.photoapp7082.data;

import android.net.Uri;

import java.util.Date;

public class ImageData {

    /**
     * Path/Uri of the image
     */
    private String imagePath;

    /**
     * The date of when the image was taken
     */
    private String dateTaken;

    /**
     * The caption of the image
     */
    private String caption;

    /**
     * Location of where the image was taken
     */
    private String Location;

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
    public void setDateTaken(Date dateTaken) {
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
     * @return a java.lang.String
     */
    public String getLocation() {
        return Location;
    }

    /**
     * Sets the Location
     *
     * @param location set Location to this value
     */
    public void setLocation(String location) {
        Location = location;
    }
}
