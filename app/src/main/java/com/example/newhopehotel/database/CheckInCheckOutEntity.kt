package com.example.newhopehotel.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CheckIn_Checkout_Table")
data class CheckInCheckOutEntity(

    //CheckIn
    @PrimaryKey(autoGenerate = true) var custId: Int = 0,

    @ColumnInfo(name = "cust_name") var customerName: String,

    @ColumnInfo(name = "ic") var icNo: String,

    @ColumnInfo(name = "contact_no") var contactNo: String

//    @ColumnInfo(name = "room_id") var roomID: String,
//
//    @ColumnInfo(name = "room_type") var roomType: String,
//
//    @ColumnInfo(name = "room_status") var roomStatus: String,
//
//    @ColumnInfo(name = "room_cardno") var roomCardNo: String

//    @ColumnInfo(name = "checkin_time") var checkInTime: Time,
//
//    @ColumnInfo(name = "checkin_date") var checkInDate: Date,

//    @ColumnInfo(name = "morning_call") var morningCall: Boolean

//    //Check Out
//    @ColumnInfo(name = "checkout_time") var checkoutTime: Time,
//
//    @ColumnInfo(name = "checkout_date") var checkoutDate: Date


)
