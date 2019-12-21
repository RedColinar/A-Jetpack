package com.opq.a.jetpack.ui

import android.app.Application
import androidx.room.Room
import com.facebook.stetho.Stetho
import com.opq.a.jetpack.db.JetpackDatabase

/**
 * Created by panqiang at 2019-12-21
 */
class MyApplication : Application() {
    companion object {
        lateinit var app: Application
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        JetpackDatabase.instance()
        Stetho.initializeWithDefaults(this)
    }
}