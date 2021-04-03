package com.example.newhopehotel.roomService.viewMorningCallList

import androidx.lifecycle.ViewModel
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.database.MorningCallEntity

class AddMorningCallViewModel(
    private val mRepo: HotelRepository,
    private val chosenMorningCall: MorningCallEntity?
) : ViewModel() {
    val morningCallBeingModified: MorningCallEntity

    private var mIsEdit: Boolean = false

    init {
        if (chosenMorningCall!= null) {
            //This is edit case
            morningCallBeingModified = chosenMorningCall.copy()
            mIsEdit = true
        } else {
            morningCallBeingModified = emptyMorningCall
            mIsEdit = false
        }
    }

    private fun insertMorningCall(morningCall:MorningCallEntity) {
        mRepo.insertMorningCall(morningCall)
    }

    private fun updateMorningCall(morningCall: MorningCallEntity) {
        mRepo.updateMorningCall(morningCall)
    }

    fun saveCico() {
        if (!mIsEdit) {
            insertMorningCall(morningCallBeingModified)
        } else {
            updateMorningCall(morningCallBeingModified)
        }
    }

    private val emptyMorningCall: MorningCallEntity
        get() {
//            val categories = mutableMapOf(
//                WOODEN to false, ELECTRONIC to false,
//                PLASTIC to false, PLUSH to false, MUSICAL to false, EDUCATIVE to false
//            )
            return MorningCallEntity(
                custName = "",
                time = "hh:mm am",
                date="dd/mm/yyyy"

            )
        }


    val isChanged: Boolean
        get() = if (mIsEdit) morningCallBeingModified != chosenMorningCall
        else morningCallBeingModified != emptyMorningCall

}