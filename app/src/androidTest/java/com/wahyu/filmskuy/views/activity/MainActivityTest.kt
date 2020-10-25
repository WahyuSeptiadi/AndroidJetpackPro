package com.wahyu.filmskuy.views.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.utils.DataDummy
import org.junit.Rule
import org.junit.Test

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MainActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.titleDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetailFilm)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.ratingDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingDetailFilm)).check(matches(withText(dummyMovies[0].rating)))
        onView(withId(R.id.releaseDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDetailFilm)).check(matches(withText(dummyMovies[0].release)))
        onView(withId(R.id.overviewDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewDetailFilm)).check(matches(withText(dummyMovies[0].overview)))
        onView(withId(R.id.btnBack)).perform(click())
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.titleDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetailFilm)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.ratingDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingDetailFilm)).check(matches(withText(dummyTvShow[0].rating)))
        onView(withId(R.id.releaseDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDetailFilm)).check(matches(withText(dummyTvShow[0].release)))
        onView(withId(R.id.overviewDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewDetailFilm)).check(matches(withText(dummyTvShow[0].overview)))
        onView(withId(R.id.btnBack)).perform(click())
    }
}