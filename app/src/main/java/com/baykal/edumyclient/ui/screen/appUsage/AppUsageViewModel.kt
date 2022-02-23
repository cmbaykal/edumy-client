package com.baykal.edumyclient.ui.screen.appUsage

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppUsageViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator