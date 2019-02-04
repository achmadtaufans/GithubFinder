package insure.onoff.viewmodels.sample

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import insure.onoff.data.models.sample.Request
import insure.onoff.data.models.sample.Response
import insure.onoff.data.repository.sample.SampleRepository

class SampleViewModel constructor(private val repository: SampleRepository) : ViewModel() {

    private val response = MediatorLiveData<Response>();

    fun register(registerRequest: Request) : MediatorLiveData<Response> {
        val liveRegisterResponse = repository.register(registerRequest);
        response.addSource(liveRegisterResponse, response::setValue)
        return response;
    }

}