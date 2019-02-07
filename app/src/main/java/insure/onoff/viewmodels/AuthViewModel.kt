/**
 * @copyright ©2018 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import insure.onoff.data.models.account.AuthResponse
import insure.onoff.data.models.account.RegisterRequest
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

    fun register(registerRequest: RegisterRequest) : MediatorLiveData<AuthResponse> {

        return authResponse;
    }

}