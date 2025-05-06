package com.example.jobsapp.RoomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkedJobDao {
    @Insert
    suspend fun addBookmarkedJob(job: BookmarkedJob)

    @Query("SELECT * FROM bookmarked_jobs")
    suspend fun getAllBookmarkedJobs(): List<BookmarkedJob>

    @Delete
    suspend fun removeBookmarkedJob(job: BookmarkedJob)
}





