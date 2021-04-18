package com.example.newhopehotel.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class RoomConverter {

    @TypeConverter
    fun convertRoomTypeToPosition(roomType: RoomType?): Int {
        return roomType?.ordinal ?: 0
    }

    @TypeConverter
    fun convertPositionToRoomType(position: Int): RoomType {
        return RoomType.values()[position]
    }

    @TypeConverter
    fun convertRoomStatusToInt(type: RoomStatus?): Int {
        return type?.ordinal ?: -1
    }

    @TypeConverter
    fun convertIntToRoomStatus(enumOrdinal: Int): RoomStatus? {
        return if (enumOrdinal == -1) null else RoomStatus.values()[enumOrdinal]
    }

    @TypeConverter
    fun convertRoomIDToPosition(roomID: RoomID?): Int {
        return roomID?.ordinal ?: 0
    }

    @TypeConverter
    fun convertPositionToRoomID(position: Int): RoomID {
        return RoomID.values()[position]
    }

    @TypeConverter
    fun convertMorningCallToPosition(morningcall: MorningCall?): Int {
        return morningcall?.ordinal ?: 0
    }

    @TypeConverter
    fun convertPositionToMorningCall(position: Int): MorningCall {
        return MorningCall.values()[position]
    }

}