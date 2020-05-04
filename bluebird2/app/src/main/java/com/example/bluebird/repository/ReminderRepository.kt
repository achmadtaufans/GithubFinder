package com.example.bluebird.repository

import android.bluetooth.BluetoothClass
import android.content.Context
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bluebird.api.ApiMain
import com.example.bluebird.data.ReminderList
import com.example.bluebird.data.ReminderListResponse
import com.example.bluebird.data.database.MyReminderDAO
import com.example.bluebird.utilities.CallBackKt
import com.example.bluebird.utilities.runOnBackgroundThread
import retrofit2.Call
import java.time.ZoneId

/**
 *  this class is responsible to hit endpoint and get data
 */
class ReminderRepository(var context: Context, var constraintLayout: ConstraintLayout, var myReminderDAO: MyReminderDAO) {
    val apilist = ApiMain().services

    /**
     * Function to use custom callback retrofit
     */
    fun <T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
        val callBackKt = CallBackKt<T>()

        callback.invoke(callBackKt)

        this.enqueue(callBackKt)
    }

    /**
     *  hit endpoint to get data reminder list from API
     */
    fun getReminderList(): LiveData<ReminderListResponse> {
        val reminderResponse: MutableLiveData<ReminderListResponse> = MutableLiveData()

        apilist.getReminder("5e7988f52d00005cbf18bd7b").enqueue {
            onSuccess = {
                reminderResponse.value = it.body()
            }

            onFailure = {}
        }

        return reminderResponse
    }

    /**
     * to save reminder in my reminder DAO
     */
    fun saveReminder(reminderList: ReminderList) {
        runOnBackgroundThread {
            myReminderDAO.insert(reminderList)
        }
    }

    /**
     * To get reminder list data from reminder viewmodel
     */
    fun getReminderDAO(): LiveData<List<ReminderList>> {
        return myReminderDAO.displayMyReminders()
    }
}
