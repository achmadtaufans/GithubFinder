/**
 * @copyright ©2018 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core

import android.app.Application
import android.content.Context

/**
 * OnOffApplication
 *
 * This class is responsible to manage Android Application
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class OnOffApplication : Application() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        fun getContext() : Context = this.getContext()
    }

    fun getContext(): Context {
        return context
    }

}