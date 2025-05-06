package com.assesment.jobfinder.ui.theme.screens.jobs.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel() {

    val bookmarkedJobs: StateFlow<List<Job>> = repository
        .getBookmarkedJobs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleBookmark(job: Job) {
        viewModelScope.launch {
            repository.toggleBookmark(job)
        }
    }
}
