package com.example.novoapp.view

import android.app.Application
import android.content.Context
import com.example.novoapp.di.ApiModule
import com.example.novoapp.di.AppModule
import com.example.novoapp.di.component.ApiComponent
import com.example.novoapp.di.component.DaggerApiComponent
import com.example.novoapp.service.APIURL


class NovoApplication :Application() {

    companion object {
        var ctx: Context? = null
        lateinit var apiComponent:ApiComponent
    }

    //lateinit var mapiModule: ApiComponent
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()
    }
    fun getMyComponent(): ApiComponent {
        return apiComponent
    }

    fun initDaggerComponent():ApiComponent{
        apiComponent =   DaggerApiComponent
            .builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule(APIURL.BASE_URL))
            .build()

        return  apiComponent

    }

}