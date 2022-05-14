package com.lq.jetnews.ui

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lq.jetnews.data.AppContainer
import com.lq.jetnews.utils.WindowSize
import kotlinx.coroutines.launch

@Composable
fun JetNewApp(appContainer: AppContainer, windowSize: WindowSize) {

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    //获取navigation栈顶的entry
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: JetnewsDestinations.HOME_ROUTE

    val isExpandedScreen = windowSize == WindowSize.Expanded
    val drawerState =
        rememberDrawerState(initialValue = if (isExpandedScreen) DrawerValue.Open else DrawerValue.Closed)

    ModalDrawer(drawerContent = {
        //侧边栏
        AppDrawer(currentRoute, navigateToHome = {
            navController.navigate(JetnewsDestinations.HOME_ROUTE)
        }, navigateToInterest = {
            navController.navigate(JetnewsDestinations.INTEREST_ROUTE)
        }, closeDrawer = {
            coroutineScope.launch { drawerState.close() }
        })
    }, drawerState = drawerState) {
        //主页面
        JetnewsNavGraph(appContainer, navHostController = navController) {
            coroutineScope.launch { drawerState.open() }
        }
    }

}