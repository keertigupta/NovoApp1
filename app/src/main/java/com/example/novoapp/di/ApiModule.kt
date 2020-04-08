package com.example.novoapp.di

import android.app.Application
import com.example.novoapp.repository.ServerRepository
import com.example.novoapp.service.AddQueryInterceptor
import com.example.novoapp.service.ApiService
import com.example.novoapp.service.NetworkConnectionInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule (baseUrl:String){
    val mBaseURL =baseUrl



    @Provides
    @Singleton
    fun provideGson():Gson{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
    }
    @Provides
    fun provideQueryInterceptor(): AddQueryInterceptor {
        return AddQueryInterceptor()
    }
    @Provides
    fun provideNetworkInterceptor(application:Application): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(application)
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(hh:HttpLoggingInterceptor,aa:AddQueryInterceptor,
                            nn:NetworkConnectionInterceptor):OkHttpClient{
           val okhttpBuilder  =  OkHttpClient.Builder()
        okhttpBuilder.addInterceptor(hh)
        okhttpBuilder.addInterceptor(aa)
        okhttpBuilder.addInterceptor(nn)
        return  okhttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient,gson:Gson):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mBaseURL)
            .build()

    }

    @Provides
    @Singleton
    fun providesService(retrofit:Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    fun provideServerRepository():ServerRepository{
        return ServerRepository()
    }

}