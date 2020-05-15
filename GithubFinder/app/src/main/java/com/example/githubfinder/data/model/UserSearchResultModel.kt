package com.example.githubfinder.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class UserSearchResultModel(
    val data: LiveData<PagedList<GitUserModel>>,
    val networkErrors: LiveData<String>,
    val isEmpty : LiveData<Boolean>
)