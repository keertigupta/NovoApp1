package com.example.novoapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseData (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("username") val username : String,
    @SerializedName("email") val email : String,
    @SerializedName("address") val address : Address,
    @SerializedName("phone") val phone : String,
    @SerializedName("website") val website : String,
    @SerializedName("company") val company : Company
):Parcelable