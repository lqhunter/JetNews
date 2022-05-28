package com.lq.jetnews.ui.interest

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lq.jetnews.R


@Composable
fun InterestsScreen(
    tabContent: List<TabContent>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.cd_interests),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon =
                {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            painter = painterResource(R.drawable.ic_jetnews_logo),
                            contentDescription = stringResource(R.string.cd_open_navigation_drawer),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* TODO: Open search */ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(R.string.cd_search)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )

        }
    ) {
        val screenModifier = Modifier.padding(it)
        InterestScreenContent(
            currentSectionIndex = selectedIndex,
            isExpandedScreen = false,
            updateSection = onSelectedIndexChange,
            tabContent = tabContent
        )
    }
}

@Composable
private fun InterestScreenContent(
    currentSectionIndex: Int,
    isExpandedScreen: Boolean,
    updateSection: (Int) -> Unit,
    tabContent: List<TabContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        InterestsTabRow(currentSectionIndex, updateSection, tabContent, isExpandedScreen)
        Divider(
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
        )
        Box(modifier = Modifier.weight(1f)) {
            // display the current tab content which is a @Composable () -> Unit
            tabContent[currentSectionIndex].content()
        }
    }
}

@Composable
private fun InterestsTabRow(
    selectedTabIndex: Int,
    updateSection: (Int) -> Unit,
    tabContent: List<TabContent>,
    isExpandedScreen: Boolean
) {
    when (isExpandedScreen) {
        false -> {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.primary
            ) {
                InterestsTabRowContent(selectedTabIndex, updateSection, tabContent)
            }
        }
        true -> {

        }
    }
}

@Composable
private fun InterestsTabRowContent(
    selectedTabIndex: Int,
    updateSection: (Int) -> Unit,
    tabContent: List<TabContent>,
    modifier: Modifier = Modifier
) {
    tabContent.forEachIndexed { index, content ->
        val colorText = if (selectedTabIndex == index) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
        }
        Tab(
            selected = selectedTabIndex == index,
            onClick = {
                if (selectedTabIndex == index) {
                    //reSelect
                } else {
                    updateSection(index)
                }
            },
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(id = content.section.titleResId),
                color = colorText,
                style = MaterialTheme.typography.subtitle2,
                modifier = modifier.paddingFromBaseline(top = 20.dp)
            )
        }
    }
}


/**
 * TabContent for a single tab of the screen.
 *
 * This is intended to encapsulate a tab & it's content as a single object. It was added to avoid
 * passing several parameters per-tab from the stateful composable to the composable that displays
 * the current tab.
 *
 * @param section the tab that this content is for
 * @param section content of the tab, a composable that describes the content
 */
class TabContent(val section: Sections, val content: @Composable () -> Unit)

enum class Sections(@StringRes val titleResId: Int) {
    Topics(R.string.interests_section_topics),
    People(R.string.interests_section_people),
    Publications(R.string.interests_section_publications)
}

/**
 * Remembers the content for each tab on the Interests screen
 * gathering application data from [InterestsViewModel]
 */
@Composable
fun rememberTabContent(interestsViewModel: InterestsViewModel): List<TabContent> {
    // UiState of the InterestsScreen
    val uiState by interestsViewModel.uiState.collectAsState()

    // Describe the screen sections here since each section needs 2 states and 1 event.
    // Pass them to the stateless InterestsScreen using a tabContent.
    val topicsSection = TabContent(Sections.Topics) {
//        val selectedTopics by interestsViewModel.selectedTopics.collectAsState()
//        TabWithSections(
//            sections = uiState.topics,
//            selectedTopics = selectedTopics,
//            onTopicSelect = { interestsViewModel.toggleTopicSelection(it) }
//        )
    }

    val peopleSection = TabContent(Sections.People) {
//        val selectedPeople by interestsViewModel.selectedPeople.collectAsState()
//        TabWithTopics(
//            topics = uiState.people,
//            selectedTopics = selectedPeople,
//            onTopicSelect = { interestsViewModel.togglePersonSelected(it) }
//        )
    }

    val publicationSection = TabContent(Sections.Publications) {
//        val selectedPublications by interestsViewModel.selectedPublications.collectAsState()
//        TabWithTopics(
//            topics = uiState.publications,
//            selectedTopics = selectedPublications,
//            onTopicSelect = { interestsViewModel.togglePublicationSelected(it) }
//        )
    }

    return listOf(topicsSection, peopleSection, publicationSection)
}