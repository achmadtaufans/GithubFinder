package insure.onoff.core

import android.app.Application
import android.content.Context

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