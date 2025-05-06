package com.example.jobsapp.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.jobsapp.RoomDatabase.AppDatabase
import com.example.jobsapp.RoomDatabase.BookmarkedJob
import com.example.jobsapp.RoomDatabase.BookmarkedJobDao
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {
    private val jobDao: BookmarkedJobDao = AppDatabase.getDatabase(application).bookmarkedJobDao()

    // LiveData to observe bookmarked jobs
    val bookmarkedJobs: LiveData<List<BookmarkedJob>> = liveData {
        emit(jobDao.getAllBookmarkedJobs())
    }

    // Function to add a job to bookmarks
    fun addJobToBookmarks(job: BookmarkedJob) {
        viewModelScope.launch {
            jobDao.addBookmarkedJob(job)
        }
    }

    // Function to remove a job from bookmarks
    fun removeJobFromBookmarks(job: BookmarkedJob) {
        viewModelScope.launch {
            jobDao.removeBookmarkedJob(job)
        }
    }
}
