package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.utils.provideRepository

class CICOViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    var cicoList: LiveData<List<CheckInCheckOutEntity>>? = null
        get() {
            return field ?: mRepo.cicoList.also { field = it }
        }

    fun insertCico(cico: CheckInCheckOutEntity) {
        mRepo.insertCico(cico)
    }

    fun deleteCico(cico: CheckInCheckOutEntity) {
        mRepo.deleteCico(cico)
    }
}