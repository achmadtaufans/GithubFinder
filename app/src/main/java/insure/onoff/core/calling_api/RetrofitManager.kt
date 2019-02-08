/**
 * @copyright ©2018 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core.calling_api

import insure.onoff.utilities.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit Manager
 *
 * This class is responsible to manage Retrofit and create Retrofit service. This class only needs 1 object
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
object RetrofitManager {
    private val mRetrofit: Retrofit

    //To provide Retrofit service
    val service: APIList
        get() = mRetrofit.create(APIList::class.java)

    //Initialize needed properties like headers, base url, etc...
    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)

        //Build ok http client
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        mRetrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

}