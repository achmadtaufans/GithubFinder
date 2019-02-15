/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.viewmodels.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import insure.onoff.data.repository.sample.SampleRepository

/**
 * SampleViewModel
 *
 * This class is responsible to SampleViewModel Factory. Factory for creating a [SampleViewModel] with a constructor that takes a [SampleRepository].
 *
 * Note : This class is temporary to understand Jetpack architecture as simple as possible
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class SampleViewModelFactory(private val repository : SampleRepository) : ViewModelProvider.NewInstanceFactory() {

    //To generate Sample ViewModel
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = SampleViewModel(repository) as T
}
