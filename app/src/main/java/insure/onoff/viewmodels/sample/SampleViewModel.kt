/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.viewmodels.sample

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import insure.onoff.data.models.sample.Request
import insure.onoff.data.models.sample.Response
import insure.onoff.data.repository.sample.SampleRepository

/**
 * SampleViewModel
 *
 * This class is responsible to be sample viewmodel. ViewModel manages UI-related data
 *
 * Note : This class is temporary to understand Jetpack architecture as simple as possible
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

class SampleViewModel constructor(private val repository: SampleRepository) : ViewModel() {

    private val response = MediatorLiveData<Response>();

    //To call register API (sample) on repository and get Live Data
    fun register(registerRequest: Request) : MediatorLiveData<Response> {
        val liveRegisterResponse = repository.register(registerRequest);
        response.addSource(liveRegisterResponse, response::setValue)
        return response;
    }

}