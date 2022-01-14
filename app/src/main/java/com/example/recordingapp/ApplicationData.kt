package com.example.recordingapp

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.recordingapp.Dagger.AppModule
import com.example.recordingapp.Dagger.ApplicationComponent
import com.example.recordingapp.Dagger.DaggerApplicationComponent
import dagger.Component

class ApplicationData: MultiDexApplication() {

    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var applicationData: ApplicationData

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        applicationData = this
        applicationComponent = DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
        applicationComponent.inject(this)
    }
    fun getApplicationComponent():ApplicationComponent{
        return this.applicationComponent
    }
}