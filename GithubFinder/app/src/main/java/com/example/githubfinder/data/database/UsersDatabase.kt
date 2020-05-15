package com.example.githubfinder.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubfinder.data.model.GitUserModel

@Database(
    entities = [GitUserModel::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase(){

    abstract fun usersDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        /**
         * build our room DB
         */
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                UsersDatabase::class.java, "github_user.db")
                .build()
    }

}