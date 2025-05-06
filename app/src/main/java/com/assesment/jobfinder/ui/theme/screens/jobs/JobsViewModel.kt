package com.assesment.jobfinder.ui.theme.screens.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val repository: JobRepository
) : ViewModel() {

    val jobs: Flow<PagingData<Job>> = repository.getJobs().cachedIn(viewModelScope)

    fun toggleBookmark(job: Job) {
        viewModelScope.launch {
            repository.toggleBookmark(job)
        }
    }

    fun cleanUpNonBookmarkedJobs() {
        viewModelScope.launch {
            repository.cleanUpNonBookmarkedJobs()
        }
    }
}
