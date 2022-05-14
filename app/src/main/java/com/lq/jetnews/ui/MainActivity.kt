package com.lq.jetnews.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lq.jetnews.JetnewsApplication
import com.lq.jetnews.ui.theme.JetNewsTheme
import com.lq.jetnews.utils.rememberWindowSizeClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window, false)
        val appContainer = (application as JetnewsApplication).container

        setContent {
            JetNewsTheme {
                //todo? windowSize作用
                val windowSize = rememberWindowSizeClass()
                JetNewApp(appContainer, windowSize)
            }
        }
    }
}
