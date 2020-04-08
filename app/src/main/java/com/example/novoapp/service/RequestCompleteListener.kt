package com.example.novoapp.service


interface RequestCompleteListener<T> {
    fun onRequestSuccess(data: T)
    fun onRequestFailed(errorMessage: String)
}