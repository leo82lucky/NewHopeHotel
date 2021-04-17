package com.example.newhopehotel.housekeeping

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.*
import com.example.newhopehotel.utils.provideRepository

class WorkerViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    var workerList: LiveData<List<RegisterEntity>>? = null
        get() {
            return field ?: mRepo.users.also { field = it }
        }

    fun deleteAllSelectedWorker() {
        mRepo.deleteAllSelectedWorker()
    }

    fun insertSelectedWorker(selectedWorker: SelectedWorkerEntity) {
        mRepo.insertSelectedWorker(selectedWorker)
    }
}