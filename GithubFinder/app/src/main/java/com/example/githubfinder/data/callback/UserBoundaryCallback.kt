package com.example.githubfinder.data.callback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.githubfinder.data.database.GithubLocal
import com.example.githubfinder.data.model.GitUserModel
import com.example.githubfinder.data.api.GithubApiRequest.Companion.searchUsers
import com.example.githubfinder.data.api.GithubService

class UserBoundaryCallback (private val query: String,
                            private val remote: GithubService,
                            private val local: GithubLocal
) :  PagedList.BoundaryCallback<GitUserModel>(){

    companion object {
        private const val NETWORK_PAGE_SIZE = 50 // number of items per page to  load form network
    }

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    // LiveData of network errors.
    private val _networkErrors = MutableLiveData<String>()

    // LiveData if data is empty
    private val _isEmpty = MutableLiveData<Boolean>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // LiveData of the empty data
    val isEmpty : LiveData<Boolean>
        get() = _isEmpty

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: GitUserModel) {
        requestAndSaveData(query)
    }

    /**
     * request more items from network with query string
     * local results in db on success
     * or post error message on fail
     */
    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        /*
        make a remote call to github with specified query and increment request page counter
        on success insert the list of user objects into db table and increment last requested page counter
        on fail/error post the error string to livedata
         */
        searchUsers(remote, query, lastRequestedPage, NETWORK_PAGE_SIZE, { users ->

            // checking the total count of the list
            if ( users?.totalCount == 0) _isEmpty.postValue(true)
            else _isEmpty.postValue(false)

            users?.items?.let {
                local.insert(
                    it
                ) { // lambda that executes when insertion is done, moved outside parenthesis
                    lastRequestedPage++
                    isRequestInProgress = false
                }
            }

        }, { error ->
            _isEmpty.postValue(false)
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}