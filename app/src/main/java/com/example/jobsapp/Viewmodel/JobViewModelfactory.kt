package com.example.jobsapp.Viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobsapp.JobRepository

class JobViewModelFactory(
    private val repository: JobRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobViewModel::class.java)) {
            return JobViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
