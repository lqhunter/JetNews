package com.lq.jetnews.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R
import com.lq.jetnews.data.posts.post3
import com.lq.jetnews.model.Post
import com.lq.jetnews.ui.theme.JetNewsTheme

@Composable
fun PostCardSimple(post: Post, isFavorite: Boolean) {
    Row(modifier = Modifier
        .clickable { }
        .padding(16.dp)
        .semantics {//todo? 这里是什么意思

        }
    ) {
        PostImage(id = post.imageThumbId, modifier = Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = post.title, style = MaterialTheme.typography.subtitle1)
            AuthorAndReadTime(post)
        }
        BookmarkButton(isBookMarked = isFavorite) {

        }

    }
}

@Composable
private fun AuthorAndReadTime(post: Post) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = stringResource(
                id = R.string.home_post_min_read,
                formatArgs = arrayOf(post.metadata.date, post.metadata.readTimeMinutes)
            ),
            style = MaterialTheme.typography.body2,
        )
    }
}

@Composable
fun PostCardHistory(post: Post, navigateToArticle: (String) -> Unit) {
    Row(modifier = Modifier
        .clickable { navigateToArticle(post.id) }) {

        PostImage(
            id = post.imageThumbId,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(top = 16.dp, bottom = 16.dp)) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.home_post_based_on_history),
                    style = MaterialTheme.typography.overline
                )
            }
            Text(
                post.title,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            AuthorAndReadTime(post)
        }
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(R.string.cd_more_actions)
            )
        }
    }
}

@Composable
fun BookmarkButton(isBookMarked: Boolean, onClick: () -> Unit) {
    IconToggleButton(checked = isBookMarked, onCheckedChange = { onClick() }) {
        Icon(
            imageVector = if (isBookMarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
            contentDescription = null
        )
    }
}

@Composable
fun PostImage(@DrawableRes id: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Preview
@Composable
fun PreviewSimple() {
    JetNewsTheme {
        Surface {
            PostCardSimple(post = post3, true)
        }
    }
}

@Preview
@Composable
fun PreviewButton() {
    JetNewsTheme {
        Surface {
            BookmarkButton(isBookMarked = true) {

            }
        }
    }
}

@Preview
@Composable
fun PreviewHistory() {
    JetNewsTheme {
        Surface {
            PostCardHistory(post = post3) {}
        }
    }
}