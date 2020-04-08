package com.example.novoapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novoapp.R
import com.example.novoapp.adapter.PostListAdapter
import com.example.novoapp.adapter.UsersListAdapter
import com.example.novoapp.model.UsersList
import com.example.novoapp.repository.ServerRepository
import com.example.novoapp.service.ApiService
import com.example.novoapp.viewModel.CustomViewModelFactory
import com.example.novoapp.viewModel.UsersListViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import javax.inject.Inject


class UsersListFragment : Fragment(),UsersListAdapter.OnClickItemListner {

    private lateinit var userList: List<UsersList>
    private lateinit var usersAdapter: UsersListAdapter
    private lateinit var viewModel: UsersListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        setAdapter()
        fetchRetroInfo()

    }

    private fun fetchRetroInfo() {
        viewModel.getUsersData().observe(viewLifecycleOwner, Observer<List<UsersList>> {
            it?.apply {
                userList = it
                usersAdapter.setAdapterList(it)
            }
        }
        )
    }

    private fun setAdapter(){
        rv_list?.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            rv_list.adapter =usersAdapter
        }

    }
    fun  initViewModel(){
        val customViewModelFactory = CustomViewModelFactory()
        viewModel = ViewModelProviders.of(this,customViewModelFactory).get(UsersListViewModel::class.java)
    }
    private fun initAdapter(){
        usersAdapter = UsersListAdapter(activity!!,this)

    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("User",userList[position])
        val navController = Navigation.findNavController(activity!!, R.id.my_nav_host_fragment);
        navController.navigate(R.id.post_action, bundle)
    }

}
