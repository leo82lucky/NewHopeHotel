package com.example.newhopehotel.homePage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.database.HotelRepository
import java.lang.IllegalArgumentException

class HomePageViewModelFactory(
    private val repository: HotelRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomePageViewModel::class.java)) {
            return HomePageViewModel(repository, application) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}