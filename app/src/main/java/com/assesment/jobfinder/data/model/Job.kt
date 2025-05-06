package com.assesment.jobfinder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "jobs")
data class Job(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("location") val location: String,
    @SerializedName("salary") val salary: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("description") val description: String,
    @SerializedName("company") val company: String,
    @SerializedName("experience") val experience: String? = null,
    @SerializedName("postedAt") val postedAt: String? = null,
    var isBookmarked: Boolean = false
)
