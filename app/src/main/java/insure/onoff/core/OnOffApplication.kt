/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core

import android.app.Application
import android.content.Context

/**
 * OnOffApplication
 *
 * This class is responsible to manage Android Application. Android Application is called first time before activity called.
 * Application can contain Application Context provider, Dagger app component provider.
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class OnOffApplication : Application() {

    //To initialize needed variable in whole activities
    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    //To get Application Context from single object. So all classes can get application context
    companion object {
        lateinit var instance: OnOffApplication

        fun getContext(): Context? {
            return instance.applicationContext
        }
    }
}
