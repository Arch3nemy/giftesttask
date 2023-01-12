package com.alacrity.giftesttask

import android.app.Application
import com.alacrity.giftesttask.di.ApiModule
import com.alacrity.giftesttask.di.AppComponent
import com.alacrity.giftesttask.di.AppModule
import com.alacrity.giftesttask.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent
            .builder()
            .apiModule(ApiModule("https://api.giphy.com/"))
            .appModule(AppModule(this))
            .build()
            .apply { inject(this@App) }
    }
}