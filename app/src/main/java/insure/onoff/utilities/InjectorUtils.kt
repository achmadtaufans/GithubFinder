/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.utilities

import android.content.Context
import insure.onoff.data.repository.AuthRepository
import insure.onoff.data.repository.sample.SampleRepository
import insure.onoff.viewmodels.AuthViewModelFactory
import insure.onoff.viewmodels.sample.SampleViewModelFactory

/**
 * InjectorUtils
 *
 * This class is responsible to inject some objects
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */


object InjectorUtils {
    //To get auth repository. Context is later required for calling SQLite database
    private fun getAuthRepository(context: Context): AuthRepository {
        return AuthRepository();
    }

    //To provide Auth View Model Factory with repository param
    fun provideAuthViewModelFactory(context: Context): AuthViewModelFactory {
        val repository = getAuthRepository(context)
        return AuthViewModelFactory(repository)
    }

    //To get sample repository. Basically it needs Context parameter when getting data from SQLite / persistent data
    private fun getSampleRepository(context: Context): SampleRepository {
        return SampleRepository();
    }

    //To provide Sample View Model Factory with repository param
    fun provideSampleViewModelFactory(context: Context): SampleViewModelFactory {
        val repository = SampleRepository()
        return SampleViewModelFactory(repository)
    }
}
