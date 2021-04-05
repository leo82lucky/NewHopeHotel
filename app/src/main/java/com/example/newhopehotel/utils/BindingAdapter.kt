package com.example.newhopehotel.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.newhopehotel.R
import com.example.newhopehotel.data.RoomStatus


@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("statusBackgroundColor")
fun ImageView.setBgColor(roomStatus: RoomStatus?) {
    roomStatus?.run {
        val colorId = when (this) {
            RoomStatus.Available -> R.drawable.status_available
            RoomStatus.Unavailable -> R.drawable.status_unavailable
            RoomStatus.Reserved -> R.drawable.status_reserved
        }
        setImageResource(colorId)
    }
}

