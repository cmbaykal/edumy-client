package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.nav.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator