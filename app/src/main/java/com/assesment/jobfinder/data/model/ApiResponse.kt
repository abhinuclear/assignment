package com.assesment.jobfinder.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("jobs") val jobs: List<Job>,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("currentPage") val currentPage: Int
)
