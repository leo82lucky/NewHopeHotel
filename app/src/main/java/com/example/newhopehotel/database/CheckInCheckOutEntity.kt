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
//    var morningCall: Map<String, Boolean>,
    var morningCall: MorningCall = MorningCall.NONE,
    @PrimaryKey(autoGenerate = true) val cicoId: Int = 0
) : Parcelable {
    fun copy(): CheckInCheckOutEntity {
//        val newMorningCall = mutableMapOf<String, Boolean>()
//        newMorningCall.putAll(morningCall)
        return CheckInCheckOutEntity(
            custName, icNo, contactNo, roomType,
            roomStatus, roomID, checkinDate, checkinTime, checkoutDate,
            checkoutTime, morningCall, cicoId
        )
    }
}
//CheckIn
// @PrimaryKey(autoGenerate = true) var custId: Int = 0,
//    @ColumnInfo(name = "cust_name") var customerName: String,
//    @ColumnInfo(name = "ic") var icNo: String,
//    @ColumnInfo(name = "contact_no") var contactNo: String,
//    @ColumnInfo(name = "room_id") var roomID: String
//    @ColumnInfo(name = "room_type") var roomType: String,
//    @ColumnInfo(name = "room_status") var roomStatus: String,
//    @ColumnInfo(name = "room_cardno") var roomCardNo: String
//    @ColumnInfo(name = "checkin_time") var checkInTime: Time,
//    @ColumnInfo(name = "checkin_date") var checkInDate: Date,
//    @ColumnInfo(name = "morning_call") var morningCall: Boolean
//    //Check Out
//    @ColumnInfo(name = "checkout_time") var checkoutTime: Time,
//    @ColumnInfo(name = "checkout_date") var checkoutDate: Date
