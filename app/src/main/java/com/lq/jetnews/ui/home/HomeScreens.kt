package com.lq.jetnews.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lq.jetnews.R
import com.lq.jetnews.data.posts.posts
import com.lq.jetnews.model.Post
import com.lq.jetnews.model.PostsFeed


/**
 * 顶部
 */
@Composable
fun PostListTopSection(post: Post, navigateToArticle: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        text = stringResource(id = R.string.home_top_section_title)
    )
    PostCardTop(post)
    PostListDivider()
}

@Composable
fun PostListSimpleSection(
    post: List<Post>,
    favorites: Set<String>,
    navigateToArticle: (String) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    Column {
        post.forEach {
            PostCardSimple(post = it, favorites.contains(it.id), onToggleFavorite = onToggleFavorite)
        }
        PostListDivider()
    }
}

@Composable
fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
fun PostListPopularSection(posts: List<Post>, navigateToArticle: (String) -> Unit) {
    Column {
        Text(
            text = stringResource(id = R.string.home_popular_section_title),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(modifier = Modifier.padding(end = 16.dp)) {
            items(posts) { post ->
                PostCardPopular(
                    post = post,
                    navigateToArticle = navigateToArticle,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
            }
        }

        PostListDivider()
    }
}

@Composable
fun PostListHistorySection(posts: List<Post>, navigateToArticle: (String) -> Unit) {

    posts.forEach {
        PostCardHistory(it) {}
        PostListDivider()
    }

    //todo? 报错为什么不能用 LazyColumn,是因为外层用了 LazyColumn，这里就不能用吗
/*    LazyColumn {
        items(posts) { post ->
            PostCardHistory(post) {}
            PostListDivider()
        }
    }*/
}


@Composable
fun HomeFeedScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    openDrawer: () -> Unit,
    onRefreshPost: () -> Unit,
    onToggleFavorite: (String) -> Unit,
    onSelectPost: (String) -> Unit,
) {
    HomeScreenWithList(
        uiState = uiState,
        showTopAppBar = showTopAppBar,
        openDrawer = openDrawer,
        onRefreshPost = onRefreshPost
    ) { hasPostsUiState, modifier ->
        PostList(
            postsFeed = hasPostsUiState.postsFeed,
            favorites = hasPostsUiState.favorites,
            onArticleTapped = onSelectPost,
            onToggleFavorite = onToggleFavorite,
            modifier = modifier
        )

    }
}

@Composable
private fun PostList(
    postsFeed: PostsFeed,
    favorites: Set<String>,
    onArticleTapped: (postId: String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            PostListTopSection(posts.highlightedPost, onArticleTapped)
        }

        if (postsFeed.recommendedPosts.isNotEmpty()) {
            item {
                PostListSimpleSection(postsFeed.recommendedPosts, favorites, onArticleTapped, onToggleFavorite = onToggleFavorite)
            }
        }

        if (postsFeed.popularPosts.isNotEmpty()) {
            item {
                PostListPopularSection(postsFeed.popularPosts, onArticleTapped)
            }
        }

        if (postsFeed.recentPosts.isNotEmpty()) {
            item { PostListHistorySection(postsFeed.recentPosts, onArticleTapped) }
        }

    }

}

@Composable
fun HomeScreenWithList(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    openDrawer: () -> Unit,
    onRefreshPost: () -> Unit,
    hasPostsContent: @Composable (
        uiState: HomeUiState.HasPosts,
        modifier: Modifier
    ) -> Unit
) {
    Scaffold(
        snackbarHost = {},
        topBar = {
            if (showTopAppBar) {
                HomeTopAppBar(
                    openDrawer = openDrawer,
                    elevation = 4.dp
                )
            }
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)
        when (uiState) {
            is HomeUiState.HasPosts -> {
                LoadingContent(loading = uiState.isLoading, onRefresh = onRefreshPost) {
                    hasPostsContent(uiState, contentModifier)
                }
            }
            is HomeUiState.NoPosts -> {
                if (uiState.isLoading) {
                    FullScreenLoading()
                } else {
                    if (uiState.errorMessages.isEmpty()) {
                        // if there are no posts, and no error, let the user refresh manually
                        TextButton(
                            onClick = onRefreshPost,
//                            modifier.fillMaxSize()
                        ) {
                            Text(
                                stringResource(id = R.string.home_tap_to_load_content),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        // there's currently an error showing, don't show any content
                        Box(contentModifier.fillMaxSize()) { /* empty screen */ }
                    }
                }
            }
        }
    }

}

@Composable
fun LoadingContent(
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = loading),
        onRefresh = onRefresh,
        content = content
    )
}

/**
 * Full screen circular progress indicator
 */
@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}