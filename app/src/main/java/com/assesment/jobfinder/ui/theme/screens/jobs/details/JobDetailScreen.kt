package com.assesment.jobfinder.ui.theme.screens.jobs.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jobfinder.ui.components.ErrorView
import com.example.jobfinder.ui.components.LoadingView

@Composable
fun JobDetailScreen(
    viewModel: JobDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Job Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                actions = {
                    when (uiState) {
                        is JobDetailUiState.Success -> {
                            val job = (uiState as JobDetailUiState.Success).job
                            IconButton(onClick = { viewModel.toggleBookmark() }) {
                                Icon(
                                    imageVector = if (job.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                    contentDescription = "Bookmark",
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                        else -> {}
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is JobDetailUiState.Loading -> {
                    LoadingView()
                }
                is JobDetailUiState.Error -> {
                    val error = (uiState as JobDetailUiState.Error).message
                    ErrorView(
                        message = error,
                        onRetry = { /* Retry logic would go here */ }
                    )
                }
                is JobDetailUiState.Success -> {
                    val job = (uiState as JobDetailUiState.Success).job
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        // Title and company
                        Text(
                            text = job.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        job.company?.let {
                            Text(
                                text = it,
                                fontSize = 18.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Details section
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 2.dp
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Location
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "Location",
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = job.location,
                                        fontSize = 16.sp
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // Salary
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.AttachMoney,
                                        contentDescription = "Salary",
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = job.salary,
                                        fontSize = 16.sp
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // Phone
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Phone,
                                        contentDescription = "Phone",
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = job.phone,
                                        fontSize = 16.sp
                                    )
                                }

                                // Experience if available
                                job.experience?.let {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.WorkOutline,
                                            contentDescription = "Experience",
                                            tint = MaterialTheme.colors.primary
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Experience: $it",
                                            fontSize = 16.sp
                                        )
                                    }
                                }

                                // Posted date if available
                                job.postedAt?.let {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.DateRange,
                                            contentDescription = "Posted Date",
                                            tint = MaterialTheme.colors.primary
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Posted: $it",
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Description
                        Text(
                            text = "Description",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = job.description,
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        )
                    }
                }
            }
        }
    }
}
