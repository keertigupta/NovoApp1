package com.example.novoapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (application: Application){

    var mApplication:Application = application

    @Singleton
    @Provides
    fun provideApplication():Application{
        return mApplication
    }
}