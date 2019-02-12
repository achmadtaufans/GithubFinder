/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import insure.onoff.core.calling_api.APIList
import insure.onoff.core.calling_api.RetrofitManager
import insure.onoff.data.models.account.AuthResponse
import insure.onoff.data.models.account.AuthRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * AuthRepository
 *
 * This class is responsible to be repository that related with authentication
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

class AuthRepository() {

    val apiList : APIList = RetrofitManager.service;

    fun register(authRequest: AuthRequest) : LiveData<AuthResponse> {
        val authResponse : MutableLiveData<AuthResponse> = MutableLiveData();

        apiList.register(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                authResponse.value = response.body();
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {

            }
        })

        return authResponse;
    }

    fun resendConfirmation(authRequest: AuthRequest) : LiveData<AuthResponse> {
        val authResponse : MutableLiveData<AuthResponse> = MutableLiveData();

        apiList.resendConfirmation(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                authResponse.value = response.body();
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {

            }
        })

        return authResponse;
    }

    fun confirmOTP(authRequest: AuthRequest) : LiveData<AuthResponse> {
        val authResponse : MutableLiveData<AuthResponse> = MutableLiveData();

        apiList.confirmOTP(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                authResponse.value = response.body();
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {

            }
        })

        return authResponse;
    }

    fun login(authRequest: AuthRequest) : LiveData<AuthResponse> {
        val authResponse : MutableLiveData<AuthResponse> = MutableLiveData();

        apiList.login(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                authResponse.value = response.body();
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {

            }
        })

        return authResponse;
    }


}