package com.example.aiaproject.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aiaproject.R
import com.example.aiaproject.databinding.MainActivityBinding

class MainActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.main_activity
        )

        setOnClick()
    }

    private fun setOnClick() {
        binding.btnProductList.setOnClickListener(this)
        binding.btnVendingMachine.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var intent: Intent? = null
        when (view) {
            binding.btnVendingMachine -> {
                intent = Intent(this, VendingMachine::class.java)
            }
            binding.btnProductList -> {
                intent = Intent(this, GridImage::class.java)
            }
        }

        startActivity(intent)
        finish()
    }
}