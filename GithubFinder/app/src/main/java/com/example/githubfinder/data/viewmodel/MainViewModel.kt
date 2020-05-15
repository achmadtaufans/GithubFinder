package com.example.githubfinder.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.githubfinder.data.model.GitUserModel
import com.example.githubfinder.data.model.UserSearchResultModel
import com.example.githubfinder.data.repositories.GithubRepository

class MainViewModel(
    private val repository: GithubRepository
) : ViewModel() {

    private val _queryLiveData = MutableLiveData<String>()

    private val userResult: LiveData<UserSearchResultModel> = Transformations.map(_queryLiveData) {
        // returns UserSearchResult live data by executing search method from GithubRepository with every update to the queryLiveData
        repository.search(it)
    }

    val users: LiveData<PagedList<GitUserModel>> = Transformations.switchMap(userResult) {
        // returns the list of users live data from UserSearchResult
        it.data
    }

    val isEmpty : LiveData<Boolean> = Transformations.switchMap(userResult){
        // returns false true if data is empty
        it.isEmpty
    }

    val networkErrors: LiveData<String> = Transformations.switchMap(userResult) {
        // returns error string live data from UserSearchResult
        it.networkErrors
    }

    /**
     * Search a user based on a query string.
     * post a new value from queryLiveData
     * resulting in initiating a search request from repository manager through userResult
     */
    fun searchUser(queryString: String) {
        _queryLiveData.postValue(queryString)
    }

    /**
     * Get the last query value.
     */
    fun lastQueryValue(): String? = _queryLiveData.value
}