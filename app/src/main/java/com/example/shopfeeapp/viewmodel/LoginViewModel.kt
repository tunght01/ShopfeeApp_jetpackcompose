package com.example.shopfeeapp.viewmodel

import android.app.Service
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopfeeapp.Api.ApiService
import com.example.shopfeeapp.Api.recipeService
import com.example.shopfeeapp.model.LoginRequest
import com.example.shopfeeapp.model.User
import com.example.shopfeeapp.model.UserRespone
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class LoginViewModel:ViewModel() {
    private val _userState = mutableStateOf(LoginState())
    val usersState:State<LoginState> = _userState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        Log.e("tung","success")
        viewModelScope.launch {
            try {
                val respone = recipeService.getUser()
                Log.e("tung", "${respone.size}")
                _userState.value = _userState.value.copy(
                    list = respone,
                    error = null
                )
            }catch (e:Exception){
                _userState.value = _userState.value.copy(
                    error = "Error fetching User ${e.message}"
                )
            }
        }

    }
//    private fun findUsersId() {
//        Log.e("tung","success")
//        viewModelScope.launch {
//            try {
//                val respone = recipeService.getUserId()
//
//                )
//            }catch (e:Exception){
//                _userState.value = _userState.value.copy(
//                    error = "Error fetching User ${e.message}"
//                )
//            }
//        }
//
//    }
    fun registerUser(requestBody:RequestBody, onResult:(User?) -> Unit){
        viewModelScope.launch {
            try {
                val response = recipeService.registerUser(requestBody = requestBody)
                onResult(response)

            }catch (e:Exception){
                onResult(null)

            }
        }
    }
//    fun registerUser(requestBody:RequestBody, onResult:(User?) -> Unit){
//        viewModelScope.launch {
//            try {
//                val response = recipeService.registerUser(requestBody = requestBody)
//                onResult(response)
//
//            }catch (e:Exception){
//                onResult(null)
//
//            }
//        }
//    }
    fun loginUser(loginRequest: LoginRequest, onResult: (String?, UserRespone?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = recipeService.login(loginRequest)
                val token = response.jwt
                Log.e("tung","token $token")
                val userResponse = response.user
                onResult(token,userResponse)
            } catch (e: Exception) {
                Log.e("tung","token ${e.message}")

                onResult(null,null)
            }
        }
    }


    data class LoginState(
        val list: List<User> = emptyList(),
        val error: String? = null
    )
}