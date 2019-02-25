/*
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import insure.onoff.R
import insure.onoff.core.events.ShowToolbarEvent
import insure.onoff.core.events.UpdateToolbarNameEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * LoRegActivity
 *
 * This class is use to view Login and Registration process
 *
 * @author    Charles S  <charlessetiadi@onoff.insure>
 */
class LoregActivity : AppCompatActivity() {
    private val TAG = LoregActivity::class.java.getName()
    private lateinit var toolbar: Toolbar

    /**
     * To display LoReg Activity layout and initialize toolbar.
     * EventBus is needed to make this activity can change toolbar title when this contains different fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loreg)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.visibility = View.GONE
        EventBus.getDefault().register(this)
    }

    /**
     * To make user go back when user click back arrow on top toolbar
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * To make user go to previous fragment when click back
     */
    override fun onBackPressed() {
        val fm = supportFragmentManager

        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Unregister subscriber (this activity is subscriber)
     */
    override fun onDestroy() {
        super.onDestroy()
        try {
            EventBus.getDefault().unregister(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * To handle toolbar name change. So, when go to new fragment. Fragment will send information to subscriber and this will handle the information
     */
    @Subscribe
    fun changeToolbarName(updateToolbarNameEvent: UpdateToolbarNameEvent) {
        supportActionBar?.setTitle(updateToolbarNameEvent.titleToolbar)
    }

    /**
     * To handle toolbar visibility. So, when go to new fragment. Fragment will send information to subscriber and this will handle the information
     */
    @Subscribe
    fun showToolbar(showToolbarEvent: ShowToolbarEvent) {
        when (showToolbarEvent.isShow) {
            true -> {
                toolbar.visibility = View.VISIBLE
            }
            false -> {
                toolbar.visibility = View.GONE
            }
        }
    }
}
