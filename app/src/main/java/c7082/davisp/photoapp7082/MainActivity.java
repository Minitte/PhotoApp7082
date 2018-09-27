package c7082.davisp.photoapp7082;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCameraPreview(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, CameraActivity.REQUEST_ID);
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

                RegisterImage(imgUri);

                break;
            default:
                break;
        }
    }

    /**
     * Registers the image to the database
     */
    private void RegisterImage(Uri imgUri)
    {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);

            ImageView imgView = findViewById(R.id.primaryImage);

            imgView.setImageBitmap(bitmap);

        } catch (IOException e) {
            Log.e("bitmapRegister", e.getMessage());
        }
    }
}
