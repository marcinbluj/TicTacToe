package pl.sda.bluj.marcin.tictactoe.view;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.sda.bluj.marcin.tictactoe.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by MSI on 21.05.2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMainActivityHasProperElements() {
        ViewInteraction customView = onView(withId(R.id.customView));
        ViewInteraction circle = onView(withId(R.id.circle));
        ViewInteraction cross = onView(withId(R.id.cross));
        ViewInteraction menu = onView(withId(R.id.new_game_menu_button));
        customView.check(matches(isDisplayed()));
        customView.check(matches(isEnabled()));
        circle.check(matches(isDisplayed()));
        cross.check(matches(isDisplayed()));
        menu.check(matches(isDisplayed()));
        menu.check(matches(isEnabled()));
    }

    @Test
    public void testViewsThrowNoErrorsWhenClicked() {
        ViewInteraction customView = onView(withId(R.id.customView));
        ViewInteraction circle = onView(withId(R.id.circle));
        ViewInteraction cross = onView(withId(R.id.cross));
        customView.perform(click());
        circle.perform(click());
        cross.perform(click());
    }

    @Test
    public void testNewGameButton_isClickable() {
        ViewInteraction menu = onView(withId(R.id.new_game_menu_button));
        menu.check(matches(isClickable()));
        menu.perform(click());
    }

    @Test
    public void testNewGameButton_multipleClick() {
        ViewInteraction menu = onView(withId(R.id.new_game_menu_button));
        menu.check(matches(isClickable()));
        for (int i = 0; i < 5; i++) {
            menu.perform(click());
        }
    }
}