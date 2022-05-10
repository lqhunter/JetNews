package com.lq.jetnews.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R
import com.lq.jetnews.model.Post


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
) {
    Column {
        post.forEach {
            PostCardSimple(post = it)
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