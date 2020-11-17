package com.wahyu.filmskuy.views.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.utils.EspressoIdlingResource
import com.wahyu.filmskuy.utils.ExpectationDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val dummyMovies = ExpectationDataDummy.dataDummyMovie()
    private val dummyTvShow = ExpectationDataDummy.dataDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource)
    }

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
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.titleDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetailFilm)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.ratingDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingDetailFilm)).check(matches(withText(dummyMovies[0].voteAverage.toString())))
        onView(withId(R.id.releaseDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDetailFilm)).check(matches(withText(dummyMovies[0].releaseDate)))
        onView(withId(R.id.overviewDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewDetailFilm)).check(matches(withText(dummyMovies[0].overview)))
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
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.titleDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetailFilm)).check(matches(withText(dummyTvShow[0].name)))
        onView(withId(R.id.ratingDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingDetailFilm)).check(matches(withText(dummyTvShow[0].voteAverage.toString())))
        onView(withId(R.id.releaseDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDetailFilm)).check(matches(withText(dummyTvShow[0].firstAirDate)))
        onView(withId(R.id.overviewDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewDetailFilm)).check(matches(withText(dummyTvShow[0].overview)))
    }

}