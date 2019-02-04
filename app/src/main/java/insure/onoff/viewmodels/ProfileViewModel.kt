package insure.onoff.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import insure.onoff.data.models.account.AuthResponse
import insure.onoff.data.models.account.RegisterRequest
import insure.onoff.data.repository.ProfileRepository

class ProfileViewModel constructor(private val repository: ProfileRepository) : ViewModel() {

    private val authResponse = MediatorLiveData<AuthResponse>();

    fun register(registerRequest: RegisterRequest) : MediatorLiveData<AuthResponse> {

        return authResponse;
    }

}