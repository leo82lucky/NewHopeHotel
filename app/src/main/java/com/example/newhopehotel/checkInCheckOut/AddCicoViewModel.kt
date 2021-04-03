package com.example.newhopehotel.checkInCheckOut

import androidx.lifecycle.ViewModel
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository

class AddCicoViewModel(
    private val mRepo: HotelRepository,
    private val chosenCico: CheckInCheckOutEntity?
) : ViewModel() {
    val cicoBeingModified: CheckInCheckOutEntity

    private var mIsEdit: Boolean = false

    init {
        if (chosenCico != null) {
            //This is edit case
            cicoBeingModified = chosenCico.copy()
            mIsEdit = true
        } else {
            cicoBeingModified = emptyCico
            mIsEdit = false
        }
    }

    private fun insertCico(cico: CheckInCheckOutEntity) {
        mRepo.insertCico(cico)
    }

    private fun updateCico(cico: CheckInCheckOutEntity) {
        mRepo.updateCico(cico)
    }

    fun saveCico() {
        if (!mIsEdit) {
            insertCico(cicoBeingModified)
        } else {
            updateCico(cicoBeingModified)
        }
    }

    private val emptyCico: CheckInCheckOutEntity
        get() {
//            val categories = mutableMapOf(
//                WOODEN to false, ELECTRONIC to false,
//                PLASTIC to false, PLUSH to false, MUSICAL to false, EDUCATIVE to false
//            )
            return CheckInCheckOutEntity(
                custName = "",
                icNo = "",
                contactNo = "",
                checkinDate = "dd/mm/yyyy",
                checkinTime = "hh:mm am",
                checkoutDate = "dd/mm/yyyy",
                checkoutTime = "hh:mm am",
                morningCall = false
            )
        }


    val isChanged: Boolean
        get() = if (mIsEdit) cicoBeingModified != chosenCico
        else cicoBeingModified != emptyCico
//        private set
//
//    companion object {
//        const val AVAILABLE = "Available"
//        const val UNAVAILABLE = "Unavailable"
//        const val RESERVED = "Reserved"
//    }
}