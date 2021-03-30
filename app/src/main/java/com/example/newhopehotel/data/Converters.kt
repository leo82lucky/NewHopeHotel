package com.example.newhopehotel.data

import androidx.room.TypeConverter


class RoomTypeConverter {

    @TypeConverter
    fun convertRoomTypeToPosition(roomType: RoomType?): Int {
        return roomType?.ordinal ?: 0
    }

    @TypeConverter
    fun convertPositionToRoomType(position: Int): RoomType {
        return RoomType.values()[position]
    }
}

class RoomStatusConverter {

    @TypeConverter
    fun convertRoomStatusToInt(type: RoomStatus?): Int {
        return type?.ordinal ?: -1
    }

    @TypeConverter
    fun convertIntToRoomStatus(enumOrdinal: Int): RoomStatus? {
        return if (enumOrdinal == -1) null else RoomStatus.values()[enumOrdinal]
    }
}

class RoomIDConverter {

    @TypeConverter
    fun convertRoomIDToPosition(roomID: RoomID?): Int {
        return roomID?.ordinal ?: 0
    }

    @TypeConverter
    fun convertPositionToRoomID(position: Int): RoomID {
        return RoomID.values()[position]
    }
}