package com.example.novoapp.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersList ( val id : Int,
                      val name : String,
                      val username : String,
                      val email : String ,val error:Throwable?):Parcelable
