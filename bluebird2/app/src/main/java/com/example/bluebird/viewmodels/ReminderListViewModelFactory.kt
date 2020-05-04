package com.example.bluebird.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bluebird.repository.ReminderRepository

/**
 * This class is responsible to WalletViewModel Factory. Factory for creating a [ReminderListViewModel] with a constructor that takes a [ReminderRepository].
 */
class ReminderListViewModelFactory(private val repository: ReminderRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ReminderListViewModel(repository) as T
}