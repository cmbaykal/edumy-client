package com.baykal.edumyclient.di

import com.baykal.edumyclient.base.nav.EdumyNavigator
import com.baykal.edumyclient.base.nav.RouteNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = EdumyNavigator()
}