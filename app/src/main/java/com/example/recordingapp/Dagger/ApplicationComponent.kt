package com.example.recordingapp.Dagger

import com.example.recordingapp.ApplicationData
import com.example.recordingapp.MainActivity
import com.example.recordingapp.repository.Volley.Volley
import com.example.recordingapp.repository.Firestore
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class] )
interface ApplicationComponent {

    fun inject(applicationData: ApplicationData)
    fun inject(mainActivity: MainActivity)
    fun Volley():Volley
    fun inject(firestore: Firestore)
    fun Firestore(): Firestore

}