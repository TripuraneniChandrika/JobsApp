package com.example.jobsapp


import com.example.jobsapp.ApiData.JobsData
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApiService {
    @GET("common/jobs")
    suspend fun getJobs(@Query("page") page: Int): JobsData
}




