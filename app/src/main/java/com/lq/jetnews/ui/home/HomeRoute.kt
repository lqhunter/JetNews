package com.lq.jetnews.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R

@Composable
fun HomeRoute(openDrawer: () -> Unit) {
    HomeTopAppBar(openDrawer = openDrawer)
}


@Composable
fun HomeTopAppBar(openDrawer: () -> Unit) {
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