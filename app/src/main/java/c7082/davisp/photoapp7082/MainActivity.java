package c7082.davisp.photoapp7082;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.ParseException;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

import c7082.davisp.photoapp7082.data.ImageData;
import c7082.davisp.photoapp7082.data.ImageDatabase;

public class MainActivity extends AppCompatActivity {

    /**
     * Database of images
     */
    private ImageDatabase database = new ImageDatabase();

    /**
     * Index of current image displayed
     */
    private int imgIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        try {
            Uri uri = Uri.parse(list.get(imgIndex));

            setImageToDisplay(uri);

        } catch (IOException e) {
            Log.e("bitmapRegister", e.getMessage());
        }
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

        try {
            Uri uri = Uri.parse(list.get(imgIndex));

            setImageToDisplay(uri);

        } catch (IOException e) {
            Log.e("bitmapRegister", e.getMessage());
        }
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

                RegisterImage(imgUri, imgDate);

                imgIndex++;

                break;
            default:
                break;
        }
    }

    /**
     * Registers the image to the database
     */
    private ImageData RegisterImage(Uri imgUri, String imgDate) {
        try {
            setImageToDisplay(imgUri);

            ImageData imgData = new ImageData(imgUri);
            imgData.setDateTaken(imgDate);

            database.register(imgData);

            return imgData;

        } catch (IOException e) {
            Log.e("bitmapRegister", e.getMessage());
        }

        return null;
    }

    /**
     * Sets the display image
     * @param imgUri
     * @throws IOException
     */
    private void setImageToDisplay(Uri imgUri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);

        ImageView imgView = findViewById(R.id.primaryImage);

        imgView.setImageBitmap(bitmap);
    }
}
