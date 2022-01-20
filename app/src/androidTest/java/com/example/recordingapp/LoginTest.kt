package com.example.recordingapp


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNot.not


@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    var USER = "10416"
    var PASSWORD = "1234"
    var ERROR_PASSWORD = "1111"

    @Test
    fun  appLaunchesSuccessfully () {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onLoginIsFailed() {
        onView(withId(R.id.etUser)).perform(ViewActions.clearText())
            .perform(ViewActions.typeText(USER), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.etPassword)).perform(ViewActions.clearText())
            .perform(ViewActions.typeText(ERROR_PASSWORD), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.btnInto))
            .perform(ViewActions.click())
            .check(matches(isDisplayed()))

    }

    @Test
    fun onLoginIsCorrect() {

        onView(withId(R.id.btnInto))
            .perform(ViewActions.click())
            .check(matches(not(isEnabled())))

        onView(withId(R.id.etUser)).perform(ViewActions.clearText())
            .perform(ViewActions.typeText(USER), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.etPassword)).perform(ViewActions.clearText())
            .perform(ViewActions.typeText(PASSWORD), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.btnInto))
            .perform(ViewActions.click())
            .check(matches(isEnabled()))

        Thread.sleep(2000)

        MenuTest().appLaunchesSuccessfully()

        MenuTest().onValidateComponents()

        Thread.sleep(2000)

        onView(withId(R.id.btnExit))
            .perform(ViewActions.click())

    }

}
