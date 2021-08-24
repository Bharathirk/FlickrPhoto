package com.example.photo_app.app

import com.example.photo_app.data.di.componants.DaggerAppComponant
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AppController : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponant.builder().create(this)
    }

    companion object {
        private var appController: AppController? = null

        @Synchronized
        fun getInstance(): AppController? {
            return appController
        }
    }

    override fun onCreate() {
        super.onCreate()
        appController = this

    }

}