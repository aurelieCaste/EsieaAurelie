package com.example.benevoletrack

import android.app.Application
import android.content.Context

class MarqueApplication : Application() {
    companion object {
        var context: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}