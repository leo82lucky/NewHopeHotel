@file:JvmName("BindingUtils")

package com.example.newhopehotel.utils

import androidx.databinding.InverseMethod
import com.example.newhopehotel.R
import com.example.newhopehotel.data.RoomStatus
import com.example.newhopehotel.data.RoomType

@InverseMethod("buttonIdToStatus")
fun statusToButtonId(roomStatus: RoomStatus?): Int {
    var selectedButtonId = -1

    roomStatus?.run {
        selectedButtonId = when (this) {
            RoomStatus.AVAILABLE -> R.id.radioBtn_available
            RoomStatus.UNAVAILABLE -> R.id.radioBtn_unavailable
            RoomStatus.RESERVED -> R.id.radioBtn_reserved
        }
    }

    return selectedButtonId
}

fun buttonIdToStatus(selectedButtonId: Int): RoomStatus? {
    return when (selectedButtonId) {
        R.id.radioBtn_available -> RoomStatus.AVAILABLE
        R.id.radioBtn_unavailable -> RoomStatus.UNAVAILABLE
        R.id.radioBtn_reserved -> RoomStatus.RESERVED
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