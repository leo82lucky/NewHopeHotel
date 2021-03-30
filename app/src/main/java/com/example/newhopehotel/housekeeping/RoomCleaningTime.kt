package com.example.newhopehotel.housekeeping

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_room_cleaning_time.*
import kotlinx.android.synthetic.main.activity_room_service.toolbar

class RoomCleaningTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_cleaning_time)

        button8am.setOnClickListener {
            val intent = Intent(button8am.context, Rooms8am::class.java)
            button8am.context.startActivity(intent)
        }

        button12pm.setOnClickListener {
            val intent = Intent(button12pm.context, Rooms12pm::class.java)
            button12pm.context.startActivity(intent)
        }

        button4pm.setOnClickListener {
            val intent = Intent(button4pm.context, Rooms4pm::class.java)
            button4pm.context.startActivity(intent)
        }

        button8pm.setOnClickListener {
            val intent = Intent(button8pm.context, Rooms8pm::class.java)
            button8pm.context.startActivity(intent)
        }

        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

}