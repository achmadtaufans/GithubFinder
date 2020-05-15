package com.example.githubfinder.data.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfinder.data.model.GitUserModel

/**
 * Adapter for the list of users.
 */
class UserAdapter : PagedListAdapter<GitUserModel, RecyclerView.ViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userItem = getItem(position)
        if (userItem != null) (holder as UserViewHolder).bind(userItem)
    }


    /**
     * diffUtils implementation for comparator
     */
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GitUserModel>() {
            override fun areItemsTheSame(oldItem: GitUserModel, newItem: GitUserModel): Boolean =
                oldItem.login == newItem.login

            override fun areContentsTheSame(oldItem: GitUserModel, newItem: GitUserModel): Boolean =
                oldItem == newItem
        }
    }
}