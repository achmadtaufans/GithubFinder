package com.example.bluebird.views.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.bluebird.R
import com.example.bluebird.data.ReminderList
import com.example.bluebird.databinding.FragmentReminderFormBinding
import com.example.bluebird.utilities.InjectorUtils
import com.example.bluebird.utilities.runOnBackgroundThread
import com.example.bluebird.viewmodels.ReminderListViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ReminderFormFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentReminderFormBinding
    private lateinit var reminderListViewModel: ReminderListViewModel

    /**
     * setup layout for reminder form fragment UI and toolbar
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReminderFormBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Add Reminder"
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    /**
     *  setup action button
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setButton()
    }

    /**
     * initialize button to set action
     */
    private fun setButton() {
        binding.edtDateInput.setOnClickListener { openCalendarDialog() }
        binding.edtTimeInput.setOnClickListener { openTimeDialog() }
        binding.btnSubmitReminder.setOnClickListener { checkEmptyForm() }
    }

    /**
     * checking empty field if submit button was pressed
     */
    private fun checkEmptyForm() {
        binding.txvErrorMessage.setText("")

        when {
            binding.edtInputTitle.text.toString() == "" -> { binding.txvErrorMessage.setText("Title must be filled!") }
            binding.edtInputDescription.text.toString() == "" -> { binding.txvErrorMessage.setText("Description must be filled!") }
            binding.edtDateInput.text.toString() == "" -> { binding.txvErrorMessage.setText("Date must be filled!") }
            binding.edtTimeInput.text.toString() == "" -> { binding.txvErrorMessage.setText("Time must be filled!") }
            else -> { saveReminder() }
        }
    }

    /**
     * saving reminder whenever user pressed submit button and save to DAO
     */
    private fun saveReminder() {
        val reminderModel = ReminderList((System.currentTimeMillis()-1000))
        val factory = InjectorUtils.provideReminderListViewModelFactory(requireContext(), binding.clyReminderFormFragment)

        reminderListViewModel = ViewModelProviders.of(this, factory).get(ReminderListViewModel::class.java)

        reminderModel.id = System.currentTimeMillis()-1000
        reminderModel.title = binding.edtInputTitle.text.toString()
        reminderModel.description = binding.edtInputDescription.text.toString()
        reminderModel.dateTime = binding.edtDateInput.text.toString() + " " + binding.edtTimeInput.text.toString()

        runOnBackgroundThread {
            reminderListViewModel.saveReminder(reminderModel)
        }

        requireView().findNavController().navigate(R.id.goToListFragment)
    }

    /**
     * get date and reformat after date picker was selected
     */
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val getMonth = month + 1
        val date = "$getMonth/$dayOfMonth/$year"
        val parser = SimpleDateFormat("MM/dd/yyyy")
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val formattedDate = formatter.format(parser.parse(date))

        binding.edtDateInput.setText(formattedDate)
    }

    /**
     * Open view of calendar
     */
    private fun openCalendarDialog() {
        val calendar = Calendar.getInstance()
        val yy = calendar.get(Calendar.YEAR)
        val mm = calendar.get(Calendar.MONTH)
        val dd = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_MinWidth, this, yy, mm, dd)

        datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePickerDialog.show()
    }

    /**
     * Load time dialog
     */
    private fun openTimeDialog() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog(activity, object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                binding.edtTimeInput.setText(SimpleDateFormat("kk:mm", Locale.ENGLISH).format(cal.time))
            }
        }, 0, 0, true)

        timeSetListener.show()
    }
}
