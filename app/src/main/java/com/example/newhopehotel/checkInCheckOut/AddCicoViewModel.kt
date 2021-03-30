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
            /*This is for adding a new toy. We initialize a ToyEntry with default or null values
            This is because two-way databinding in the AddToyFragment is designed to
            register changes automatically, but it will need a toy object to register those changes.*/
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
                morningCall = false
            )
        }


    val isChanged: Boolean
        get() = if (mIsEdit) cicoBeingModified != chosenCico
        else cicoBeingModified != emptyCico
//        private set

//    companion object {
//        const val WOODEN = "Wooden"
//        const val ELECTRONIC = "Electronic"
//        const val PLASTIC = "Plastic"
//        const val PLUSH = "Plush"
//        const val MUSICAL = "Musical"
//        const val EDUCATIVE = "Educative"
//    }
}