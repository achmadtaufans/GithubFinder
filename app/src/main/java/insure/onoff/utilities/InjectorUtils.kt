/**
 * @copyright ©2018 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
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
    private fun getAuthRepository(context: Context): AuthRepository {
        return AuthRepository();
    }

    fun provideAuthViewModelFactory(context: Context): AuthViewModelFactory {
        val repository = getAuthRepository(context)
        return AuthViewModelFactory(repository)
    }

    private fun getSampleRepository(context: Context): SampleRepository {
        return SampleRepository();
    }

    fun provideSampleViewModelFactory(context: Context): SampleViewModelFactory {
        val repository = SampleRepository()
        return SampleViewModelFactory(repository)
    }
}
