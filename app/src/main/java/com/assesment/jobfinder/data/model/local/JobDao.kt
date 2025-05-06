package com.assesment.jobfinder.data.model.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.jobfinder.data.model.Job
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobs(jobs: List<Job>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Query("SELECT * FROM jobs WHERE id = :jobId")
    suspend fun getJobById(jobId: String): Job?

    @Query("SELECT * FROM jobs WHERE isBookmarked = 1")
    fun getBookmarkedJobs(): Flow<List<Job>>

    @Query("UPDATE jobs SET isBookmarked = :isBookmarked WHERE id = :jobId")
    suspend fun updateBookmarkStatus(jobId: String, isBookmarked: Boolean)

    @Query("SELECT isBookmarked FROM jobs WHERE id = :jobId")
    suspend fun isJobBookmarked(jobId: String): Boolean?

    @Query("DELETE FROM jobs WHERE isBookmarked = 0")
    suspend fun deleteNonBookmarkedJobs()
}
