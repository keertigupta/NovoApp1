package com.example.novoapp.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor (var mContext: Context) : Interceptor {


    fun  isConnected():Boolean{
        var connectivityManager =  mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var netInfo = connectivityManager.getActiveNetworkInfo() as NetworkInfo
        return (netInfo != null && netInfo.isConnected());
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isConnected()){
            throw  NoConnectivityException()
        }
        var builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}