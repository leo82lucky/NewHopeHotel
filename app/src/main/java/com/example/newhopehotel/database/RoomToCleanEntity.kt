package com.example.newhopehotel.database

import android.content.res.ColorStateList
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newhopehotel.data.RoomID
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Room_To_Clean_Table")
data class RoomToCleanEntity (

    @ColumnInfo(name = "border_color")
    var borderColor: Int,
    var cleaningTime: String,
    @PrimaryKey var roomID: RoomID = RoomID.R001

) : Parcelable  {
    fun copy(): RoomToCleanEntity {
        return RoomToCleanEntity(
            borderColor, cleaningTime, roomID
        )
    }
}