package com.example.pruebjuan

import android.app.Application
import android.content.Context

class AppContext: Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        private lateinit var context: Context

        fun getContext() = context;
    }
}