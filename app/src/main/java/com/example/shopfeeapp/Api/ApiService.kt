package com.example.shopfeeapp.Api


import com.example.shopfeeapp.model.DetailOrderCart
import com.example.shopfeeapp.model.Drink
import com.example.shopfeeapp.model.LoginRequest
import com.example.shopfeeapp.model.LoginResponse
import com.example.shopfeeapp.model.PaymentMethod
import com.example.shopfeeapp.model.User
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//val api:String = "192.168.0.107"
val api:String = "192.168.0.107"
private val retrofit = Retrofit.Builder().baseUrl("http://$api:1337/").addConverterFactory(GsonConverterFactory.create()).build()
val recipeService = retrofit.create(ApiService::class.java)
interface ApiService{
    @GET("users")
    suspend fun getUser():List<User>
    @GET("users/{id}")
    suspend fun getUserId(
        @Path("id") id: Int, // Thêm Path parameter cho id
    ): User

    @GET("Drinks")
    suspend fun getProduct():List<Drink>

    @POST("auth/local/register")
    suspend fun registerUser(@Body requestBody: RequestBody):User
    @POST("Detailodercarts")
    suspend fun addOrderDetail(@Body detailOrderCart: DetailOrderCart): Response<Unit>
    @PUT("Detailodercarts/{id}")
    suspend fun updateOrderDetail(
        @Path("id") id: Int, // Thêm Path parameter cho id
        @Body detailOrderCart: DetailOrderCart
    ): Response<Unit>
//    @GET("Detailodercarts")
//    suspend fun getDetailOrder():List<DetailOrderCart>

    @GET("Detailodercarts")
    suspend fun getDetailOrderUser(
        @Query("users_permissions_user.username") username: String,
    ):List<DetailOrderCart>

    @POST("/auth/local")
    suspend fun login(@Body loginRequest:LoginRequest):LoginResponse
    @GET("Payment-Methods")
    suspend fun getPaymentMethod():List<PaymentMethod>
//    @GET("Detailodercarts")
//    suspend fun getDetailodercartsUsername():List<DetailOrderCart>
}


