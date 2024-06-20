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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class DetailCartViewModel: ViewModel() {
    private val _detailOrderState = mutableStateOf(DetailOderState())
    val DetailOrderState: State<DetailOderState> = _detailOrderState

    init {
        getDetailOrder()
    }


    fun getDetailOrder() {
        Log.e("tung","success")
        viewModelScope.launch {

            try {
                val respone = recipeService.getDetailOrder()
                Log.e("tung", "${respone.size}")
//                Log.e("tung", "${respone.last().sales}")
                _detailOrderState.value = _detailOrderState.value.copy(
                    list = respone,
                    error = null,
//                    loadding = true

                )
            }catch (e:Exception){
                _detailOrderState.value = _detailOrderState.value.copy(
                    error = "Error fetching User ${e.message}"
                )
            }
        }

    }
    data class ProductState(
        val list: List<Drink> = emptyList(),
//        val loadding:Boolean = false,
        val error: String? = null
    )
    data class DetailOderState(
        val list: List<DetailOrderCart> = emptyList(),
        val error: String? = null
    )
}