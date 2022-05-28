package com.lq.jetnews.ui.interest

import androidx.annotation.StringRes
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun InterestsRoute(
    interestsViewModel: InterestsViewModel,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val contents = rememberTabContent(interestsViewModel)
    val selectedIndex = rememberSaveable {
        mutableStateOf(0)
    }

    InterestsScreen(
        tabContent = contents,
        selectedIndex = selectedIndex.value,
        onSelectedIndexChange = { selectedIndex.value = it},
        openDrawer = openDrawer,
        scaffoldState =  scaffoldState
    )


}
