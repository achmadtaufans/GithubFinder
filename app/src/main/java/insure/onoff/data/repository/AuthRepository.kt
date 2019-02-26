/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import insure.onoff.core.calling_api.APIList
import insure.onoff.core.calling_api.RetrofitManager
import insure.onoff.data.models.auth.AuthResponse
import insure.onoff.data.models.auth.AuthRequest
import insure.onoff.data.models.auth.verification_code.VerificationCodeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import insure.onoff.data.models.auth.check_phone_number.PhoneNumberCheckResponse

/**
 * AuthRepository
 *
 * This class is responsible to be repository that related with authentication
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

class AuthRepository() {
    val TAG : String = AuthRepository::class.java.getName()

    val apiList : APIList = RetrofitManager.service;

    //To call register API
    fun register(authRequest: AuthRequest) : LiveData<AuthResponse> {
        val authResponse : MutableLiveData<AuthResponse> = MutableLiveData();

        apiList.register(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                authResponse.value = response.body();
                Log.e(TAG, "Go to On Response");
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.e(TAG, "Go to On Failure");
            }
        })

        return authResponse;
    }

    //To call resend confirmation code API
    fun resendConfirmation(authRequest: AuthRequest) : LiveData<VerificationCodeResponse> {
        val verificationCodeResponse : MutableLiveData<VerificationCodeResponse> = MutableLiveData();

        apiList.resendConfirmation(authRequest).enqueue(object: Callback<VerificationCodeResponse> {
            override fun onResponse(call: Call<VerificationCodeResponse>, response: Response<VerificationCodeResponse>) {
                Log.e(TAG, "Go to On Response");
            }

            override fun onFailure(call: Call<VerificationCodeResponse>, t: Throwable) {
                Log.e(TAG, "Go to On Failure");
            }
        })

        return verificationCodeResponse;
    }

    //To call confirm code API
    fun confirmOTP(authRequest: AuthRequest) : LiveData<VerificationCodeResponse> {
        val verificationCodeResponse : MutableLiveData<VerificationCodeResponse> = MutableLiveData();

        apiList.confirmRegisterOTP(authRequest).enqueue(object: Callback<VerificationCodeResponse> {
            override fun onResponse(call: Call<VerificationCodeResponse>, response: Response<VerificationCodeResponse>) {
                Log.e(TAG, "Go to On Response");
            }

            override fun onFailure(call: Call<VerificationCodeResponse>, t: Throwable) {
                Log.e(TAG, "Go to On Failure");
            }
        })

        return verificationCodeResponse;
    }

    //To call login API
    fun login(authRequest: AuthRequest) : LiveData<AuthResponse> {
        val authResponse : MutableLiveData<AuthResponse> = MutableLiveData();

        apiList.login(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                authResponse.value = response.body();
                Log.e(TAG, "Go to On Response");
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.e(TAG, "Go to On Failure");
            }
        })

        return authResponse;
    }

    //To check phone number
    fun checkPhoneNumber(authRequest: AuthRequest) : LiveData<PhoneNumberCheckResponse> {
        val phoneNumberCheckResponse : MutableLiveData<PhoneNumberCheckResponse> = MutableLiveData();

        apiList.checkExistedPhoneNumber(authRequest).enqueue(object: Callback<PhoneNumberCheckResponse> {
            override fun onResponse(call: Call<PhoneNumberCheckResponse>, response: Response<PhoneNumberCheckResponse>) {
                phoneNumberCheckResponse.value = response.body()
                Log.e(TAG, "Go to On Response");
            }

            override fun onFailure(call: Call<PhoneNumberCheckResponse>, t: Throwable) {
                Log.e(TAG, "Go to On Failure");
            }
        })

        return phoneNumberCheckResponse;
    }
}
