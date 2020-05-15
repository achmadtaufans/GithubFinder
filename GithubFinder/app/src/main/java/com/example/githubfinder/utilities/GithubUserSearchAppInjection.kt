package com.example.githubfinder.utilities

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.githubfinder.data.database.GithubLocal
import com.example.githubfinder.data.database.UsersDatabase
import com.example.githubfinder.data.api.GithubService
import com.example.githubfinder.data.repositories.GithubRepository
import com.example.githubfinder.data.viewmodel.MainViewModelFactory
import java.util.concurrent.Executors

object GithubUserSearchAppInjection{
    /**
     * Creates an instance of [GithubLocal] based on the database DAO.
     */
    private fun provideCache(context: Context): GithubLocal {
        val database = UsersDatabase.getInstance(context) // create Room db
        return GithubLocal(database.usersDao(),// Dao implementation
            Executors.newSingleThreadExecutor()// single thread sequential executor
        )
    }

    /**
     * Creates an instance of [GithubRepository] based on the [GithubService] and a
     * [GithubLocal]
     */
    private fun provideGithubRepository(context: Context): GithubRepository {
        return GithubRepository(
            GithubService.create(),//create retrofit service call for github with implementation of endpoints
            provideCache(
                context
            )// set cache
        )
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     *
     * has instance of the GithubRepository in constructor and context
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return MainViewModelFactory(
            provideGithubRepository(
                context
            )
        )
    }
}