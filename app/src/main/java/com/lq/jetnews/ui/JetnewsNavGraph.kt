package com.lq.jetnews.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lq.jetnews.JetnewsDestinations
import com.lq.jetnews.ui.home.HomeRoute
import com.lq.jetnews.ui.interest.InterestRoute

@Composable
fun JetnewsNavGraph(navHostController: NavHostController, openDrawer: () -> Unit) {

    NavHost(navController = navHostController, startDestination = JetnewsDestinations.HOME_ROUTE) {
        composable(route = JetnewsDestinations.HOME_ROUTE) {
            HomeRoute(openDrawer)
        }

        composable(route = JetnewsDestinations.INTEREST_ROUTE) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                InterestRoute()
            }
        }
    }
}