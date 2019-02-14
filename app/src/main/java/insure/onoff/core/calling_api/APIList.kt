/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core.calling_api

import insure.onoff.data.models.auth.AuthRequest
import insure.onoff.data.models.auth.AuthResponse
import insure.onoff.data.models.auth.verification_code.VerificationCodeResponse
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

    @POST("v/1/register")
    fun register(@Body authRequest: AuthRequest): Call<AuthResponse>

    @POST("v/1/register/resend-confirmation")
    fun resendConfirmation(@Body authRequest: AuthRequest): Call<VerificationCodeResponse>

    @POST("v/1/register/confirm")
    fun confirmOTP(@Body authRequest: AuthRequest): Call<VerificationCodeResponse>

    @POST("v/1/login")
    fun login(@Body authRequest: AuthRequest): Call<AuthResponse>

}