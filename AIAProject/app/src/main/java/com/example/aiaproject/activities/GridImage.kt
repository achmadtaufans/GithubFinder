package com.example.aiaproject.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.aiaproject.R
import com.example.aiaproject.adapter.ImageAdapter


class GridImage: AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_list)

        setupToolbar()

        val gridview = findViewById<GridView>(R.id.gridview)

        val adapter = ImageAdapter(this,
            R.layout.item_image
        )

        gridview.adapter = adapter

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            val intent = Intent(this, ImageDetail::class.java)

            when (position) {
                0 ->  {
                    intent.putExtra("product_name", "Aqua")
                }
                1 -> {
                    intent.putExtra("product_name", "Teh Botol")
                }
                2 -> {
                    intent.putExtra("product_name", "Pocari Sweat")
                }
            }
            startActivity(intent)
            finish()
        }
    }

    fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Product"
        toolbar.visibility = View.VISIBLE
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