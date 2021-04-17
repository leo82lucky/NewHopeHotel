package com.example.newhopehotel.housekeeping

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CleaningListEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.database.RoomToCleanEntity
import com.example.newhopehotel.utils.provideRepository

class CleaningListViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    var cleaningList: LiveData<List<CleaningListEntity>>? = null
        get() {
            return field ?: mRepo.cleaningList.also { field = it }
        }

    var cleaningListOfUserID: LiveData<List<CleaningListEntity>>? = null
        get() {
            return field ?: mRepo.getCleaningListByUserID(1).also { field = it }
        }

    fun getCleaningListByUserID(userID: Int): LiveData<List<CleaningListEntity>> {
        return mRepo.getCleaningListByUserID(userID)
    }

    fun insertCleaningList(cleaningList: CleaningListEntity) {
        mRepo.insertCleaningList(cleaningList)
    }

    fun deleteCleaningList(cleaningList: CleaningListEntity) {
        mRepo.deleteCleaningList(cleaningList)
    }

    fun insertRoomToClean(roomToClean: RoomToCleanEntity) {
        mRepo.insertRoomToClean(roomToClean)
    }

    fun deleteAllRoomToClean() {
        mRepo.deleteAllRoomToClean()
    }
}