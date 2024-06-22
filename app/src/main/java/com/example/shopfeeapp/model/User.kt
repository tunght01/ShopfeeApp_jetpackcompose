package com.example.shopfeeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id:Int,
    val username:String,
    val email:String,
    val detailodercarts:DetailOrderCart?
): Parcelable


//@Parcelize
//data class LoginRequest(val identifier: String, val password: String): Parcelable
//@Parcelize
data class LoginRequest(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("password")
    val password: String)
//    : Parcelable


// LoginResponse.kt


data class LoginResponse(
    @SerializedName("jwt")
    val jwt: String,
    @SerializedName("user") val user: UserRespone
)
//@Parcelize
//data class LoginResponse(
//    val jwt: String,
//    val user: UserResponse
//): Parcelable

@Parcelize
data class UserRespone(
    val id: Int,
    val username: String,
    val email: String,
    val provider: String,
    val confirmed: Boolean,
    val blocked: Boolean,
    val role: Role?,
    // Other fields like deliver_adress, created_at, updated_at, rates, detailodercarts, orders
):Parcelable

@Parcelize
data class Role(
    val id: Int,
    val name: String,
    val description: String,
    val type: String,
):Parcelable