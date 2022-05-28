package com.lq.jetnews.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R
import com.lq.jetnews.data.posts.posts
import com.lq.jetnews.ui.article.ArticleScreen

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val uiState by homeViewModel.uiState.collectAsState()

    when (getHomeScreenType(isExpandedScreen, uiState)) {
        HomeScreenType.FeedWithArticleDetails -> {
            //列表与详情一起显示


        }
        HomeScreenType.Feed -> {
            //列表
            HomeFeedScreen(
                uiState = uiState,
                showTopAppBar = !isExpandedScreen,
                openDrawer = openDrawer,
                onRefreshPost = { homeViewModel.refreshPosts() },
                onToggleFavorite = { homeViewModel.toggleFavourite(it) },
                onSelectPost = { homeViewModel.selectedArticle(it) }
            )
        }
        HomeScreenType.ArticleDetails -> {
            check(uiState is HomeUiState.HasPosts)
            val post = (uiState as HomeUiState.HasPosts).selectedPost
            //详情
            ArticleScreen(
                post = post,
                isFavorite = (uiState as HomeUiState.HasPosts).favorites.contains(post.id),
                onToggleFavorite = { homeViewModel.toggleFavourite(post.id) }
            ) {
                homeViewModel.closeArticle()
            }

            BackHandler {
                homeViewModel.closeArticle()
            }
        }
    }

}


@Composable
fun HomeTopAppBar(elevation: Dp, openDrawer: () -> Unit) {
    //Material Design的app bar
    TopAppBar(
        //中间title
        title = {
            Icon(
                painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
                contentDescription = stringResource(id = R.string.app_name),
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp, top = 10.dp)
            )
        },
        //最左边的导航按钮
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_jetnews_logo),
                    contentDescription = stringResource(R.string.cd_open_navigation_drawer),
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        //最右边的一系列行为按钮
        actions = {
            IconButton(onClick = { /* TODO: Open search */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.cd_search)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
    )
}


/**
 * A precise enumeration of which type of screen to display at the home route.
 *
 * There are 3 options:
 * - [FeedWithArticleDetails], which displays both a list of all articles and a specific article.
 * - [Feed], which displays just the list of all articles
 * - [ArticleDetails], which displays just a specific article.
 */
private enum class HomeScreenType {
    FeedWithArticleDetails,
    Feed,
    ArticleDetails
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getHomeScreenType(
    isExpandedScreen: Boolean,
    uiState: HomeUiState
): HomeScreenType = when (isExpandedScreen) {
    false -> {
        when (uiState) {
            is HomeUiState.HasPosts -> {
                if (uiState.isArticleOpen) {
                    HomeScreenType.ArticleDetails
                } else {
                    HomeScreenType.Feed
                }
            }
            is HomeUiState.NoPosts -> HomeScreenType.Feed
        }
    }
    true -> HomeScreenType.FeedWithArticleDetails
}