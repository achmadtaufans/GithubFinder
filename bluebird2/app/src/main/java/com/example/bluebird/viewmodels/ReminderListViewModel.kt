package com.example.bluebird.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.bluebird.data.ReminderList
import com.example.bluebird.data.ReminderListResponse
import com.example.bluebird.repository.ReminderRepository

/**
 * This class is responsible to be speed ViewModel. This ViewModel manages UI-related data related with get Speed
 */
class ReminderListViewModel constructor(private val repository: ReminderRepository) : ViewModel() {
    private val reminderListResponse = MediatorLiveData<ReminderListResponse>()

    /**
     * To get Reminder List on repository and getting Live Data
     */
    fun getReminderResponse(
    ): MediatorLiveData<ReminderListResponse> {
        val liveData: LiveData<ReminderListResponse> = repository.getReminderList()

        reminderListResponse.addSource(liveData, reminderListResponse::setValue)

        return reminderListResponse
    }

    /**
     * save reminder to DAO in repository
     */
    fun saveReminder(reminderList: ReminderList) {
        repository.saveReminder(reminderList)
    }

    /**
     * get reminder from DAO in repository
     */
    fun getReminderDAO(): LiveData<List<ReminderList>> {
        return repository.getReminderDAO()
    }
}