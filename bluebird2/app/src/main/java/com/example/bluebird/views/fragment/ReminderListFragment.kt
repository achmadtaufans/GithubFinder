package com.example.bluebird.views.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluebird.R
import com.example.bluebird.adapter.ReminderListAdapter
import com.example.bluebird.data.ReminderList
import com.example.bluebird.data.ReminderListResponse
import com.example.bluebird.databinding.FragmentReminderListBinding
import com.example.bluebird.utilities.DateHandler
import com.example.bluebird.utilities.InjectorUtils
import com.example.bluebird.utilities.runOnBackgroundThread
import com.example.bluebird.viewmodels.ReminderListViewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@Suppress("DEPRECATION")
class ReminderListFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentReminderListBinding
    private lateinit var reminderListViewModel: ReminderListViewModel
    private lateinit var adapter: ReminderListAdapter

    /**
     *  setup layout for reminder form fragment UI and toolbar
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReminderListBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Bluebird"
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    /**
     *  setup action button
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddReminder.setOnClickListener(this)
    }

    /**
     * build adapter whenever user open this fragment
     */
    override fun onStart() {
        super.onStart()

        getReminderList()
    }

    /**
     *  check DAO to get list of reminder, if DAO empty and will be hit endpoint to get reminder list
     */
    @SuppressLint("UseRequireInsteadOfGet")
    private fun getReminderList() {
        val factory = InjectorUtils.provideReminderListViewModelFactory(context!!, binding.clyAdapterReminderList)

        reminderListViewModel = ViewModelProviders.of(this, factory).get(ReminderListViewModel::class.java)

        binding.rcvReminderList.layoutManager = LinearLayoutManager(context)
        adapter = ReminderListAdapter(activity, fragmentManager!!)
        binding.rcvReminderList.adapter = adapter

        reminderListViewModel.getReminderDAO().observe(viewLifecycleOwner) { result ->
            if (!result.isEmpty()) {
                val resultList = result.sortedWith(compareBy {DateHandler.getEpoch(it.dateTime!!)}).asReversed()

                adapter.submitList(resultList)
                adapter.notifyDataSetChanged()
            } else {
                getListFromEndpoint()
            }
        }
    }

    /**
     *  hit endpoint to get list of reminder
     */
    private fun getListFromEndpoint() {
        reminderListViewModel.getReminderResponse().observe(viewLifecycleOwner,
            Observer<ReminderListResponse> { response ->
                if (!response.todos.isNullOrEmpty()) {

                    runOnBackgroundThread {
                        for (item in response.todos!!.indices) {
                            reminderListViewModel.saveReminder(response.todos!![item])
                        }
                    }

                    adapter.submitList(response.todos)
                    adapter.notifyDataSetChanged()

                    binding.listSize = response.todos!!.size
                }
            }
        )
    }

    /**
     * initialize button to set an action
     */
    override fun onClick(view: View?) {
        when (view) {
            binding.fabAddReminder -> addNewReminder()
        }
    }

    /**
     * move to reminder form to add new reminder
     */
    private fun addNewReminder() {
        requireView().findNavController().navigate(R.id.goToReminderForm)
    }
}
