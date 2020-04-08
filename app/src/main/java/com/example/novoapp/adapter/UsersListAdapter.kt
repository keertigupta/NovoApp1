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
import com.example.novoapp.model.ResponseData
import com.example.novoapp.model.UsersList

import java.util.*


class UsersListAdapter(
    var context: Context,
    private val mItemClickLis:OnClickItemListner) : RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {
    private var projectArrayList: List<ResponseData>  = emptyList<ResponseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view,mItemClickLis)
    }
    fun setAdapterList(list: List<ResponseData> ){
        projectArrayList = list
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tv_userId.text = projectArrayList[position].id.toString()
        holder.tv_userEmail.text =projectArrayList[position].email
        holder.tv_userName.text = projectArrayList[position].username

    }

    override fun getItemCount(): Int {
        return projectArrayList.size
    }

    inner class MyViewHolder(itemView: View,var onClickItemListner:OnClickItemListner)
        : ViewHolder(itemView),View.OnClickListener {


        var tv_userId :TextView = itemView.findViewById<View>(R.id.tv_userId) as TextView
        var tv_userName:TextView
        var tv_userEmail:TextView
        init {
            tv_userName = itemView.findViewById(R.id.tv_userName)
            tv_userEmail = itemView.findViewById(R.id.tv_userEmail)
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