/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import insure.onoff.data.repository.AuthRepository
import insure.onoff.data.repository.sample.SampleRepository
import insure.onoff.viewmodels.sample.SampleViewModel

/**
 * AuthViewModelFactory
 *
 * This class is responsible to AuthViewModel Factory. Factory for creating a [AuthViewModel] with a constructor that takes a [AuthRepository].
 *
 * Note : This class is temporary to understand Jetpack architecture as simple as possible
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class AuthViewModelFactory(private val repository : AuthRepository) : ViewModelProvider.NewInstanceFactory() {

    //To generate AuthViewModel
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AuthViewModel(repository) as T
}
