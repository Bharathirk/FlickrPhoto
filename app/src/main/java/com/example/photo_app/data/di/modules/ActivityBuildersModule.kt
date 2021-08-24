package com.example.photo_app.data.di.modules

import com.example.photo_app.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuildersModule {

    @ContributesAndroidInjector
    fun contributeSplashActivity(): MainActivity


}