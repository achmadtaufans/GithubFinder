package com.example.bluebird.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.bluebird.data.ReminderList

/**
 * build room for database
 */
@Database(entities = arrayOf(ReminderList::class), version = 4)
abstract class BlueBirdDatabase : RoomDatabase() {
    abstract fun myReminderDAO():MyReminderDAO

    companion object {
        private var INSTANCE: BlueBirdDatabase? = null
        fun getInstance(context: Context): BlueBirdDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    BlueBirdDatabase::class.java,
                    "roomdb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as BlueBirdDatabase
        }
    }
}