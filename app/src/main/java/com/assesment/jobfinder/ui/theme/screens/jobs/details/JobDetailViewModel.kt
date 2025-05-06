package com.assesment.jobfinder.ui.theme.screens.jobs.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobDetailViewModel @Inject constructor(
    private val repository: JobRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<JobDetailUiState>(JobDetailUiState.Loading)
    val uiState: StateFlow<JobDetailUiState> = _uiState.asStateFlow()

    private var currentJob: Job? = null

    init {
        val jobId = savedStateHandle.get<String>("jobId") ?: ""
        if (jobId.isNotEmpty()) {
            loadJob(jobId)
        } else {
            _uiState.value = JobDetailUiState.Error("Invalid job ID")
        }
    }

    private fun loadJob(jobId: String) {
        viewModelScope.launch {
            try {
                val job = repository.getJobById(jobId)
                if (job != null) {
                    currentJob = job
                    _uiState.value = JobDetailUiState.Success(job)
                } else {
                    _uiState.value = JobDetailUiState.Error("Job not found")
                }
            } catch (e: Exception) {
                _uiState.value = JobDetailUiState.Error(e.localizedMessage ?: "Failed to load job details")
            }
        }
    }

    fun toggleBookmark() {
        currentJob?.let { job ->
            viewModelScope.launch {
                repository.toggleBookmark(job)
                // Update the UI state with the new bookmark status
                currentJob = job.copy(isBookmarked = !job.isBookmarked)
                _uiState.value = JobDetailUiState.Success(currentJob!!)
            }
        }
    }
}

sealed class JobDetailUiState {
    object Loading : JobDetailUiState()
    data class Success(val job: Job) : JobDetailUiState()
    data class Error(val message: String) : JobDetailUiState()
}
