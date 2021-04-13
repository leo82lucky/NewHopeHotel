package com.example.newhopehotel.customerFeedback

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import com.example.newhopehotel.database.HotelRepository

class FeedbackScene2ViewModel(repository: HotelRepository, application: Application) :
    AndroidViewModel(application), Observable{


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}