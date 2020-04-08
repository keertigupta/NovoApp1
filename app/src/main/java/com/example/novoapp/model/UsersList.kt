package com.example.novoapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class UsersList (val  responseData:List<ResponseData>? ,val error:Throwable?=null):Parcelable{
    constructor(error: Throwable?) :this(null,error)

}
