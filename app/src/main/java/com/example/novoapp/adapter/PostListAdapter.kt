package com.example.novoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.novoapp.R
import com.example.novoapp.model.Posts
import com.example.novoapp.model.UsersList

import java.util.*


class PostListAdapter(
    var context: Context,
    private val mItemClickLis:OnClickItemListner
) : RecyclerView.Adapter<PostListAdapter.MyViewHolder>() {
    private var postArrayList:List<Posts> = emptyList<Posts>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return MyViewHolder(view,mItemClickLis)
    }

    fun setPostListAdapter(postList:List<Posts>){
        postArrayList = postList
        notifyDataSetChanged()

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tv_userId.text = postArrayList[position].id.toString()
        holder.tv_userDescription.text =postArrayList[position].body
        holder.tv_postTitle.text = postArrayList[position].title

    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }

    inner class MyViewHolder(itemView: View,var onClickItemListner:OnClickItemListner)
        : ViewHolder(itemView),View.OnClickListener {


        var tv_userId :TextView = itemView.findViewById<View>(R.id.tv_userId) as TextView
        var tv_postTitle:TextView =itemView.findViewById(R.id.tv_title) as TextView
        var tv_userDescription:TextView =itemView.findViewById(R.id.tv_description) as TextView
        init {

            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            onClickItemListner.onItemClick(adapterPosition)
        }


    }
    interface  OnClickItemListner{
        fun onItemClick(position:Int)
    }

}