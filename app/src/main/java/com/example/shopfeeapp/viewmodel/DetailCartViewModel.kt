package com.example.shopfeeapp.viewmodel


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopfeeapp.Api.recipeService
import com.example.shopfeeapp.model.DetailOrderCart
import com.example.shopfeeapp.model.Drink
import com.example.shopfeeapp.model.User
import com.example.shopfeeapp.repository.OrderRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.RequestBody

//class DetailCartViewModel(private val orderRepository: OrderRepository): ViewModel() {
class DetailCartViewModel(): ViewModel() {
    private val _detailOrderState = mutableStateOf(DetailOderState())
    val DetailOrderState: State<DetailOderState> = _detailOrderState

    init {
        getDetailOrder()
    }


    fun getDetailOrder() {
        Log.e("tung","success")
        viewModelScope.launch {
            try {
                val response = recipeService.getDetailOrder()
                Log.e("tung","getdetailorder ${response.size}")
                _detailOrderState.value = _detailOrderState.value.copy(
                    list = response,
                    error = null
                )
            } catch (e: Exception) {
                Log.e("detail", "${e.message}")
                _detailOrderState.value = _detailOrderState.value.copy(
                    error = "Error fetching order details: ${e.message}"
                )
            }
        }

    }
    fun addOrderDetail(detailOrderCart: DetailOrderCart) {
        viewModelScope.launch {
            val response = recipeService.addOrderDetail(detailOrderCart)
        }
    }

    data class DetailOderState(
        val list: List<DetailOrderCart> = emptyList(),
        val error: String? = null
    )
}