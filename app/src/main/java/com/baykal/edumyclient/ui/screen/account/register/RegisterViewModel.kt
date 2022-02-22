package com.baykal.edumyclient.ui.screen.account.register

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {

}