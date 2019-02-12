/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import insure.onoff.data.models.account.AuthResponse
import insure.onoff.data.models.account.AuthRequest
import insure.onoff.data.repository.AuthRepository

/**
 * AuthViewModel
 *
 * This class is responsible to be profile ViewModel. This ViewModel manages UI-related data related with authentication
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

class AuthViewModel constructor(private val repository: AuthRepository) : ViewModel() {

    private val authResponse = MediatorLiveData<AuthResponse>();

    fun registerByEmail(username: String, password: String, userType: String, deviceId: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, password, userType, deviceId, 1)
        return register(authRequest);
    }

    fun registerByPhone(phoneNumber: String, password: String, userType: String, MFA: Boolean) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(phoneNumber, password, userType, MFA);
        return register(authRequest);
    }

    //To call register by email and phone API on repository and getting Live Data
    fun register(authRequest: AuthRequest) : MediatorLiveData<AuthResponse> {
        val liveData : LiveData<AuthResponse> = repository.register(authRequest);
        authResponse.addSource(liveData, authResponse::setValue)
        return authResponse;
    }

    fun resendConfirmation(username: String, userType: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, userType)
        val liveData : LiveData<AuthResponse> = repository.resendConfirmation(authRequest);
        authResponse.addSource(liveData, authResponse::setValue)
        return authResponse;
    }

    fun confirmOTP(username: String, verificationCode: String, userType: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, verificationCode, userType)
        val liveData : LiveData<AuthResponse> = repository.confirmOTP(authRequest);
        authResponse.addSource(liveData, authResponse::setValue)
        return authResponse;
    }

    fun login(username: String, password: String, userType: String, provider: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, password, userType, provider, 2)
        val liveData : LiveData<AuthResponse> = repository.login(authRequest);
        authResponse.addSource(liveData, authResponse::setValue)
        return authResponse;
    }

}