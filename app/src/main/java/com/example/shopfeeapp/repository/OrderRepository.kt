package com.example.shopfeeapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.shopfeeapp.Api.ApiService
import com.example.shopfeeapp.Api.recipeService
import com.example.shopfeeapp.model.DetailOrderCart
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

class OrderRepository(private  val ApiService:ApiService) {
    suspend fun getOrderDetail(): List<DetailOrderCart> {
        return try {
            Log.e("detail","hihihi")
            ApiService.getDetailOrder()
        } catch (e: Exception) {

            // Xử lý ngoại lệ và trả về danh sách rỗng nếu có lỗi
            Log.e("OrderRepository", "Exception fetching order details", e)
            emptyList()
        }
    }
    suspend fun addOrderDetail(detailOrderCart: DetailOrderCart) {
        try {
            val response = ApiService.addOrderDetail(detailOrderCart)
            if (response.isSuccessful) {
                // Xử lý khi thêm chi tiết đơn hàng thành công
                Log.d("OrderRepository", "Order detail added successfully")
            } else {
                // Xử lý lỗi khi thêm chi tiết đơn hàng
                Log.e("OrderRepository", "Error adding order detail: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            // Xử lý lỗi ngoại lệ
            Log.e("OrderRepository", "Exception adding order detail", e)
        }
    }
}

//fun register(){
//    if (password.equals(confirmpassword)){
//        val json = JSONObject().apply {
//            put("username", userName)
//            put("email", emailValue)
//            put("password", password)
//        }
//        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())
//        viewModel.registerUser(requestBody) {
//            onClickToLoginScreen()
//
//        }
//        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
//    }
//
//}