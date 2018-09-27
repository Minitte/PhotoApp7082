package c7082.davisp.photoapp7082.activities;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import c7082.davisp.photoapp7082.R;
import c7082.davisp.photoapp7082.data.ImageData;

public class SearchResultActivity extends AppCompatActivity {

    private LinearLayout resultList;

    private List<ImageView> imgViews;

    private List<ImageData> matches;

    private String captionCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        captionCriteria = getIntent().getStringExtra("captionSearch");

        resultList = (LinearLayout) findViewById(R.id.resultList);

        imgViews = new ArrayList<ImageView>();
        matches = new ArrayList<>();

        runCaptionSearch();

        addResults();
    }

    private void runCaptionSearch() {
        List<String> byCaption = MainActivity.database.getListSortedByCaption();

        for (int i = 0; i < byCaption.size(); i++) {
            ImageData data = MainActivity.database.get(byCaption.get(i));

            if (data.getCaption().contains(captionCriteria)) {
                matches.add(data);
            }
        }
    }

    private void addResults() {
        for (int i = 0; i < matches.size(); i++) {
            ImageData data = matches.get(i);

            ImageView iv = new ImageView(this);

            TextView text = new TextView(this);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(),
                        data.getImagePathAsUri());

                iv.setImageBitmap(bitmap);

                text.setText(data.getCaption());
                text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                resultList.addView(iv);

                iv.getLayoutParams().height = 300;

                resultList.addView(text);
            } catch (IOException ioE) {
                Log.e("Result",ioE.getMessage());
            }
        }
    }
}
