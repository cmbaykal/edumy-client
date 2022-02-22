package com.baykal.edumyclient.ui.screen.classroomSection.classrooms

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassroomsViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator