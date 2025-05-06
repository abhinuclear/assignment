package com.assesment.jobfinder.data.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jobfinder.data.local.JobDao
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.remote.ApiService
import com.example.jobfinder.data.remote.JobPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobRepository @Inject constructor(
    private val apiService: ApiService,
    private val jobDao: JobDao
) {
    fun getJobs(): Flow<PagingData<Job>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 3
            ),
            pagingSourceFactory = { JobPagingSource(apiService, jobDao) }
        ).flow
    }

    suspend fun getJobById(jobId: String): Job? {
        return jobDao.getJobById(jobId)
    }

    fun getBookmarkedJobs(): Flow<List<Job>> {
        return jobDao.getBookmarkedJobs()
    }

    suspend fun toggleBookmark(job: Job) {
        val newBookmarkStatus = !job.isBookmarked
        jobDao.updateBookmarkStatus(job.id, newBookmarkStatus)

        // If we're bookmarking a job that might not be in DB yet
        if (newBookmarkStatus) {
            jobDao.insertJob(job.copy(isBookmarked = true))
        }
    }

    suspend fun isJobBookmarked(jobId: String): Boolean {
        return jobDao.isJobBookmarked(jobId) ?: false
    }

    suspend fun cleanUpNonBookmarkedJobs() {
        jobDao.deleteNonBookmarkedJobs()
    }
}
