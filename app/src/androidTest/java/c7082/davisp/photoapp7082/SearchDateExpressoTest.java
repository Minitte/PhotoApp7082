package c7082.davisp.photoapp7082;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import c7082.davisp.photoapp7082.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchDateExpressoTest {
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
    public void testDateSearchForResult() {
        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.captionInput))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.dateFromBtn))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 01, 06));

        onView(withText("OK"))
                .perform(click());

        onView(withId(R.id.dateToBtn))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2900, 05, 23));

        onView(withText("OK"))
                .perform(click());

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(not(withText("0 result(s)"))));
    }

    @Test
    public void testDateSearchForResultNone() {
        onView(withId(R.id.searchBtn))
                .perform(click());

        onView(withId(R.id.captionInput))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.dateFromBtn))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2901, 01, 06));

        onView(withText("OK"))
                .perform(click());

        onView(withId(R.id.dateToBtn))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(3724, 05, 23));

        onView(withText("OK"))
                .perform(click());

        onView(withId(R.id.submitBtn))
                .perform(click());

        onView(withId(R.id.numResults))
                .check(matches(withText("0 result(s)")));
    }
}
