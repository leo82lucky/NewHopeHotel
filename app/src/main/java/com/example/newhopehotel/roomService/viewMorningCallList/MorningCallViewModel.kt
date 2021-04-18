package com.example.newhopehotel.roomService.viewMorningCallList

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.utils.provideRepository

class MorningCallViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    var morningCallList: LiveData<List<CheckInCheckOutEntity>>? = null
        get() {
            return field ?: mRepo.cicoList.also { field = it }
        }

    fun insertMorningCall(morningCall: CheckInCheckOutEntity) {
        mRepo.insertCico(morningCall)
    }

    fun deleteMorningCall(morningCall: CheckInCheckOutEntity) {
        mRepo.insertCico(morningCall)
    }
}