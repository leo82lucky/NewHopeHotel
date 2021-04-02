package com.example.newhopehotel.utils

import android.content.Context
import com.example.newhopehotel.database.HotelDatabase
import com.example.newhopehotel.database.HotelRepository

fun provideRepository(context: Context): HotelRepository {
    val executors = AppExecutors.getInstance()
    val db = HotelDatabase.getInstance(context)
    return HotelRepository.getInstance(db, executors)
}