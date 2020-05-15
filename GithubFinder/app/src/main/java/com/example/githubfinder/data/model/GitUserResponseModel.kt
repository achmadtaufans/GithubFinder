package com.example.githubfinder.data.model

import com.google.gson.annotations.SerializedName

data class GitUserResponseModel(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean?,
    @SerializedName("items")
    var items: List<GitUserModel>,
    @SerializedName("total_count")
    var totalCount: Int
)