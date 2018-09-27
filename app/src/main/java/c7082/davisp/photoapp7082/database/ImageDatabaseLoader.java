package c7082.davisp.photoapp7082.database;

import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ImageDatabaseLoader {

    /**
     * Path to file
     */
    private String path;

    /**
     * Constructor for a ImageDatabaseSaver
     * @param path to the file
     */
    public ImageDatabaseLoader(String path) {
        this.path = path;
    }

    /**
     * Loads data base from path
     * @return
     */
    public ImageDatabase loadDatabase() {
        ImageDatabase db = null;

        try {
            // open stream
            FileInputStream fStream = new FileInputStream(path);

            ObjectInputStream objStream = new ObjectInputStream(fStream);

            // write
            db = (ImageDatabase)objStream.readObject();

            // close stream
            objStream.close();

            fStream.close();

        } catch (IOException ioE) {
            Log.e("ImgDBLoad", ioE.getMessage());
        } catch (ClassNotFoundException cnfE) {
            Log.e("ImgDBLoadType", cnfE.getMessage());
        }

        return db;
    }
}
