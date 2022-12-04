package com.dudu.ledger.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.litepal.LitePal

class MyContext : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        LitePal.initialize(context)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}