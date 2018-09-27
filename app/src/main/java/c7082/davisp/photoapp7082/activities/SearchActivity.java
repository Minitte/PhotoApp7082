package c7082.davisp.photoapp7082.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import c7082.davisp.photoapp7082.R;
import c7082.davisp.photoapp7082.database.ImageDatabase;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
    }

    /**
     * Begins the search
     * @param view
     */
    public void submitSearch(View view) {
        Intent intent = new Intent(this, SearchResultActivity.class);

        TextView captionText = findViewById(R.id.captionInput);

        intent.putExtra("captionSearch", captionText.getText().toString());
        startActivity(intent);
    }
}
