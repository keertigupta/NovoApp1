package com.example.novoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.novoapp.di.component.ApiComponent
import com.example.novoapp.repository.ServerRepository
import com.example.novoapp.view.NovoApplication
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory  :ViewModelProvider.Factory {

    @Inject
    lateinit var repo: ServerRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        var apiComponent :ApiComponent =  NovoApplication.apiComponent
        apiComponent.inject(this)
          return when {
            modelClass.isAssignableFrom(UsersListViewModel::class.java) -> {
                UsersListViewModel(repo) as T
            }
            modelClass.isAssignableFrom(UserDetailsViewModel::class.java) -> {
                UserDetailsViewModel(repo) as T
            }
              modelClass.isAssignableFrom(PostCommentsViewModel::class.java) -> {
                  PostCommentsViewModel(repo) as T
              }

              else -> {
                  throw IllegalArgumentException("Unknown ViewModel class")
              }
        }
    }

}