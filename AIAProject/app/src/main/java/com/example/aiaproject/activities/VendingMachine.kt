package com.example.aiaproject.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.aiaproject.R
import com.example.aiaproject.databinding.ActivityVendingMachineBinding

class VendingMachine: AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityVendingMachineBinding
    private lateinit var toolbar: Toolbar
    private var balance: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_vending_machine
        )

        setupToolbar()

        binding.balance = balance.toString()

        setButton()
    }

    fun setupToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Vending Machine"
        toolbar.visibility = View.VISIBLE
    }

    private fun setButton() {
        binding.btn2k.setOnClickListener(this)
        binding.btn5k.setOnClickListener(this)
        binding.btn10k.setOnClickListener(this)
        binding.btn20k.setOnClickListener(this)
        binding.imbAqua.setOnClickListener(this)
        binding.imbTehBotol.setOnClickListener(this)
        binding.imbPocari.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
       binding.txvErrorMessage.text = ""
       binding.txvErrorMessage.setTextColor(resources.getColor(R.color.design_default_color_error))

       when (view) {
           binding.btn2k -> {
               binding.balance = addBalance(2000).toString()
           }
           binding.btn5k -> {
               binding.balance = addBalance(5000).toString()
           }
           binding.btn10k -> {
               binding.balance = addBalance(10000).toString()
           }
           binding.btn20k -> {
               binding.balance = addBalance(20000).toString()
           }
           binding.imbAqua -> {
               if (checkStock(binding.txvCurrentStockAqua.text.toString().toInt())) {
                   binding.balance = purchaseDrink(5000, binding.txvCurrentStockAqua).toString()
               }
           }
           binding.imbTehBotol -> {
               if (checkStock(binding.txvCurrentStockTehBotol.text.toString().toInt())) {
                   binding.balance = purchaseDrink(7000, binding.txvCurrentStockTehBotol).toString()
               }
           }
           binding.imbPocari -> {
               if (checkStock(binding.txvCurrentStockPocari.text.toString().toInt())) {
                   binding.balance = purchaseDrink(10000, binding.txvCurrentStockPocari).toString()
               }
           }
       }

        balance = binding.balance!!.toLong()
    }

    private fun addBalance(value: Long): Long {
        binding.txvErrorMessage.text = ""

       return balance + value
    }

    private fun purchaseDrink(price: Long, textView: TextView): Long {
        val calculateBalance = balance - price

        if (calculateBalance >= 0) {
            textView.text = (textView.text.toString().toInt() - 1).toString()

            binding.txvErrorMessage.setTextColor(resources.getColor(R.color.colorPrimary))
            binding.txvErrorMessage.text = onError("Item purchased!")

            return balance - price
        }

        binding.txvErrorMessage.text = onError("Not enough Balance!")
        return balance
    }

    private fun checkStock (stock: Int): Boolean {
        if (stock != 0) {
            return true
        }

        binding.txvErrorMessage.text = onError("Out of Stock!")
        return false
    }


    private fun onError(errorMessage: String = ""): String {
        if (errorMessage.isNotEmpty()) {
            return errorMessage
        }

        return ""
    }

    /**
     * To make user go back when user click back arrow on top toolbar
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * To make user go to previous fragment when click back
     */
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}