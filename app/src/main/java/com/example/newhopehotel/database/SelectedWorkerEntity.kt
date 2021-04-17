package com.example.newhopehotel.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Selected_Worker_Table")
data class SelectedWorkerEntity (

    var userName: String,
    @PrimaryKey var userID: Int

) : Parcelable {
    fun copy() : SelectedWorkerEntity {
        return SelectedWorkerEntity(
            userName, userID
        )
    }
}