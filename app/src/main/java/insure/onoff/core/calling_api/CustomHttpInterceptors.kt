/**
 * @copyright ©2018 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core.calling_api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.HashMap

/**
 * APIList Interface.
 *
 * This class function is creating custom headers when calling endpoint
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class CustomHttpInterceptors : Interceptor {

    protected var headers: MutableMap<String, String> = HashMap()

    //To create chain after calling CustomHttpInterceptors constructor
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        for ((key, value) in headers) {
            builder.addHeader(key, value)
        }
        val request = builder.build()
        return chain.proceed(request)
    }

    //Builder to make all added headers in one line code
    class Builder {
        internal var customHttpInterceptors: CustomHttpInterceptors

        init {
            customHttpInterceptors = CustomHttpInterceptors()
        }

        //To add some header. Every value should be string
        fun addHeaderParams(key: String, value: String): Builder {
            customHttpInterceptors.headers[key] = value
            return this
        }

        fun build(): CustomHttpInterceptors {
            return customHttpInterceptors
        }
    }
}