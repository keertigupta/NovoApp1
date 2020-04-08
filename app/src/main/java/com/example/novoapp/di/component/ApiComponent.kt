package com.example.novoapp.di.component

import com.example.novoapp.di.ApiModule
import com.example.novoapp.di.AppModule
import com.example.novoapp.repository.ServerRepository
import com.example.novoapp.view.UserDetailsFragment
import com.example.novoapp.view.UsersListFragment
import com.example.novoapp.viewModel.CustomViewModelFactory
import com.example.novoapp.viewModel.UsersListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class),(AppModule::class)])
interface ApiComponent {

    fun inject(serverRepository: ServerRepository)

    fun inject(userListViewModel: UsersListViewModel)
    fun inject(userListFrag: UsersListFragment)
    fun inject(retroViewModelFactory:CustomViewModelFactory)

}
