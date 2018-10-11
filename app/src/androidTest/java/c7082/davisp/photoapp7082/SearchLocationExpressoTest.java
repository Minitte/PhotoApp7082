package c7082.davisp.photoapp7082;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import c7082.davisp.photoapp7082.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchLocationExpressoTest {
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
    public void testLocationSearchForResult() {
        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.minLat))
                .perform(replaceText("105.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.maxLat))
                .perform(replaceText("0.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.minLong))
                .perform(replaceText("0.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.maxLong))
                .perform(replaceText("105.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(not(withText("0 result(s)"))));
    }

    @Test
    public void testLocationSwappedSearchForResult() {
        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.minLat))
                .perform(replaceText("0.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.maxLat))
                .perform(replaceText("105.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.minLong))
                .perform(replaceText("105.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.maxLong))
                .perform(replaceText("0.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(not(withText("0 result(s)"))));
    }

    @Test
    public void testLocationSearchForResultNone() {
        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.minLat))
                .perform(replaceText("106"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.maxLat))
                .perform(replaceText("321"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.minLong))
                .perform(replaceText("0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.maxLong))
                .perform(replaceText("105.0"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(withText("0 result(s)")));
    }
}
