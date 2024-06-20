package com.example.shopfeeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id:Int,
    val username:String,
    val email:String
): Parcelable
