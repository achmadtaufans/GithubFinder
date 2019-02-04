package insure.onoff.utilities

import android.content.Context
import insure.onoff.data.repository.ProfileRepository
import insure.onoff.data.repository.sample.SampleRepository
import insure.onoff.viewmodels.ProfileViewModelFactory
import insure.onoff.viewmodels.sample.SampleViewModelFactory

object InjectorUtils {
    private fun getProfileRepository(context: Context): ProfileRepository {
        return ProfileRepository();
    }

    fun provideProfileViewModelFactory(context: Context): ProfileViewModelFactory {
        val repository = getProfileRepository(context)
        return ProfileViewModelFactory(repository)
    }

    private fun getSampleRepository(context: Context): SampleRepository {
        return SampleRepository();
    }

    fun provideSampleViewModelFactory(context: Context): SampleViewModelFactory {
        val repository = SampleRepository()
        return SampleViewModelFactory(repository)
    }
}
