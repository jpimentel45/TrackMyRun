package com.example.trackmyrun.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "run_table")
data class RunEntity(
    var img: Bitmap? = null,
    var timeStamp : Long = 0L,
    var avgSpeedInMPH : Float = 0f,
    var distanceInMeters : Int = 0,
    var timeInMillis : Long = 0L,
    var caloriesBurned : Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null
}