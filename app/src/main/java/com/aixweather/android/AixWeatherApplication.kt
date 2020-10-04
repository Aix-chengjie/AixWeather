package com.aixweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class AixWeatherApplication : Application() {
    //全局获取Context的办法
    companion object {
        //获取的是Application中的Context，全局只会存在一份实例，并且
        // 整个应用生命周期内都不会回收，因此不存在内存泄漏风险，所以使用以下注解
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "auv03EiB3Zt9RZcn"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}