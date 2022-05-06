package com.lq.jetnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lq.jetnews.ui.JetNewApp
import com.lq.jetnews.ui.theme.JetNewsTheme
import com.lq.jetnews.utils.rememberWindowSizeClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JetNewsTheme {
                //todo ???
                val windowSize = rememberWindowSizeClass()
                JetNewApp(windowSize)
            }
        }
    }
}
