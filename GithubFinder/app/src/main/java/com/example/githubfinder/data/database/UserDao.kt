package com.example.githubfinder.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubfinder.data.model.GitUserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<GitUserModel>)

    // Do a similar query as the search API:
    // Look for users that contain the query string in the name login
    @Query("SELECT * FROM users WHERE (login LIKE :queryString)")
    fun usersByName(queryString: String): DataSource.Factory<Int, GitUserModel>
}