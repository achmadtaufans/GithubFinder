package insure.onoff.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import insure.onoff.data.repository.ProfileRepository

class ProfileViewModelFactory(private val repository : ProfileRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ProfileViewModel(repository) as T

}