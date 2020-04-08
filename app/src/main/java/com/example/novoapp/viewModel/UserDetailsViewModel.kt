package com.example.novoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.novoapp.model.Posts
import com.example.novoapp.repository.ServerRepository

class UserDetailsViewModel (private val repo:ServerRepository): ViewModel() {


    fun getUsersPost(userId:Int):LiveData<List<Posts>>{
         return  repo.getUsersPost(userId)
    }

}
