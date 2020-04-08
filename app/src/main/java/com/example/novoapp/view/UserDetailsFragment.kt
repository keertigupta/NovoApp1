package com.example.novoapp.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novoapp.R
import com.example.novoapp.adapter.PostListAdapter
import com.example.novoapp.model.Posts
import com.example.novoapp.model.UsersList
import com.example.novoapp.viewModel.CustomViewModelFactory
import com.example.novoapp.viewModel.UserDetailsViewModel
import kotlinx.android.synthetic.main.user_details_fragment.*

class UserDetailsFragment : Fragment(),PostListAdapter.OnClickItemListner {


    private lateinit var postList: List<Posts>
    private lateinit var postAdapter: PostListAdapter
    private lateinit var viewModel: UserDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        val customViewModelFactory = CustomViewModelFactory()
        viewModel = ViewModelProviders.of(this,customViewModelFactory).get(UserDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var user = arguments!!.getParcelable<UsersList>("User")
        user?.let {
            user_name.text = it.name
            user_email.text = it.email
            user_id.text = it.id.toString()
        }
        initAdapter()
        setAdapter()
        fetchRetroInfo(user!!.id)
    }

    private fun fetchRetroInfo(userId:Int) {
        viewModel.getUsersPost(userId).observe(viewLifecycleOwner,
            Observer<List<Posts>> {
                    t -> postAdapter.setPostListAdapter(t)
                   postList = t

            })
    }

    private fun setAdapter() {
        rv_list_post?.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            adapter =postAdapter
        }
    }

    private fun initAdapter() {
        postAdapter = PostListAdapter(activity!!,this@UserDetailsFragment)

    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("POST",postList[position])
        val navController = Navigation.findNavController(activity!!, R.id.my_nav_host_fragment);
        navController.navigate(R.id.comments_action, bundle)
    }


}
