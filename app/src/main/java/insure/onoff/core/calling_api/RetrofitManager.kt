package insure.onoff.core.calling_api

import insure.onoff.utilities.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by andika on 1/27/19.
 */
object RetrofitManager {
    private val mRetrofit: Retrofit

    val service: APIList
        get() = mRetrofit.create(APIList::class.java)

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