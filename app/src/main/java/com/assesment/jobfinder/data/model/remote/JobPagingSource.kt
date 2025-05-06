package com.assesment.jobfinder.data.model.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jobfinder.data.local.JobDao
import com.example.jobfinder.data.model.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class JobPagingSource(
    private val apiService: ApiService,
    private val jobDao: JobDao
) : PagingSource<Int, Job>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Job> {
        val page = params.key ?: 1

        return try {
            val response = apiService.getJobs(page)
            val jobs = response.jobs

            // Check bookmark status for each job
            val jobsWithBookmarkStatus = jobs.map { job ->
                val isBookmarked = jobDao.isJobBookmarked(job.id) ?: false
                job.copy(isBookmarked = isBookmarked)
            }

            // Save jobs to database
            withContext(Dispatchers.IO) {
                jobDao.insertJobs(jobsWithBookmarkStatus)
            }

            LoadResult.Page(
                data = jobsWithBookmarkStatus,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.totalPages) page + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
