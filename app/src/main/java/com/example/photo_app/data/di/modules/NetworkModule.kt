package com.example.photo_app.data.di.modules

import com.example.photo_app.data.api.ApiConstants
import com.example.photo_app.data.api.ApiServiceGenerator
import com.example.photo_app.data.api.AppApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.photo_app.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Provides
    @Singleton
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }



    @Provides
    @Singleton
    internal fun provideApi(
        gson: Gson, loggingInterceptor: HttpLoggingInterceptor): AppApi {
        return ApiServiceGenerator.generate(
               ApiConstants.BASE_URL
            , AppApi::class.java, gson, loggingInterceptor)

    }
}
