package com.example.newhopehotel.housekeeping

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_room_cleaning_time.*

class RoomCleaningTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_cleaning_time)

        _8amButton.setOnClickListener {
            val intent = Intent(_8amButton.context, _8amRoomList::class.java)
            _8amButton.context.startActivity(intent)
        }

        _12pmButton.setOnClickListener {
            val intent = Intent(_12pmButton.context, _12pmRoomList::class.java)
            _12pmButton.context.startActivity(intent)
        }

        _4pmButton.setOnClickListener {
            val intent = Intent(_4pmButton.context, _4pmRoomList::class.java)
            _4pmButton.context.startActivity(intent)
        }

        _8pmButton.setOnClickListener {
            val intent = Intent(_8pmButton.context, _8pmRoomList::class.java)
            _8pmButton.context.startActivity(intent)
        }
    }

}