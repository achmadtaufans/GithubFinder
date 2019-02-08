/**
 * @copyright ©2018 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core.calling_api

import insure.onoff.data.models.sample.Request
import insure.onoff.data.models.sample.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * APIList Interface.
 *
 * This interface is used as API container
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

interface APIList {

    //Temporary - To call some API
    @POST("api/users")
    fun displaySomeResponse(@Body request: Request): Call<Response>

}