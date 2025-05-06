package com.assesment.jobfinder.data.model.remote

import com.example.jobfinder.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("common/jobs")
    suspend fun getJobs(@Query("page") page: Int): ApiResponse
}
