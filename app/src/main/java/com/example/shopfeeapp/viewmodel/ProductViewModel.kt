package com.example.shopfeeapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopfeeapp.Api.recipeService
import com.example.shopfeeapp.model.Drink
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val _productState = mutableStateOf(ProductState())
    val ProductsState: State<ProductState> = _productState

    init {
        fetchProduct()
//        getDetailOrder()
    }

    private fun fetchProduct() {
        Log.e("tung","success")
        viewModelScope.launch {

            try {
                val respone = recipeService.getProduct()
                Log.e("tung", "${respone.size}")
                Log.e("tung", "${respone.last().sales}")
                _productState.value = _productState.value.copy(
                    list = respone,
                    error = null,
//                    loadding = true

                )
            }catch (e:Exception){
                _productState.value = _productState.value.copy(
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













}