package com.example.trackmyrun.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: RunEntity)

    @Delete
    suspend fun deleteRun(run: RunEntity)

    @Query("SELECT * FROM run_table ORDER BY timeStamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<RunEntity>>

    @Query("SELECT * FROM run_table ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis(): LiveData<List<RunEntity>>

    @Query("SELECT * FROM run_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<RunEntity>>

    @Query("SELECT * FROM run_table ORDER BY avgSpeedInMPH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<RunEntity>>

    @Query("SELECT * FROM run_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<RunEntity>>

    @Query("SELECT SUM(timeInMillis) FROM run_table")
    fun getTotalTimeInMillis(): LiveData<Long>

     @Query("SELECT SUM(caloriesBurned) FROM run_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

     @Query("SELECT SUM(distanceInMeters) FROM run_table")
    fun getTotalDistance(): LiveData<Int>

     @Query("SELECT SUM(avgSpeedInMPH) FROM run_table")
    fun getTotalAvgSpeed(): LiveData<Float>


}