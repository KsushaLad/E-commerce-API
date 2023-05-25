package com.ksusha.e_commerceapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ECommerceApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}