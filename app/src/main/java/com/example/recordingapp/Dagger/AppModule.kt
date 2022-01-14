package com.example.recordingapp.Dagger

import com.example.recordingapp.ApplicationData
import com.example.recordingapp.repository.Firestore
import com.example.recordingapp.repository.Volley.Volley
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val applicationData: ApplicationData) {

    @Provides
    @Singleton
    fun applicationManager()=applicationData

    @Provides
    @Singleton
    fun firestoreManager():Firestore{
        return Firestore(this.applicationData)
    }

    @Provides
    @Singleton
    fun volleyManager():Volley{
        return Volley(this.applicationData)
    }

}