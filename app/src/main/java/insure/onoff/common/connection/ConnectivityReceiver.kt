/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */

package insure.onoff.common.connection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import insure.onoff.core.OnOffApplication

/**
 * ConnectivityReceiver Class.
 *
 * This class is responsible to manage internet connection
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

class ConnectivityReceiver : BroadcastReceiver() {

    //To check internet connection automatically. This method detects Receiver tag in Manifest
    override fun onReceive(context: Context, arg1: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnected
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.onNetworkConnectionChanged(isConnected)
        }
    }

    //To provide network connection events
    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    //To provide checking internet connection in single variable
    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null

        val isConnected: Boolean
            get() {
                val connectivityManager = OnOffApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = connectivityManager.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnected
            }
    }

}