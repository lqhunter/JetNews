package com.lq.jetnews.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R
import com.lq.jetnews.model.Post
import com.lq.jetnews.utils.BookmarkButton
import com.lq.jetnews.utils.FavoriteButton
import com.lq.jetnews.utils.ShareButton
import com.lq.jetnews.utils.TextSettingsButton


@Composable
fun ArticleScreen(
    post: Post,
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onToggleFavorite: (String) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        //todo? fillMaxWidth 和 wrapContentWidth 为什么一起用
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_article_background),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(36.dp)
                        )
                        Text(
                            text = stringResource(
                                id = R.string.published_in,
                                post.publication?.name ?: ""
                            ),
                            style = MaterialTheme.typography.subtitle2,
                            color = LocalContentColor.current,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .weight(1.5f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_navigate_up),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface
            )
        },
        bottomBar = {
            val md = Modifier
            Surface(elevation = 8.dp, modifier = md) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = md
                        .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Vertical))
                        .height(56.dp)
                        .fillMaxWidth()
                ) {
                    FavoriteButton(onClick = { })
                    BookmarkButton(
                        isBookmarked = isFavorite,
                        onClick = { onToggleFavorite(post.id) })
                    ShareButton(onClick = {  })
                    Spacer(modifier = Modifier.weight(1f))
                    TextSettingsButton(onClick = {})
                }
            }
        }) { innerPadding ->
        PostContent(post = post, modifier = Modifier.padding(innerPadding))
    }

}
