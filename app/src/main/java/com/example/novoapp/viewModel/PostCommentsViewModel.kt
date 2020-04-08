package com.example.novoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.novoapp.model.Comments
import com.example.novoapp.model.Posts
import com.example.novoapp.repository.ServerRepository

class PostCommentsViewModel(private val repo: ServerRepository) : ViewModel() {

    fun getPostComments(userId:Int): LiveData<List<Comments>> {
        return  repo.getPostComment(userId)
    }
}
