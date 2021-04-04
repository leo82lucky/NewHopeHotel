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
    fun convertMapToJson(morningCall: Map<String, Boolean>): String {
        val categoryMap = object : TypeToken<Map<String, Boolean>>() {}.type
        return Gson().toJson(morningCall, categoryMap)
    }

    @TypeConverter
    fun convertJsonToMap(jsonToConvert: String): Map<String, Boolean> {
        val morningCallMap = object : TypeToken<Map<String, Boolean>>() {}.type
        return Gson().fromJson(jsonToConvert, morningCallMap)
    }
}