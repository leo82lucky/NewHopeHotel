package com.example.newhopehotel.data

import androidx.room.TypeConverter


//class MapConverter {
//
//    @TypeConverter
//    fun convertMapToJson(categories: Map<String, Boolean>): String {
//        val categoryMap = object : TypeToken<Map<String, Boolean>>() {}.type
//        return Gson().toJson(categories, categoryMap)
//    }
//
//    @TypeConverter
//    fun convertJsonToMap(jsonToConvert: String): Map<String, Boolean> {
//        val categoryMap = object : TypeToken<Map<String, Boolean>>() {}.type
//        return Gson().fromJson(jsonToConvert, categoryMap)
//    }
//}
//
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