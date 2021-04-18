package com.example.newhopehotel.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors private constructor(private val diskIO: Executor) {

    fun diskIO(): Executor {
        return diskIO
    }

    companion object {
        private val LOCK = Any()

        private var sInstance: AppExecutors? = null

        fun getInstance(): AppExecutors {
            return sInstance ?: synchronized(LOCK) {
                sInstance ?: AppExecutors(Executors.newSingleThreadExecutor()).also {
                    sInstance = it
                }
            }
        }
    }
}