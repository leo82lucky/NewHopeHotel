package com.example.newhopehotel.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "Room_Service_Table")
data class RoomServiceEntity(

    var custName: String,
    var request: String,
    @PrimaryKey(autoGenerate = true) val rsId: Int = 0
) : Parcelable {
    fun copy(): RoomServiceEntity {
        return RoomServiceEntity(
            custName, request,
            rsId
        )
    }
}