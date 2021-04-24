package com.example.trackmyrun.repositories

import com.example.trackmyrun.db.RunDao
import com.example.trackmyrun.db.RunEntity
import javax.inject.Inject

//Provide functions of Database for our ViewModels
class MainRepository @Inject constructor(
    val runDao: RunDao
){

    suspend fun insertRun(run : RunEntity) = runDao.insertRun(run)
    suspend fun deleteRun(run : RunEntity) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()
    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()
    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()
    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()
    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()
    fun getTotalDistance() = runDao.getTotalDistance()
    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()
    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()
}