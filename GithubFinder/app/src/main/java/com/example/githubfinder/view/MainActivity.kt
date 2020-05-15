package com.example.githubfinder.view

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.example.githubfinder.R
import com.example.githubfinder.data.adapter.UserAdapter
import com.example.githubfinder.data.model.GitUserModel
import com.example.githubfinder.data.viewmodel.MainViewModel
import com.example.githubfinder.databinding.ActivityMainBinding
import com.example.githubfinder.utilities.GithubUserSearchAppInjection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)    // get the view
        // model
        viewModel = ViewModelProviders.of(this, GithubUserSearchAppInjection.provideViewModelFactory(this)).get(MainViewModel::class.java)

        // initiate adapter and load default user list from network
        initAdapter()

        // check if the search query string
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY

        // initiate / update search query string livedata in vm
        progressBar.visibility = View.GONE
        initSearch(query)

        checkKeyword()
    }

    //hit endpoint every words changing
    private fun checkKeyword() {
        binding.searchUser.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {  updateUserListFromInput() }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        // keep track of last search query string
        outState.putString(LAST_SEARCH_QUERY, viewModel.lastQueryValue())
    }

    /**
     * initiate and set the rv adapter
     * observe the vm users live data for returned list of user from network call
     *
     * observes network error messages
     *
     * observe empty list
     */
    private fun initAdapter() {
        binding.list.adapter = adapter

        // observer list of user live data returned form network call
        viewModel.users.observe(this, Observer<PagedList<GitUserModel>> {
            Log.d("Activity", "list: ${it?.size}")
            adapter.submitList(it) // Submits a new list to be diffed, and displayed.
            if (it.size>0) progressBar.visibility = View.GONE
        })

        // observe the network error
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show()
        })

        // observe if the list is empty
        viewModel.isEmpty.observe(this, Observer<Boolean> {
            showEmptyList(it)
        })
    }

    /**
     * display query sting
     * initiate search
     */
    private fun initSearch(query: String) {
        binding.searchUser.setText(query)

        binding.searchUser.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateUserListFromInput()
                true
            } else {
                false
            }
        }

        binding.searchUser.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateUserListFromInput()
                true
            } else {
                false
            }
        }
    }

    /**
     * helper method to show or hide a textview relaying no data message
     * or the list of users
     * @param show flag indicating ui state
     */
    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
            progressBar.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun updateUserListFromInput() {
        binding.searchUser.text.trim().let {
            if (it.isNotEmpty()) {

                // manual init the progressbar
                progressBar.visibility = View.VISIBLE
                emptyList.visibility = View.GONE

                list.scrollToPosition(0)// reset rv scroll
                viewModel.searchUser(it.toString()) // initiate a search for query
                adapter.submitList(null) // empty/reset the adapter
            }
        }
    }


    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query" // last saved search string
        private const val DEFAULT_QUERY = "" // default git user search query string
    }
}
