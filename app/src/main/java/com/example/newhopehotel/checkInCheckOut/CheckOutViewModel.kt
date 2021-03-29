package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newhopehotel.database.HotelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CheckOutViewModel(private val repository: HotelRepository, application: Application) :
    AndroidViewModel(application), Observable {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoCICOList = MutableLiveData<Boolean>()

    val navigatetoCICOList: LiveData<Boolean>
        get() = _navigatetoCICOList

    fun confirmButton() {
        _navigatetoCICOList.value = true
    }

    fun doneNavigatingCICOList() {
        _navigatetoCICOList.value = false
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}