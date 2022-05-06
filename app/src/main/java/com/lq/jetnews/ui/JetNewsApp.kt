package com.lq.jetnews.ui

import androidx.compose.material.ModalDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lq.jetnews.JetnewsDestinations

@Composable
fun JetNewApp() {

    val navController = rememberNavController()
    //获取navigation栈顶的entry
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: JetnewsDestinations.HOME_ROUTE


    ModalDrawer(drawerContent = {
        //侧边栏
        AppDrawer(currentRoute, navigateToHome = {
            navController.navigate(JetnewsDestinations.HOME_ROUTE)
        }, navigateToInterest = {
            navController.navigate(JetnewsDestinations.INTEREST_ROUTE)
        }, closeDrawer = {

        })
    }) {
        //主页面
        JetnewsNavGraph(navHostController = navController)
    }

}