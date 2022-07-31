package com.example.ccstestapp

import android.app.Application
import com.example.ccstestapp.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import com.example.ccstestapp.di.DaggerAppComponent
import javax.inject.Inject

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        component = DaggerAppComponent.builder()
            .setContext(this)
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}