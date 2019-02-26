/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import insure.onoff.data.models.auth.AuthResponse
import insure.onoff.data.models.auth.AuthRequest
import insure.onoff.data.models.auth.check_phone_number.PhoneNumberCheckResponse
import insure.onoff.data.models.auth.verification_code.VerificationCodeResponse
import insure.onoff.data.repository.AuthRepository

/**
 * AuthViewModel
 *
 * This class is responsible to be auth ViewModel. This ViewModel manages UI-related data related with authentication
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class AuthViewModel constructor(private val repository: AuthRepository) : ViewModel() {

    private val authResponse = MediatorLiveData<AuthResponse>();
    private val verificationCodeResponse = MediatorLiveData<VerificationCodeResponse>();
    private val phoneNumberCheckResponse = MediatorLiveData<PhoneNumberCheckResponse>();

    //To call register by email on repository and getting Live Data
    fun registerByEmail(username: String, password: String, userType: String, deviceId: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, password, userType, deviceId, 1)
        return register(authRequest);
    }

    //To call register by phone on repository and getting Live Data
    fun registerByPhone(phoneNumber: String, password: String, userType: String, MFA: Boolean) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(phoneNumber, password, userType, MFA);
        return register(authRequest);
    }

    //To call register on repository and getting Live Data
    fun register(authRequest: AuthRequest) : MediatorLiveData<AuthResponse> {
        val liveData : LiveData<AuthResponse> = repository.register(authRequest);
        authResponse.addSource(liveData, authResponse::setValue)
        return authResponse;
    }

    //To resend confirmation code and getting LiveData
    fun resendConfirmation(username: String, userType: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, userType)
        val liveData : LiveData<VerificationCodeResponse> = repository.resendConfirmation(authRequest);
        verificationCodeResponse.addSource(liveData, verificationCodeResponse::setValue)
        return authResponse;
    }

    //To confirm OTP
    fun confirmOTP(username: String, verificationCode: String, userType: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, verificationCode, userType)
        val liveData : LiveData<VerificationCodeResponse> = repository.confirmOTP(authRequest);
        verificationCodeResponse.addSource(liveData, verificationCodeResponse::setValue)
        return authResponse;
    }

    //To login with phone or email
    fun login(username: String, password: String, userType: String, provider: String) : MediatorLiveData<AuthResponse> {
        val authRequest: AuthRequest = AuthRequest(username, password, userType, provider, 2)
        val liveData : LiveData<AuthResponse> = repository.login(authRequest);
        authResponse.addSource(liveData, authResponse::setValue)
        return authResponse;
    }

    fun checkExistedPhoneNumber(username: String, userType: String) : MediatorLiveData<PhoneNumberCheckResponse> {
        val authRequest: AuthRequest = AuthRequest(username, userType)
        val liveData : LiveData<PhoneNumberCheckResponse> = repository.checkPhoneNumber(authRequest);
        phoneNumberCheckResponse.addSource(liveData, phoneNumberCheckResponse::setValue)
        return phoneNumberCheckResponse;
    }
}
