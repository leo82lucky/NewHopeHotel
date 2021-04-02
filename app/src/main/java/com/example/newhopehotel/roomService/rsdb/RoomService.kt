package com.example.newhopehotel.roomService.rsdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_service_data_table")
data class RoomService (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "room_service_id")
    var RSID : Int,


    @ColumnInfo(name = "client_name")
    var name : String,

    @ColumnInfo(name = "request")
    var request : String

)