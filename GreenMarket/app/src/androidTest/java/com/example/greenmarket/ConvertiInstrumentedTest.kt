package com.example.greenmarket

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class ConvertiInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<SplashActivity>
            = ActivityScenarioRule(SplashActivity::class.java)


    @Test
    fun convertiTest() {
        Thread.sleep(4000)
        onView(withId(R.id.editTextEmailLogin)).perform(typeText("abc@gmail.com"), closeSoftKeyboard())
        Thread.sleep(2000)
        onView(withId(R.id.editTextPasswordLogin)).perform(typeText("12345678"), closeSoftKeyboard())

        Thread.sleep(2000)
        onView(withId(R.id.buttonAccediLogin)).perform(click())

        Thread.sleep(5000)
        onView(withId(R.id.tessPt_bt)).perform(click())

        Thread.sleep(2000)
        onView(withId(R.id.converti)).perform(click())

        Thread.sleep(2000)
        onView(withId(R.id.valore_punti)).check(matches(withText("4")))

        Thread.sleep(5000)

    }

}