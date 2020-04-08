package com.example.novoapp.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.example.novoapp.R
import com.example.novoapp.model.Comments
import com.example.novoapp.model.Posts
import com.example.novoapp.model.UsersList
import com.example.novoapp.viewModel.CustomViewModelFactory
import com.example.novoapp.viewModel.PostCommentsViewModel
import com.example.novoapp.viewModel.UsersListViewModel
import kotlinx.android.synthetic.main.user_details_fragment.*

class PostCommentsFragment : Fragment() {


    private lateinit var viewModel: PostCommentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_comments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var post = arguments!!.getParcelable<Posts>("POST")
        initViewModel()
        fetchRetroInfo(post!!.id)
    }

    fun  initViewModel(){
        val customViewModelFactory = CustomViewModelFactory()
        viewModel = ViewModelProviders.of(this,customViewModelFactory).get(PostCommentsViewModel::class.java)
    }

    private fun fetchRetroInfo(postId:Int) {
        viewModel.getPostComments(postId).observe(viewLifecycleOwner,object:Observer<List<Comments>>{
            override fun onChanged(t: List<Comments>?) {
                    Toast.makeText(activity,t.toString(),Toast.LENGTH_LONG).show()
            }

        })
    }

}
