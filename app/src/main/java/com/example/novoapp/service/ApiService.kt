package com.example.novoapp.service

import com.example.novoapp.model.Comments
import com.example.novoapp.model.Posts
import com.example.novoapp.model.ResponseData
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
//https://jsonplaceholder.typicode.com/users

    @GET("users")
    fun callApiForUsers(): Call<List<ResponseData>>

    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    fun getAllPostByUsers(@Query("userId")userId:Int):Call<List<Posts>>

    //https://jsonplaceholder.typicode.com/posts/1/comments
    @GET("posts/{post_id}/comments")
    fun getAllCommentsOfPost(@Path("post_id") id:Int):Call<List<Comments>>
}
