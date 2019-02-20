/*
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import insure.onoff.R

/**
 * LoRegActivity
 *
 * This class is use to view Login and Registration process
 *
 * @author    Charles S  <charlessetiadi@onoff.insure>
 */
class LoRegActivity : AppCompatActivity() {

    /**
     * Generate view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lo_reg)
    }
}
