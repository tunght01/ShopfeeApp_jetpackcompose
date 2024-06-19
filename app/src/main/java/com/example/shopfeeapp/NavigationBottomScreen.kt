package com.example.shopfeeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shopfeeapp.model.NavigationBottomScreen
import com.example.shopfeeapp.view.HistoryScreen
import com.example.shopfeeapp.view.HomeScreen
import com.example.shopfeeapp.view.ProfileScreenScreen
import com.example.shopfeeapp.viewmodel.LoginViewModel

@Composable
fun NavigationBottomScreen(
    viewModel: LoginViewModel,
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(navController = navController, startDestination = NavigationBottomScreen.HomeScreen.route){

        composable(NavigationBottomScreen.HomeScreen.route){
//            HomeScreen(navController)
        }
        composable(NavigationBottomScreen.HistoryScreen.route){
            HistoryScreen()
        }
        composable(NavigationBottomScreen.ProfileScreen.route){
//            ProfileScreenScreen()
        }
    }

}