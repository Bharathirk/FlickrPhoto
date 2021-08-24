package com.example.photo_app.data.di.componants

import com.example.photo_app.app.AppController
import com.example.photo_app.data.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class,
        FragmentBuildersModule::class, AppModule::class, ViewModelFactoryModule::class,
        AppViewModelModule::class, NetworkModule::class]
)
interface AppComponant : AndroidInjector<AppController> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AppController>()
}

