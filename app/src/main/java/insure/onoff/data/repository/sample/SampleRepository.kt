package insure.onoff.data.repository.sample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import insure.onoff.core.calling_api.APIList
import insure.onoff.core.calling_api.RetrofitManager
import insure.onoff.data.models.sample.Request
import insure.onoff.data.models.sample.Response
import retrofit2.Call
import retrofit2.Callback

class SampleRepository() {

    val apiList : APIList = RetrofitManager.service;

    fun register(request: Request) : LiveData<Response> {
        val authResponse : MutableLiveData<Response> = MutableLiveData();

        apiList.displaySomeResponse(request).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                authResponse.value = response.body();
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "onFailure");
            }
        })

        return authResponse;
    }

}