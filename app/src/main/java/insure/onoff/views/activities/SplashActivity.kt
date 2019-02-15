/*
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *               Any dissemination outside of Onoff Insurance is strictly prohibited.
 */

package insure.onoff.views.activities

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import insure.onoff.R
import insure.onoff.utilities.ANIMATION_DURATION
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * SplashActivity
 *
 * This class is use to view splash screen and do background check
 *
 * @author    Charles S  <charlessetiadi@onoff.insure>
 */

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, ANIMATION_DURATION)
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}
