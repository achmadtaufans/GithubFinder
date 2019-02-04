package insure.onoff.viewmodels.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import insure.onoff.data.repository.sample.SampleRepository

class SampleViewModelFactory(private val repository : SampleRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = SampleViewModel(repository) as T

}