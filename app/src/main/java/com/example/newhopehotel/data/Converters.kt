package com.example.newhopehotel.data

import androidx.room.TypeConverter
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
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}