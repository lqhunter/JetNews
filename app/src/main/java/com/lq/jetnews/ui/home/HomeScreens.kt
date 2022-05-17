package com.lq.jetnews.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    navigateToArticle: (String) -> Unit
) {
    Column {
        post.forEach {
            PostCardSimple(post = it, favorites.contains(it.id))
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
fun PostList(
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
                PostListSimpleSection(postsFeed.recommendedPosts, favorites, onArticleTapped)
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