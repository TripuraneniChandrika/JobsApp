package com.example.jobsapp.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsapp.ApiData.Result
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.jobsapp.JobRepository


class JobViewModel(private val jobRepository: JobRepository) : ViewModel() {

    private val _jobsData = mutableStateOf<List<Result>>(emptyList())
    val jobsData: State<List<Result>> = _jobsData

    var isLoading by mutableStateOf(false)
    fun getJobById(jobId: String): Result? {
        // Logic to find a job by its ID (you may fetch it from your repository if it's not in the local state already)
        return jobsData.value.find { it.id == jobId?.toInt()}
    }

    fun fetchJobs() {
        isLoading = true
        viewModelScope.launch {
            try {
                val data = jobRepository.getJobs(1)
                _jobsData.value = data.results
            } catch (e: Exception) {
            } finally {
                isLoading = false
            }
        }
    }
}