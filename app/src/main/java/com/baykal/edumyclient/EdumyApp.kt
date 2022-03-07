package com.baykal.edumyclient

import android.app.Application
import com.baykal.edumyclient.base.nav.EdumyController
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EdumyApp : Application() {

    companion object {
        val screenController: EdumyController = EdumyController()
    }

}