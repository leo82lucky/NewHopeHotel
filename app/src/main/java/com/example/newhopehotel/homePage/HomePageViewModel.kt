package com.example.newhopehotel.homePage

import com.example.newhopehotel.database.RegisterRepository
import androidx.databinding.Observable
import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomePageViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    val users = repository.users
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}