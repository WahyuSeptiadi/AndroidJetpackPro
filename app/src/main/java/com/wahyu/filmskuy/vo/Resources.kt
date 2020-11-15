package com.wahyu.filmskuy.vo

/**
 * Created by wahyu_septiadi on 14, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

data class Resources<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resources<T> = Resources(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): Resources<T> = Resources(Status.ERROR, data, msg)

        fun <T> loading(data: T?): Resources<T> = Resources(Status.LOADING, data, null)
    }
}