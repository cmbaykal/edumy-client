package com.baykal.edumyclient.di

import android.app.Application
import com.baykal.edumyclient.base.preference.EdumySession
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideSession(
        application: Application,
        gson: Gson
    ) = EdumySession(application, gson)
}