@file:JvmName("BindingUtils")

package com.example.newhopehotel.utils

import androidx.databinding.InverseMethod
import com.example.newhopehotel.R
import com.example.newhopehotel.data.RoomID
import com.example.newhopehotel.data.RoomStatus
import com.example.newhopehotel.data.RoomType

fun attachMorningCall(morningCall: Map<String, Boolean>): String? {
    return morningCall.filter { it.value }
        .keys
        .joinToString(separator = ", ")
}

fun setToText(anyNotString: Any?): String {
    return anyNotString.toString()
}

@InverseMethod("buttonIdToStatus")
fun statusToButtonId(roomStatus: RoomStatus?): Int {
    var selectedButtonId = -1

    roomStatus?.run {
        selectedButtonId = when (this) {
            RoomStatus.Available -> R.id.radioBtn_available
            RoomStatus.Unavailable -> R.id.radioBtn_unavailable
            RoomStatus.Reserved -> R.id.radioBtn_reserved
        }
    }

    return selectedButtonId
}

fun buttonIdToStatus(selectedButtonId: Int): RoomStatus? {
    return when (selectedButtonId) {
        R.id.radioBtn_available -> RoomStatus.Available
        R.id.radioBtn_unavailable -> RoomStatus.Unavailable
        R.id.radioBtn_reserved -> RoomStatus.Reserved
        else -> null
    }
}

@InverseMethod("positionToRoomType")
fun roomTypeToPosition(room_type: RoomType?): Int {
    return room_type?.ordinal ?: 0
}

fun positionToRoomType(position: Int): RoomType {
    return RoomType.values()[position]
}

@InverseMethod("positionToRoomID")
fun roomIDToPosition(room_id: RoomID?): Int {
    return room_id?.ordinal ?: 0
}

fun positionToRoomID(position: Int): RoomID {
    return RoomID.values()[position]
}

