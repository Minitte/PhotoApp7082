package c7082.davisp.photoapp7082.database;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ImageDatabaseSaver {

    /**
     * Path to file
     */
    private String path;

    /**
     * Constructor for a ImageDatabaseSaver
     * @param path to the file
     */
    public ImageDatabaseSaver(String path) {
        this.path = path;
    }

    /**
     * Write database to path
     * @param db
     */
    public void writeDatabase(ImageDatabase db) {
        try {
            // open stream
            FileOutputStream fStream = new FileOutputStream(path);

            ObjectOutputStream objStream = new ObjectOutputStream(fStream);

            // write
            objStream.writeObject(db);

            // close stream
            objStream.close();

            fStream.close();

        } catch (IOException ioE) {
            Log.e("ImgDBSave", ioE.getMessage());
        }
    }
}
