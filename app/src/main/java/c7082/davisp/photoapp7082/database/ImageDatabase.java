package c7082.davisp.photoapp7082.database;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import c7082.davisp.photoapp7082.data.ImageData;

public class ImageDatabase implements Serializable {

    public static final String VERSION = "1";

    private String version = VERSION;

    /**
     * A hash map containing ImageData
     * Key: Uri as string
     * Value: ImageData object
     */
    private Map<String, ImageData> database;

    /**
     * A list of Uris sorted by caption
     */
    private List<String> listSortedByCaption;

    /**
     * A list sorted by Dates
     */
    private List<String> listSortedByDate;

    /**
     * A list of sorted locations
     */
    private List<String> listSortedByLocation;

    /**
     * Number of entries
     */
    private int numEntries;

    /**
     * ImageDatabase constructor
     */
    public ImageDatabase() {
        database = new HashMap<>();

        listSortedByCaption = new ArrayList<>();
        listSortedByDate = new ArrayList<>();
        listSortedByLocation = new ArrayList<>();
    }

    /**
     * Adds data to the sorted list
     */
    private void initalizeSortedList()
    {
        Collection<ImageData> imgs = database.values();

        for (ImageData i : imgs) {
            addToSortedList(i);
        }

        sortAllList();
    }

    /**
     * Sorts all sorted List
     */
    private void sortAllList() {
        Collections.sort(listSortedByCaption, new CaptionComparator());

        Collections.sort(listSortedByDate, new DateComparator());

        Collections.sort(listSortedByLocation, new LocationComparator());
    }

    /**
     * Adds a URI entry to all sorted list
     * @param i
     */
    private void addToSortedList(ImageData i) {
        String uri = i.getImagePath();
        listSortedByCaption.add(uri);
        listSortedByDate.add(uri);
        listSortedByLocation.add(uri);
    }

    /**
     * Removes URI entry from sorted lists
     * @param i
     */
    private void removeFromSortedList(ImageData i) {
        String uri = i.getImagePath();
        listSortedByCaption.remove(uri);
        listSortedByDate.remove(uri);
        listSortedByLocation.remove(uri);
    }

    /**
     * Adds the ImageData to database. If one already exist with the same Uri, it will be replaced
     * @param img
     */
    public void register(ImageData img) {
        if (img != null) {
            database.put(img.getImagePath(), img);
            addToSortedList(img);
            sortAllList();
            numEntries++;
        }
    }

    /**
     * Deletes the entry for the ImageData
     * @param img
     */
    public void delete(ImageData img) {
        if (img != null) {
            database.remove(img.getLocation());
            removeFromSortedList(img);
            numEntries--;
        }
    }

    /**
     * Retrieves the ImageData corresponding to the imgUri
     * @param imgUri
     * @return null if not in database
     */
    public ImageData get(String imgUri) {
        return database.get(imgUri);
    }

    /**
     * Retrieves the ImageData corresponding to the imgUri
     * @param imgUri
     * @return null if not in database
     */
    public ImageData get(Uri imgUri) {
        return database.get(imgUri.toString());
    }

    /**
     * Deletes the entry for the ImageData
     * @param imgUri
     */
    public void delete(Uri imgUri) {
        if (imgUri != null) {
            database.remove(imgUri.toString());
        }
    }

    /**
     * Checks if the database contains this image
     * @param img
     * @return true if database contains an entry for it
     */
    public boolean contains(ImageData img) {
        return database.containsKey(img.getImagePath());
    }

    /**
     * Checks if the database contains this image
     * @param uri
     * @return true if database contains an entry for it
     */
    public boolean contains(Uri uri) {
        return database.containsKey(uri.toString());
    }

    /**
     * Gets the value of listSortedByCaption
     *
     * @return a java.util.List<java.lang.String>
     */
    public List<String> getListSortedByCaption() {
        return listSortedByCaption;
    }

    /**
     * Gets the value of listSortedByDate
     *
     * @return a java.util.List<java.lang.String>
     */
    public List<String> getListSortedByDate() {
        return listSortedByDate;
    }

    /**
     * Gets the value of listSortedByLocation
     *
     * @return a java.util.List<java.lang.String>
     */
    public List<String> getListSortedByLocation() {
        return listSortedByLocation;
    }

    /**
     * Gets the value of version
     *
     * @return a java.lang.String
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets the value of numEntries
     *
     * @return a int
     */
    public int getNumEntries() {
        return numEntries;
    }

    /* =================
           Comparators
        ================= */

    /**
     * Given the uri as string, get data from database and compare Captions
     */
    private class CaptionComparator implements Comparator<String> {
        @Override
        public int compare(String firstUri, String secondUri) {
            ImageData first = database.get(firstUri);
            ImageData second = database.get(secondUri);
            return first.getCaption().compareTo(second.getCaption());
        }
    }

    /**
     * Given the uri as string, get data from database and compare date
     */
    private class DateComparator implements Comparator<String> {
        @Override
        public int compare(String firstUri, String secondUri) {
            ImageData first = database.get(firstUri);
            ImageData second = database.get(secondUri);
            return first.getDateTakenAsDate().compareTo(second.getDateTakenAsDate());
        }
    }

    /**
     * Given the uri as string, get data from database and compare location
     */
    private class LocationComparator implements Comparator<String> {
        @Override
        public int compare(String firstUri, String secondUri) {
            ImageData first = database.get(firstUri);
            ImageData second = database.get(secondUri);
            return (int) (first.getLocation().getLatitude() - second.getLocation().getLatitude());
        }
    }
}
