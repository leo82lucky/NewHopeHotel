package com.example.newhopehotel.customerFeedback

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.newhopehotel.database.HotelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FeedbackScene1ViewModelFactory(private val repository: HotelRepository,
                                    private val application: Application) :
    ViewModelProvider.Factory {

    @Suppress("Unchecked_cast")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FeedbackScene1ViewModel::class.java)) {
            return FeedbackScene1ViewModel(repository, application) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}