package com.example.bluebird.utilities

import android.content.Context
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bluebird.data.database.BlueBirdDatabase
import com.example.bluebird.repository.ReminderRepository
import com.example.bluebird.viewmodels.ReminderListViewModelFactory

/**
 * This class is responsible to inject some objects
 */
object InjectorUtils {
    /**
     * To get Reminder repository
     */
    private fun getReminderRepository(context: Context, constraintLayout: ConstraintLayout): ReminderRepository {
        return ReminderRepository(context, constraintLayout, BlueBirdDatabase.getInstance(context)!!.myReminderDAO())
    }

    /**
     * To provide reminder list View Model Factory with repository param
     */
    fun provideReminderListViewModelFactory(
        context: Context,
        constraintLayout: ConstraintLayout
    ): ReminderListViewModelFactory {
        val repository = getReminderRepository(context, constraintLayout)

        return ReminderListViewModelFactory(repository)
    }
}
