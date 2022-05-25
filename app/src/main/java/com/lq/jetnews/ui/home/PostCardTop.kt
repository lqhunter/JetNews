package com.lq.jetnews.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R
import com.lq.jetnews.data.posts.posts
import com.lq.jetnews.model.Post
import com.lq.jetnews.ui.theme.JetNewsTheme

@Composable
fun PostCardTop(post: Post, modifier: Modifier = Modifier, navigateToArticle: (String) -> Unit) {
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { navigateToArticle(post.id) }
    ) {
        Image(
            painter = painterResource(post.imageId), contentDescription = null,
            modifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = post.title,
            style = typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = post.metadata.author.name,
            style = typography.subtitle2,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        //这里效果和 Modifier.alpha 一样
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = stringResource(
                    id = R.string.home_post_min_read,
                    formatArgs = arrayOf(post.metadata.date, post.metadata.readTimeMinutes)
                ),
                style = typography.subtitle2,
//                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        }


    }
}

@Preview
@Composable
private fun Preview() {
    val post = posts.highlightedPost

    JetNewsTheme {
        Surface {
            PostCardTop(post = post) {}
        }
    }
}
