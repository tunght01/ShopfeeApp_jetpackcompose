package com.example.shopfeeapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopfeeapp.Api.recipeService
import com.example.shopfeeapp.model.LoginRequest
import com.example.shopfeeapp.model.PaymentMethod
import com.example.shopfeeapp.model.User
import kotlinx.coroutines.launch
import okhttp3.RequestBody


class PayMentViewModel: ViewModel() {
    private val _paymentState = mutableStateOf(PaymentState())
    val paymentState: State<PaymentState> = _paymentState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        Log.e("tung","success")
        viewModelScope.launch {
            try {
                val respone = recipeService.getPaymentMethod()
                Log.e("tung", "${respone.size}")
                _paymentState.value = _paymentState.value.copy(
                    list = respone,
                    error = null
                )
            }catch (e:Exception){
                _paymentState.value = _paymentState.value.copy(
                    error = "Error fetching User ${e.message}"
                )
            }
        }

    }
    data class PaymentState(
        val list: List<PaymentMethod> = emptyList(),
        val error: String? = null
    )
}