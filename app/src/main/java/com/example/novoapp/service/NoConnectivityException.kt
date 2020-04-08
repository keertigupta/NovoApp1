package com.example.novoapp.service

import java.io.IOException
import java.lang.Exception
class NoConnectivityException : IOException(){
    override fun getLocalizedMessage(): String? {
        return    return "No Internet Connection";
    }
}