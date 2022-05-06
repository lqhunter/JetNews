package com.lq.jetnews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lq.jetnews.JetnewsDestinations
import com.lq.jetnews.R
import com.lq.jetnews.ui.theme.JetNewsTheme

@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterest: () -> Unit,
    closeDrawer: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        DrawerJetNewsLogo(Modifier.padding(16.dp))
        Divider()
        DrawerButton(
            icon = Icons.Filled.Home,
            title = stringResource(id = R.string.home_title),
            currentRoute == JetnewsDestinations.HOME_ROUTE
        ) {
            navigateToHome()
            closeDrawer()
        }
        DrawerButton(
            icon = Icons.Filled.List,
            title = stringResource(id = R.string.interests_title),
            currentRoute == JetnewsDestinations.INTEREST_ROUTE

        ) {
            navigateToInterest()
            closeDrawer()
        }
    }


}

@Composable
private fun DrawerJetNewsLogo(modifier: Modifier = Modifier) {
    Row(modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_jetnews_logo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
            contentDescription = "JetNews"
        )
    }
}

@Composable
fun DrawerButton(icon: ImageVector, title: String, isSelected: Boolean, onClick: () -> Unit) {
    val textColor =
        if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = 0.6f)

    val bgColor =
        if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.12f) else Color.Transparent

    Surface(
        Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp).fillMaxWidth(),
        color = bgColor,
        shape = RoundedCornerShape(4.dp)
    ) {
        TextButton(onClick = onClick, Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    imageVector = icon,
                    contentDescription = title,
                    colorFilter = ColorFilter.tint(textColor)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = title, color = textColor)
            }
        }


    }


}


@Preview
@Composable
fun PreviewHome() {
    JetNewsTheme {
        Surface {
            DrawerButton(
                icon = Icons.Filled.Home,
                title = stringResource(id = R.string.home_title),
                true
            ) {

            }
        }
    }
}

@Preview
@Composable
fun PreviewLogo() {
    JetNewsTheme {
        Surface {
            DrawerJetNewsLogo(Modifier.padding(16.dp))
        }
    }
}