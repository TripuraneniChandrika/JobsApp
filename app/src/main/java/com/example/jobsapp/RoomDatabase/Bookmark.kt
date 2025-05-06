package com.example.jobsapp.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class BookmarkedJob(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val jobTitle: String,
    val jobLocation: String,
    val jobSalary: String,
    val jobPhone: String,
    val isBookmarked: Boolean = false
)





