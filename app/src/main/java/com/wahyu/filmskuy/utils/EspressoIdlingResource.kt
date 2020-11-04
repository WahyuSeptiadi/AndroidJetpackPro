package com.wahyu.filmskuy.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by wahyu_septiadi on 04, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val countingIdlingResource = CountingIdlingResource(RESOURCE)
    private val idlingResource: IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!idlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}