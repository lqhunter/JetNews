package com.lq.jetnews.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lq.jetnews.data.AppContainer
import com.lq.jetnews.ui.home.HomeRoute
import com.lq.jetnews.ui.home.HomeViewModel
import com.lq.jetnews.ui.interest.InterestsRoute
import com.lq.jetnews.ui.interest.InterestsViewModel

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
            println("viewmodel:${homeViewModel}")
            HomeRoute(
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
            )
        }

        composable(route = JetnewsDestinations.INTEREST_ROUTE) {
            val interestsViewModel: InterestsViewModel =
                viewModel(factory = InterestsViewModel.provideFactory(appContainer.interestsRepository))

            InterestsRoute(
                interestsViewModel = interestsViewModel,
                openDrawer = openDrawer
            )
        }
    }
}