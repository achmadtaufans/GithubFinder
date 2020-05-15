package com.example.githubfinder.data.api

import android.util.Log
import com.example.githubfinder.data.model.GitUserResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "GithubService"
private const val IN_QUALIFIER = "in:login"

class GithubApiRequest {

    companion object{
        fun searchUsers(
            service: GithubService,
            query: String,
            page: Int,
            itemsPerPage: Int,
            onSuccess: (users: GitUserResponseModel? ) -> Unit,
            onError: (error: String) -> Unit) {


            val apiQuery = query + IN_QUALIFIER

            // make a service call
            service.searchUsers(apiQuery, page, itemsPerPage).enqueue(
                object : Callback<GitUserResponseModel> {
                    override fun onFailure(call: Call<GitUserResponseModel>?, t: Throwable) {
                        Log.d(TAG, "fail to get data")
                        onError(t.message ?: "unknown error")
                    }

                    override fun onResponse(
                        call: Call<GitUserResponseModel>?,
                        response: Response<GitUserResponseModel>
                    ) {
                        Log.e(TAG, "got a response ${response.body()}")
                        if (response.isSuccessful) {
                            val users = response.body()
                            onSuccess(users)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }
                }
            )
        }
    }
}