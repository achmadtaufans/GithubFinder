/*
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *               Any dissemination outside of Onoff Insurance is strictly prohibited.
 */

package insure.onoff.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import insure.onoff.R

/**
 * SplashActivity
 *
 * This class is use to view splash screen and do background check
 *
 * @author    Charles S  <charlessetiadi@onoff.insure>
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
