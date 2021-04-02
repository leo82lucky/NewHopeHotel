package com.example.newhopehotel.roomService

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.newhopehotel.roomService.rsdb.RoomService
import com.example.newhopehotel.roomService.rsdb.RoomServiceRepository
import kotlinx.coroutines.launch


class RoomServiceViewModel(private val repository: RoomServiceRepository) : ViewModel(), Observable {

    val roomService = repository.roomService
    private var isUpdateOrDelete = false
    private lateinit var roomServiceToUpdateOrDelete: RoomService




    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputRequest = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {

        if (inputName.value == null) {
            statusMessage.value = Event("Please enter subscriber's name")
        } else if (inputRequest.value == null) {
            statusMessage.value = Event("Please enter subscriber's email")
        } else {
            if (isUpdateOrDelete) {
                roomServiceToUpdateOrDelete.name = inputName.value!!
                roomServiceToUpdateOrDelete.request = inputRequest.value!!
                update(roomServiceToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val request = inputRequest.value!!
                insert(RoomService(0 , name, request))
                inputName.value = null
                inputRequest.value = null
            }
        }


    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(roomServiceToUpdateOrDelete)
        } else {
            clearAll()
        }

    }

    fun insert(roomService: RoomService) = viewModelScope.launch {
        val newRowId = repository.insert(roomService)
        if (newRowId > -1) {
            statusMessage.value = Event("Room Service Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun update(roomService: RoomService) = viewModelScope.launch {
        val noOfRows = repository.update(roomService)
        if (noOfRows > 0) {
            inputName.value = null
            inputRequest.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun delete(roomService:  RoomService) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(roomService)

        if (noOfRowsDeleted > 0) {
            inputName.value = null
            inputRequest.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun clearAll() = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun initUpdateAndDelete(roomService: RoomService) {
        inputName.value = roomService.name
        inputRequest.value = roomService.request
        isUpdateOrDelete = true
        roomServiceToUpdateOrDelete = roomService
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}