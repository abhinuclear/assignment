package com.assesment.jobfinder.ui.theme.screens.jobs.bookmarks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jobfinder.ui.components.EmptyView
import com.example.jobfinder.ui.components.JobCard

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    onJobClick: (String) -> Unit
) {
    val bookmarkedJobs by viewModel.bookmarkedJobs.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        TopAppBar(
            title = { Text("Bookmarked Jobs") },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )

        // Content
        if (bookmarkedJobs.isEmpty()) {
            EmptyView(message = "You haven't bookmarked any jobs yet")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(bookmarkedJobs) { job ->
                    JobCard(
                        job = job,
                        onJobClick = onJobClick,
                        onBookmarkClick = { clickedJob ->
                            viewModel.toggleBookmark(clickedJob)
                        }
                    )
                }
            }
        }
    }
}