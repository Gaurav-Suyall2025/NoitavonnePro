package com.suyal.noitavonnepro

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

/**
 * Created by Gaurav Suyal on 28,January,2023
 */
class MainApplication : Application() {


    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
        )

        context = applicationContext
    }
}