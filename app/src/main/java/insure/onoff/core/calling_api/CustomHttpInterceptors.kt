package insure.onoff.core.calling_api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.HashMap

/**
 * Created by andika on 1/27/19.
 */
class CustomHttpInterceptors : Interceptor {

    protected var headers: MutableMap<String, String> = HashMap()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        for ((key, value) in headers) {
            builder.addHeader(key, value)
        }
        val request = builder.build()
        return chain.proceed(request)
    }

    class Builder {
        internal var customHttpInterceptors: CustomHttpInterceptors

        init {
            customHttpInterceptors = CustomHttpInterceptors()
        }

        //Every value should be string
        fun addHeaderParams(key: String, value: String): Builder {
            customHttpInterceptors.headers[key] = value
            return this
        }

        fun build(): CustomHttpInterceptors {
            return customHttpInterceptors
        }
    }
}