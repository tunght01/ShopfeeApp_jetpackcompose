package com.example.shopfeeapp

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.rememberNavController

import com.example.shopfeeapp.ui.theme.ShopfeeAppTheme
import com.example.shopfeeapp.view.CartScreen
import com.example.shopfeeapp.view.DetailScreen
import com.example.shopfeeapp.view.MainScreen
import com.example.shopfeeapp.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var viewModel:LoginViewModel = viewModel()
            var navController = rememberNavController()
            ShopfeeAppTheme {
                Navigation(viewModel = viewModel, navController = navController, modifier = Modifier)
//                MainScreen(viewModel = viewModel){}
//                DetailScreen(drink = )
//                CartScreen()
            }
        }
    }
}

