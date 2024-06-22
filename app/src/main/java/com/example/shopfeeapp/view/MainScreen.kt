package com.example.shopfeeapp.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shopfeeapp.Navigation
import com.example.shopfeeapp.NavigationBottomScreen
import com.example.shopfeeapp.model.Drink

import com.example.shopfeeapp.model.NavigationBottomScreen
import com.example.shopfeeapp.model.UserRespone
import com.example.shopfeeapp.viewmodel.LoginViewModel

@Composable
fun MainScreen(viewModel: LoginViewModel,onClickScreen: (Drink) -> Unit, onClickLogout:()->Unit, onClickCart:()->Unit) {
    val navController: NavHostController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationBottomScreen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationBottomScreen.HomeScreen.route) {
                HomeScreen(navController, onClick = onClickCart, onClickDetailScreen = { onClickScreen(it) })
            }
            composable(NavigationBottomScreen.HistoryScreen.route) {
                HistoryScreen()
            }
            composable(NavigationBottomScreen.ProfileScreen.route) {
                ProfileScreenScreen(onClickRespone = {

                }, onClickContact = {

                }, onClickChangePassword = {

                }, onClickLogout = {
                    onClickLogout()
                })
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        NavigationBottomScreen.HomeScreen,
        NavigationBottomScreen.HistoryScreen,
        NavigationBottomScreen.ProfileScreen,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(backgroundColor = Color.White, contentColor = Color.Gray) {
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationBottomScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title, style = TextStyle(fontSize = 10.sp)) },
        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            if (currentDestination?.route != screen.route) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    )
}
