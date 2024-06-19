package com.example.shopfeeapp.Api


import com.example.shopfeeapp.model.Drink
import com.example.shopfeeapp.model.User
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

val api:String = "192.168.0.107"
private val retrofit = Retrofit.Builder().baseUrl("http://$api:1337/").addConverterFactory(GsonConverterFactory.create()).build()
val recipeService = retrofit.create(ApiService::class.java)
interface ApiService{
    @GET("users")
    suspend fun getUser():List<User>

    @GET("Drinks")
    suspend fun getProduct():List<Drink>

    @POST("auth/local/register")
    suspend fun registerUser(@Body requestBody: RequestBody):User

}


