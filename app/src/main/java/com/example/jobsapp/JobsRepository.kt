package com.example.jobsapp

import com.example.jobsapp.ApiData.JobsData


class JobRepository(private val apiService: JobApiService) {

    suspend fun getJobs(page: Int): JobsData {
        return apiService.getJobs(page)
    }
}

