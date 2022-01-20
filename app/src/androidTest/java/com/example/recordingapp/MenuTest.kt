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
import org.hamcrest.core.IsNot.not


@LargeTest
@RunWith(AndroidJUnit4::class)
class MenuTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MenuActivity::class.java)


    @Test
    fun  appLaunchesSuccessfully () {
        ActivityScenario.launch(MenuActivity::class.java)
    }

    @Test
    fun onValidateComponents() {

        onView(withId(R.id.btnList))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnProfiler))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnExit))
            .check(matches(isDisplayed()))

        onView(withText("Selecciona una opción"))
            .check(matches(isDisplayed()))
    }

}
