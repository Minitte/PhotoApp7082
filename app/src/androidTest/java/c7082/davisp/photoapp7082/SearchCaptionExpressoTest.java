package c7082.davisp.photoapp7082;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Before;
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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchCaptionExpressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        onView(withId(R.id.snapBtn))
                .perform(click());

        onView(withId(R.id.captureBtn))
                .perform(click());
    }

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
                .perform(replaceText(id + ""))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(withText("1 result(s)")));
    }

    @Test
    public void testSearchForResultsNone() {

        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.captionInput))
                .perform(replaceText("fdasg465547ghf"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(withText("0 result(s)")));
    }




}