package com.lq.jetnews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R
import com.lq.jetnews.ui.theme.JetNewsTheme

@Composable
fun AppDrawer() {
    Column(Modifier.fillMaxSize()) {
        JetNewsLogo()


    }


}

@Composable
private fun JetNewsLogo() {
    Row {
        Image(painter = painterResource(id = R.drawable.ic_jetnews_logo), contentDescription = null, colorFilter = ColorFilter.tint(MaterialTheme.colors.primary))
        Box(Modifier.width(8.dp).background(MaterialTheme.colors.primary)) {
//            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Preview
@Composable
fun PreviewLogo() {
    JetNewsTheme {
        Surface {
            JetNewsLogo()
        }
    }
}