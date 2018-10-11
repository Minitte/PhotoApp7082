package c7082.davisp.photoapp7082.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import c7082.davisp.photoapp7082.R;
import c7082.davisp.photoapp7082.data.ImageData;
import c7082.davisp.photoapp7082.data.LocationData;
import c7082.davisp.photoapp7082.database.ImageDatabase;
import c7082.davisp.photoapp7082.database.ImageDatabaseLoader;
import c7082.davisp.photoapp7082.database.ImageDatabaseSaver;
import c7082.davisp.photoapp7082.dialog.EditDialogFragment;

public class MainActivity extends AppCompatActivity implements EditDialogFragment.EditDialogListener {

    /**
     * Database of images
     */
    public static ImageDatabase database = new ImageDatabase();

    /**
     * Path of db file
     */
    private String dbPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/PhotoApp7082DP/imgDB.data";

    /**
     * Index of current image displayed
     */
    private int imgIndex;

    /**
     * current image's data
     */
    public ImageData currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(dbPath);

        if (file.exists())
        {
            ImageDatabaseLoader loader = new ImageDatabaseLoader(file.getPath());

            database = loader.loadDatabase();

            imgIndex = database.getNumEntries() - 1;

            List<String> list = database.getListSortedByDate();

            ImageData imgData = database.get(list.get(imgIndex));

            try {
                setImageToDisplay(imgData);

            } catch (IOException e) {
                Log.e("bitmapDisplayStart", e.getMessage());
            }

            currentImage = imgData;
        }
    }

    /**
     * Opens camera activity
     * @param view
     */
    public void openCameraPreview(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, CameraActivity.REQUEST_ID);
    }

    /**
     * Goes to next image
     * @param view
     */
    public void nextImage(View view) {
        List<String> list = database.getListSortedByDate();

        // Skip if no images
        if (list.size() == 0) {
            return;
        }

        imgIndex = (imgIndex + 1) % list.size();

        ImageData imgData = database.get(list.get(imgIndex));

        try {
            setImageToDisplay(imgData);

        } catch (IOException e) {
            Log.e("bitmapRegister", e.getMessage());
        }

        currentImage = imgData;
    }

    /**
     * Goes to prev image
     * @param view
     */
    public void prevImage(View view) {
        List<String> list = database.getListSortedByDate();

        // Skip if no images
        if (list.size() == 0) {
            return;
        }

        imgIndex = (imgIndex - 1 + list.size()) % list.size();

        ImageData imgData = database.get(list.get(imgIndex));

        try {
            setImageToDisplay(imgData);

        } catch (IOException e) {
            Log.e("bitmapRegister", e.getMessage());
        }

        currentImage = imgData;
    }

    /**
     * Creates an edit dialog
     * @param view
     */
    public void createEditDialog(View view) {

        if (currentImage == null) {
            return;
        }

        EditDialogFragment edit = new EditDialogFragment();
        edit.data = currentImage;
        edit.listener = this;
        edit.show(getSupportFragmentManager(), "editDialog");
    }

    /**
     * Opens search activity
     */
    public void openSearchActivity(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("database", database);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED)
        {
            return;
        }

        switch (requestCode)
        {
            case CameraActivity.REQUEST_ID:
                Uri imgUri = data.getData();
                String imgDate = data.getStringExtra("imgDate");
                double longitude = data.getDoubleExtra("long", 0);
                double latitude = data.getDoubleExtra("lat", 0);

                RegisterImage(imgUri, imgDate, latitude, longitude);

                imgIndex++;

                break;
            default:
                break;
        }
    }

    /**
     * Registers the image to the database
     */
    private ImageData RegisterImage(Uri imgUri, String imgDate, double latitude, double longitude) {
        try {
            ImageData imgData = new ImageData(imgUri);
            imgData.setDateTaken(imgDate);
            imgData.setLocation(new LocationData(longitude, latitude));

            setImageToDisplay(imgData);

            database.register(imgData);

            File file = new File(dbPath);

            ImageDatabaseSaver saver = new ImageDatabaseSaver(file.getPath());

            imgIndex = database.getNumEntries() - 1;

            saver.writeDatabase(database);

            currentImage = imgData;

            return imgData;

        } catch (IOException e) {
            Log.e("bitmapRegister", e.getMessage());
        }

        return null;
    }

    /**
     * Sets the display image
     * @param imgData
     * @throws IOException
     */
    private void setImageToDisplay(ImageData imgData) throws IOException {
        // set image
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                this.getContentResolver(),
                imgData.getImagePathAsUri());

        ImageView imgView = findViewById(R.id.primaryImage);

        imgView.setImageBitmap(bitmap);

        updateInfoFields(imgData);
    }

    /**
     * Updates info field with given ImageData
     * @param imgData
     */
    private void updateInfoFields(ImageData imgData) {
        // set caption
        TextView capView = findViewById(R.id.captionText);

        capView.setText(imgData.getCaption());

        // set date
        TextView dateView = findViewById(R.id.dateText);

        dateView.setText(imgData.getDateTaken());

        // set location
        TextView locView = findViewById(R.id.locationText);

        locView.setText(imgData.getLocation().toString());
    }

    @Override
    public void onDialogPositiveClick(String newCaption) {

        currentImage.setCaption(newCaption);

        updateInfoFields(currentImage);

        File file = new File(dbPath);

        ImageDatabaseSaver saver = new ImageDatabaseSaver(file.getPath());

        saver.writeDatabase(database);
    }
}
