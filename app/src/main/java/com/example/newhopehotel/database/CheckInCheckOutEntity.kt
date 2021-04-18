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
@Entity(tableName = "CheckIn_Checkout_Table")
data class CheckInCheckOutEntity(

    var custName: String,
    var icNo: String,
    var contactNo: String,
    var roomType: RoomType = RoomType.Standard,
    var roomStatus: RoomStatus? = null,
    var roomID: RoomID = RoomID.R001,
    var checkinDate: String,
    var checkinTime: String,
    var checkoutDate: String,
    var checkoutTime: String,
    var morningCall: MorningCall = MorningCall.NONE,
    @PrimaryKey(autoGenerate = true) val cicoId: Int = 0
) : Parcelable {
    fun copy(): CheckInCheckOutEntity {
        return CheckInCheckOutEntity(
            custName, icNo, contactNo, roomType,
            roomStatus, roomID, checkinDate, checkinTime, checkoutDate,
            checkoutTime, morningCall, cicoId
        )
    }
}
