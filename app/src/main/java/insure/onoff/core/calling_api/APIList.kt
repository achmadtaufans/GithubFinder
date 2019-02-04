package insure.onoff.core.calling_api

import insure.onoff.data.models.sample.Request
import insure.onoff.data.models.sample.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by andika on 1/27/19.
 */
interface APIList {
    //Temporary
    @POST("api/users")
    fun displaySomeResponse(@Body request: Request): Call<Response>

}