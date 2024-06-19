package com.example.shopfeeapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route:String,
                     ){
    object LoginScreen:Screen("login_screen")
    object SignupScreen:Screen("signup_screen")
    object MainScreen:Screen("main_screen")
    object DetailScreen:Screen("detail_screen")
    object FlashScreen:Screen("flash_screen")

}
sealed class NavigationBottomScreen (val route:String,
                     val title:String,
                     val icon:ImageVector){
    object HomeScreen:NavigationBottomScreen("home_screen","Trang chủ", Icons.Default.Home)
    object HistoryScreen:NavigationBottomScreen("history_screen", "Lịch sử", Icons.Default.List)
    object ProfileScreen:NavigationBottomScreen("profile_screen","Tài khoản", Icons.Default.Person)
}
