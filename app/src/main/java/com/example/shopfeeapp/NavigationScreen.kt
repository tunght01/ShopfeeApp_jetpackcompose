package com.example.shopfeeapp
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shopfeeapp.Api.recipeService
import com.example.shopfeeapp.datastore.StoreUserEmail
import com.example.shopfeeapp.model.DetailOrderCart
import com.example.shopfeeapp.model.Drink
import com.example.shopfeeapp.model.Screen
import com.example.shopfeeapp.model.UserRespone
import com.example.shopfeeapp.view.CartScreen
import com.example.shopfeeapp.view.DetailCartItem
import com.example.shopfeeapp.view.DetailOrderCartScreen
import com.example.shopfeeapp.view.DetailScreen
import com.example.shopfeeapp.view.LoginScreen
import com.example.shopfeeapp.view.MainScreen
import com.example.shopfeeapp.view.MethodPayment
import com.example.shopfeeapp.view.SignUpScreen
import com.example.shopfeeapp.viewmodel.DetailCartViewModel
import com.example.shopfeeapp.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun Navigation(
    viewModel: LoginViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold {
        val context = LocalContext.current
        val storeUserEmail = StoreUserEmail(context)
        var isLoading by remember { mutableStateOf(true) }
        var email by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val viewModelDetail:DetailCartViewModel = viewModel()

        // Fetch email and update states
        LaunchedEffect(Unit) {
            email = storeUserEmail.fetchEmail() // Gọi hàm fetchEmail()
            isLoading = false
        }

        // Handle navigation based on email and loading state
        LaunchedEffect(email, isLoading) {
            if (!isLoading) {
                if (email == "") {
                    // Nếu email rỗng, điều hướng đến màn hình đăng nhập
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.FlashScreen.route) { inclusive = true } // Xóa hết các destination trước đó khỏi back stack
                    }
                } else {
                    // Nếu có email, điều hướng đến màn hình chính
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.FlashScreen.route) { inclusive = true } // Xóa hết các destination trước đó khỏi back stack
                    }
                }
            }
        }

        NavHost(navController = navController, startDestination =Screen.FlashScreen.route, modifier = Modifier.padding(it)) {
            composable(Screen.FlashScreen.route) {
                LoadingScreen()
            }
            composable(Screen.LoginScreen.route) {
                LoginScreen(
                    navHostController = navController,
                    onClickToSignupScreen = {
                        navController.navigate(Screen.SignupScreen.route)
                    },
                    onClickToMainScreen = {user ->
                        val currentEntry = navController.currentBackStackEntry
                        if (currentEntry != null) {
                            currentEntry.savedStateHandle.set("userres", user)
                            navController.navigate(Screen.MainScreen.route)
                        }

                    }
                )
            }
            composable(Screen.MainScreen.route) {
                val user = navController.previousBackStackEntry?.savedStateHandle?.get<UserRespone>("userres")
                MainScreen(
                    viewModel = viewModel,
                    onClickScreen = { drink ->
                        val currentEntry = navController.currentBackStackEntry
                        if (currentEntry != null) {
                            try {
                                currentEntry.savedStateHandle.set("drink", drink)
                                navController.navigate(Screen.DetailScreen.route)
                            } catch (e: Exception) {
                                Log.e("Navigation", "Failed to set savedStateHandle: ${e.message}")
                            }
                        } else {
                            Log.e("Navigation", "currentBackStackEntry is null")
                        }
                    },
                    onClickLogout = {
                        scope.launch {
                            storeUserEmail.saveEmail("")
                            email = ""
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(Screen.FlashScreen.route) { inclusive = true } // Xóa hết các destination trước đó khỏi back stack
                            }
                        }
                    },
                    onClickCart = {
                        navController.navigate("Cart")
                    }


                )
            }
            composable(Screen.SignupScreen.route) {
                SignUpScreen(
                    viewModel = viewModel,
                    navHostController = navController,
                    onClickToLoginScreen = {
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    onCickSignUp = {}
                )
            }
            composable(Screen.DetailScreen.route) {
                val drink = navController.previousBackStackEntry?.savedStateHandle?.get<Drink>("drink")
                if (drink != null) {
                    DetailScreen(drink = drink,onClickCart = {
                        scope.launch {
                            val state = recipeService.getDetailOrderUser(email)
//                            Log.e("hihi","${state.list.size}")
                            if (state.size !=0){
                                for (detailItem in state){
                                    if (detailItem.users_permissions_user == it.users_permissions_user
                                        && detailItem.drink.id == it.drink.id
                                        && detailItem.Topping == it.Topping
                                        && detailItem.Size == it.Size
                                        && detailItem.Sugarlevel == it.Sugarlevel
                                        && detailItem.Temperature == it.Temperature
                                    ){
                                        val updatedQuantity = detailItem.Quantity + it.Quantity
                                        val updatedPrice = it.drink.Price * updatedQuantity // Thay đổi nếu cần thiết
                                        val updatedDetailItem = DetailOrderCart(
                                            id = detailItem.id, // Giữ nguyên id của detailItem
                                            users_permissions_user = it.users_permissions_user,
                                            drink = it.drink,
                                            Quantity = updatedQuantity,
                                            Price = updatedPrice ,// Cập nhật giá mới
                                            Topping = it.Topping,
                                            Size = it.Size,
                                            Sugarlevel = it.Sugarlevel,
                                            Temperature = it.Temperature
                                        )
                                        viewModelDetail.updateOrderDetail(detailItem.id!!, updatedDetailItem)
                                        navController.navigate("cart")
//                                    navController.navigate("cart")
                                        break
                                    }else{
                                        viewModelDetail.addOrderDetail(it)
                                        navController.navigate("cart")
                                        break
                                    }
                                }
                            }else{
                                viewModelDetail.addOrderDetail(it)
                                navController.navigate("cart")
                            }

                        }



                    })
                } else {
                    Log.e("Navigation", "Drink data is null")
                    // Handle null state appropriately
                    // You might want to show an error screen or navigate back
                }
            }
            composable("cart") {
//                CartScreen(onClickBack = {
//                    navController.popBackStack()
//                })
                val chose = navController.previousBackStackEntry?.savedStateHandle?.get<String>("chose")
                Log.e("tung","deailscrenn composobale")
                DetailOrderCartScreen(navHostController = navController, modifier = Modifier, onClickBack = {
                    navController.popBackStack()
                },
                    onClickMethodPay = {navController.navigate("methodpayment")}, chose = chose)
            }
            composable("methodpayment") {
//
                MethodPayment(onClickBack = {navController.popBackStack()}, onClickChoseMethod = {chose ->
                    val currentEntry = navController.currentBackStackEntry
                    if (currentEntry != null) {
                        currentEntry.savedStateHandle.set("chose", chose)
                        navController.navigate("cart")
                    }
                })
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.img), contentDescription = null, Modifier.size(200.dp))
    }
}


