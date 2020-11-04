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
import com.wahyu.filmskuy.data.network.ApiClient
import com.wahyu.filmskuy.utils.EspressoIdlingResource
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
    private val dummyMovies = ApiClient.create().getMovie().execute().body()?.results
    private val dummyTvShow = ApiClient.create().getTvShow().execute().body()?.results

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies?.size!!
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
        onView(withId(R.id.titleDetailFilm)).check(matches(withText(dummyMovies?.get(0)?.title)))
        onView(withId(R.id.ratingDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingDetailFilm)).check(matches(withText(dummyMovies?.get(0)?.vote_average.toString())))
        onView(withId(R.id.releaseDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDetailFilm)).check(matches(withText(dummyMovies?.get(0)?.release_date)))
        onView(withId(R.id.overviewDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewDetailFilm)).check(matches(withText(dummyMovies?.get(0)?.overview)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow?.size!!
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
        onView(withId(R.id.titleDetailFilm)).check(matches(withText(dummyTvShow?.get(0)?.name)))
        onView(withId(R.id.ratingDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingDetailFilm)).check(matches(withText(dummyTvShow?.get(0)?.vote_average.toString())))
        onView(withId(R.id.releaseDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDetailFilm)).check(matches(withText(dummyTvShow?.get(0)?.first_air_date)))
        onView(withId(R.id.overviewDetailFilm)).check(matches(isDisplayed()))
        onView(withId(R.id.overviewDetailFilm)).check(matches(withText(dummyTvShow?.get(0)?.overview)))
    }

}