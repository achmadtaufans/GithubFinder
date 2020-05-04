/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package com.example.bluebird.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bluebird.data.ReminderList

/**
 * This class is responsible to my Reminder DAO (Data Access Object)
 */
@Dao
interface MyReminderDAO {
    @Query("SELECT * from my_reminder")
    fun getAll(): LiveData<List<ReminderList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reminderList: ReminderList)

    @Update
    fun update(reminderList: ReminderList)

    @Query("DELETE from my_reminder")
    fun deleteAll()

    @Query("SELECT * FROM my_reminder WHERE id")
    fun displayMyReminders():  LiveData<List<ReminderList>>
}
