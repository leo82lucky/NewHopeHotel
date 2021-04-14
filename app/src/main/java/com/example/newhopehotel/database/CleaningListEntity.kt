package com.example.newhopehotel.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newhopehotel.data.MorningCall
import com.example.newhopehotel.data.RoomID
import com.example.newhopehotel.data.RoomStatus
import com.example.newhopehotel.data.RoomType
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Cleaning_List_Table")
data class CleaningListEntity(

    var userID: Int,
    var roomID: RoomID = RoomID.R001,
    var cleaningTime: String,
    @PrimaryKey(autoGenerate = true) val cleaningListId: Int = 0

) : Parcelable {
    fun copy(): CleaningListEntity {
        return CleaningListEntity(
            userID, roomID, cleaningTime, cleaningListId
        )
    }
}