package com.example.aiaproject.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.aiaproject.R
import com.example.aiaproject.databinding.ActivityImageDetailBinding


class ImageDetail: AppCompatActivity() {
    private lateinit var binding: ActivityImageDetailBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_image_detail
        )

        setupToolbar()

        setUi()
    }

    fun setupToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Product Detail"
        toolbar.visibility = View.VISIBLE
    }

    private fun setUi() {
        when (intent.getStringExtra("product_name")) {
            "Aqua" -> {
                binding.txvProductNameValue.setText(intent.getStringExtra("product_name"))
                binding.imageView.setImageResource(R.drawable.img_aqua)
                binding.txvPriceValue.setText("5000")
            }
            "Teh Botol" -> {
                binding.txvProductNameValue.setText(intent.getStringExtra("product_name"))
                binding.imageView.setImageResource(R.drawable.img_tehbotol)
                binding.txvPriceValue.setText("7000")
            }
            "Pocari Sweat" -> {
                binding.txvProductNameValue.setText(intent.getStringExtra("product_name"))
                binding.imageView.setImageResource(R.drawable.img_pocari)
                binding.txvPriceValue.setText("10000")
            }
        }
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
        val intent = Intent(this, GridImage::class.java)
        startActivity(intent)
        finish()
    }
}