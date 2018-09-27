package c7082.davisp.photoapp7082.data;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageDatabase {

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
        }
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
            return first.getLocation().compareTo(second.getLocation());
        }
    }
}
