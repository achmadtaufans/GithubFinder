/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import insure.onoff.R

/**
 * MainActivity
 *
 * This class is responsible to be as Main Activity
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

class MainActivity : AppCompatActivity() {

    //To display Main Activity layout and initialize needed variables
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

