package com.example.newhopehotel.roomService

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.*



class ViewRoomServiceList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_room_service_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = RoomServiceListAdapter()
        recyclerView.adapter = adapter
    }
}