package com.example.newhopehotel.roomService

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newhopehotel.roomService.rsdb.RoomServiceRepository
import java.lang.IllegalArgumentException



class RoomServiceViewModelFactory(private val repository: RoomServiceRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoomServiceViewModel::class.java)){
            return RoomServiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}