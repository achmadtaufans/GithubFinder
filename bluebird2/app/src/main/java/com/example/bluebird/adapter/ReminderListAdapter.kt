package com.example.bluebird.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bluebird.databinding.AdapterReminderListBinding
import com.example.bluebird.data.ReminderList
import com.example.bluebird.utilities.DateHandler

/**
 * create adapter and get data from reminder list fragment
 */
class ReminderListAdapter(private val activity: Activity?, var fragmentManager: FragmentManager) :
    ListAdapter<ReminderList, ReminderListAdapter.ViewHolder>(ReminderListDiffCallback()) {

    /**
     * Set view of adapter
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterReminderListBinding.inflate(
                LayoutInflater.from(parent.context)
                , parent
                , false
            )
        )
    }

    /**
     * Set action of each element on each item position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reminderListItem = getItem(position)

        holder.apply {
            bind(
                reminderListItem
            )
        }
    }

    /**
     * This class is use to hold xml binding variable to adapter
     */
    class ViewHolder(private val binding: AdapterReminderListBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var reminderList: ReminderList

        fun bind(
            reminderItem: ReminderList
        ) {
            this.reminderList = reminderItem
            binding.apply {
                reminderListItem = reminderListItem
                reminderTitle = reminderList.title
                reminderDescription = checkDescriptionLength()
                reminderDateAndTime = checkReminderDate()
            }
        }

        /**
         * check description if description length > 30 then set "..." for the rest
         */
        private fun checkDescriptionLength(): String {
            return if (reminderList.description!!.length >= 30) {
                reminderList.description!!.substring(0, 30) + "..."
            } else {
                reminderList.description!!
            }
        }

        /**
         * check if reminder is active or not based on current time
         */
        private fun checkReminderDate(): String {
            val currentTime = System.currentTimeMillis() / 1000

            if (DateHandler.getEpoch(reminderList.dateTime!!) < currentTime) {
                binding.txvReminderTitle.alpha = .4f
                binding.txvReminderDescription.alpha = .4f
                binding.txvReminderDateAndTime.alpha = .4f
            }

            return reminderList.dateTime!!
        }
    }

    /**
     *  Make recycler view doesn't attach data which is already exist
     */
    class ReminderListDiffCallback : DiffUtil.ItemCallback<ReminderList>() {
        /**
         * check item is same by id
         */
        override fun areItemsTheSame(oldItem: ReminderList, newItem: ReminderList): Boolean {
            return oldItem == newItem
        }

        /**
         * check content is same
         */
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ReminderList, newItem: ReminderList): Boolean {
            return oldItem == newItem
        }
    }
}
