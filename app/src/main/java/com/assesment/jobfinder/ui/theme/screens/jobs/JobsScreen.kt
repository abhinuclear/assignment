package com.assesment.jobfinder.ui.theme.screens.jobs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.jobfinder.ui.components.EmptyView
import com.example.jobfinder.ui.components.ErrorView
import com.example.jobfinder.ui.components.JobCard
import com.example.jobfinder.ui.components.LoadingItem
import com.example.jobfinder.ui.components.LoadingView

@Composable
fun JobsScreen(
    viewModel: JobsViewModel = hiltViewModel(),
    onJobClick: (String) -> Unit
) {
    val jobItems = viewModel.jobs.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        TopAppBar(
            title = { Text("Available Jobs") },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )

        // Content
        when (jobItems.loadState.refresh) {
            is LoadState.Loading -> {
                LoadingView()
            }
            is LoadState.Error -> {
                val error = jobItems.loadState.refresh as LoadState.Error
                ErrorView(
                    message = "Failed to load jobs: ${error.error.localizedMessage}",
                    onRetry = { jobItems.refresh() }
                )
            }
            is LoadState.NotLoading -> {
                if (jobItems.itemCount == 0) {
                    EmptyView(message = "No jobs found")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(jobItems) { job ->
                            job?.let {
                                JobCard(
                                    job = it,
                                    onJobClick = onJobClick,
                                    onBookmarkClick = { clickedJob ->
                                        viewModel.toggleBookmark(clickedJob)
                                    }
                                )
                            }
                        }

                        // Footer loading state
                        when (jobItems.loadState.append) {
                            is LoadState.Loading -> {
                                item { LoadingItem() }
                            }
                            is LoadState.Error -> {
                                val error = jobItems.loadState.append as LoadState.Error
                                item {
                                    Box(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)) {
                                        Text(
                                            text = "Failed to load more: ${error.error.localizedMessage}",
                                            textAlign = TextAlign.Center,
                                            color = MaterialTheme.colors.error,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}