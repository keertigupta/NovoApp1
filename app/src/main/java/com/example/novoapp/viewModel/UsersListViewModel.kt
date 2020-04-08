package com.example.novoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.novoapp.model.UsersList
import com.example.novoapp.repository.ServerRepository
import javax.inject.Inject

class UsersListViewModel (private val serverRepository: ServerRepository) : ViewModel() {

    val progressBarLiveData = MutableLiveData<Boolean>()
    // here we can also inject WeatherRepo by Dagger in constructor of MainActivityViewModel
    //private val serverRepository: ServerRepository = ServerRepository()
   /* init {
        this.retrofitRepository  = retrofitRepository
        fetchPostInfoFromRepository()
    }*/
    fun getUsersData(): LiveData<List<UsersList>> {
        // progressBarLiveData.postValue(true)
    return serverRepository.getUsersData()

    }
}
