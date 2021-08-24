package com.example.photo_app.data.di.modules

import android.content.Context
import com.example.photo_app.app.AppController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun providesContext(): Context {
        return AppController.getInstance()!!.applicationContext
    }




}