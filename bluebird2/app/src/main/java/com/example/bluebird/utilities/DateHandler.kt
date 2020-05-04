package com.example.bluebird.utilities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * This object is responsible to any related with Date
 */
object DateHandler {
    /**
     * To get epoch integer value from date.
     * Note : Later, when this method is used in many classes. This method location should be changed
     */
    @SuppressLint("SimpleDateFormat")
    fun getEpoch(date: String, dateFormat: String = "dd-MM-yyyy hh:mm") : Long {
        var milliSecond: Long = 0
        val date = SimpleDateFormat(dateFormat).parse(date)

        milliSecond = date.time

        return milliSecond / 1000
    }
}