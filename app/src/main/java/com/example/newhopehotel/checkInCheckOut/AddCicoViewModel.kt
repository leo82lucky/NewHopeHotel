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
//            val morningCall = mutableMapOf(
//                EIGHT_AM to false, NINE_AM to false,
//                TEN_AM to false, ELEVEN_AM to false
//            )
            return CheckInCheckOutEntity(
                custName = "",
                icNo = "",
                contactNo = "",
                checkinDate = "dd/mm/yyyy",
                checkinTime = "hh:mm am",
                checkoutDate = "dd/mm/yyyy",
                checkoutTime = "hh:mm am"
            )
        }


    val isChanged: Boolean
        get() = if (mIsEdit) cicoBeingModified != chosenCico
        else cicoBeingModified != emptyCico
//        private set

//    companion object {
//        const val EIGHT_AM = "8 AM"
//        const val NINE_AM = "9 AM"
//        const val TEN_AM = "10 AM"
//        const val ELEVEN_AM = "11 AM"
//    }

}
