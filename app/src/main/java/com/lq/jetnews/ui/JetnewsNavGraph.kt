package com.lq.jetnews.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lq.jetnews.data.AppContainer
import com.lq.jetnews.ui.home.HomeRoute
import com.lq.jetnews.ui.home.HomeViewModel
import com.lq.jetnews.ui.interest.InterestRoute

@Composable
fun JetnewsNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    navHostController: NavHostController,
    openDrawer: () -> Unit
) {

    NavHost(navController = navHostController, startDestination = JetnewsDestinations.HOME_ROUTE) {
        composable(route = JetnewsDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel =
                viewModel(factory = HomeViewModel.provideFactory(appContainer.postsRepository))

            HomeRoute(
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
            )
        }

        composable(route = JetnewsDestinations.INTEREST_ROUTE) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                InterestRoute()
            }
        }
    }
}