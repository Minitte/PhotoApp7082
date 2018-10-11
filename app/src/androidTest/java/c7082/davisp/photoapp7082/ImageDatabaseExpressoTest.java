package c7082.davisp.photoapp7082;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class ImageDatabaseExpressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testEditDisplayCaption() {
        onView(withId(R.id.editButton))
                .perform(click());

        onView((withId(R.id.editCaption)))
                .inRoot(isDialog())
                .perform(replaceText("Expresso's picture"));

        onView(withText("Save"))
                .inRoot(isDialog())
                .perform(click());

        onView(withId(R.id.captionText))
                .check(matches(withText("Expresso's picture")));
    }

    @Test
    public void testEditDatabaseUpdateCaption() {
        onView(withId(R.id.editButton))
                .perform(click());

        onView((withId(R.id.editCaption)))
                .inRoot(isDialog())
                .perform(replaceText("db's picture"));

        onView(withText("Save"))
                .inRoot(isDialog())
                .perform(click());

        ImageData current = mActivityRule.getActivity().currentImage;

        assertEquals("db's picture", current.getCaption());
    }

    @Test
    public void testEditDatabaseUpdateLocation() {
        onView(withId(R.id.editButton))
                .perform(click());

        onView(withText("Save"))
                .inRoot(isDialog())
                .perform(click());

        ImageData current = mActivityRule.getActivity().currentImage;
    }
}
