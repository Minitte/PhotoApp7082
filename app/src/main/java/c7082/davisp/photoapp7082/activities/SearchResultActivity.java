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
import c7082.davisp.photoapp7082.bounds.DateBounds;
import c7082.davisp.photoapp7082.bounds.LocationBounds;
import c7082.davisp.photoapp7082.data.ImageData;

public class SearchResultActivity extends AppCompatActivity {

    public static String captionSearch;

    public static DateBounds dateBounds;

    public static LocationBounds locationBounds;

    private LinearLayout resultList;

    private List<ImageData> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        resultList = (LinearLayout) findViewById(R.id.resultList);

        matches = new ArrayList<>();

        // filter list
        filterList(captionSearch, dateBounds, locationBounds);

        addResults();

        TextView numResultView = findViewById(R.id.numResults);
        numResultView.setText(matches.size() + " result(s)");
    }

    private void filterList(String caption, DateBounds dateBounds, LocationBounds locBounds) {
        List<String> byCaption = MainActivity.database.getListSortedByCaption();

        for (int i = 0; i < byCaption.size(); i++) {
            ImageData data = MainActivity.database.get(byCaption.get(i));

            if (caption != null && !data.getCaption().contains(caption)) {
                continue;
            }

            if (dateBounds != null && !dateBounds.Contains(data.getDateTakenAsGC())) {
                continue;
            }

            if (locBounds != null && !locBounds.contains(data.getLocation().getLatitude(), data.getLocation().getLongitude())) {
                continue;
            }

            matches.add(data);
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
