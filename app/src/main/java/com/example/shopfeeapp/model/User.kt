package com.example.shopfeeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id:Int,
    val username:String,
    val email:String
): Parcelable


@Parcelize
data class LoginRequest(val identifier: String, val password: String): Parcelable

// LoginResponse.kt


@Parcelize
data class LoginResponse(
    val jwt: String,
    val user: UserResponse
): Parcelable

@Parcelize
data class UserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val provider: String,
    val confirmed: Boolean,
    val blocked: Boolean,
    val role: RoleResponse,
    val deliver_adress: String?,
    val created_at: String,
    val updated_at: String,

):Parcelable

@Parcelize
data class RoleResponse(
    val id: Int,
    val name: String,
    val description: String,
    val type: String
):Parcelable