package c7082.davisp.photoapp7082;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import c7082.davisp.photoapp7082.activities.MainActivity;
import c7082.davisp.photoapp7082.data.ImageData;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchExpressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSearchForResults() {

        Random rand = new Random();
        int id = rand.nextInt(100000);

        onView(withId(R.id.editButton))
                .perform(click());

        onView((withId(R.id.editCaption)))
                .inRoot(isDialog())
                .perform(replaceText("test id " + id));

        onView(withText("Save"))
                .inRoot(isDialog())
                .perform(click());

        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.captionInput))
                .perform(replaceText(id + ""));

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(withText("1 result(s)")));
    }

    @Test
    public void testSearchForResultsNo() {

        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.captionInput))
                .perform(replaceText("fdasg465547ghf"));

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(withText("0 result(s)")));
    }
}