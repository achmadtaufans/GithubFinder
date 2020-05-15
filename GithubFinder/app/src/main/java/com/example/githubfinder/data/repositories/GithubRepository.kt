package com.example.githubfinder.data.repositories

import androidx.paging.LivePagedListBuilder
import com.example.githubfinder.data.callback.UserBoundaryCallback
import com.example.githubfinder.data.database.GithubLocal
import com.example.githubfinder.data.model.UserSearchResultModel
import com.example.githubfinder.data.api.GithubService

class GithubRepository(
    private val remote : GithubService,
    private val local : GithubLocal

) {
    /**
     * Search users whose names match the query.
     */
    fun search(query: String) : UserSearchResultModel {
        // Get data from the local cache
        val dataSourceFactory  = local.usersByName(query)

        // Construct the boundary callback
        val boundaryCallback = UserBoundaryCallback(query, remote, local)
        val networkErrors = boundaryCallback.networkErrors
        val isEmpty = boundaryCallback.isEmpty

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get the network errors exposed by the boundary callback
        return UserSearchResultModel(data,networkErrors,isEmpty)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 10 // page data from the DataSource in chunks of 10 items.
    }

}