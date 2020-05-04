package com.example.bluebird.api

import com.example.bluebird.data.ReminderListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * set api interface function
 */
interface APIList {
    @GET
    fun getReminder(@Url url: String): Call<ReminderListResponse>
}