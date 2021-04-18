package com.example.newhopehotel.roomService



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newhopehotel.R
import com.example.newhopehotel.roomService.viewMorningCallList.MorningCall
import com.example.newhopehotel.roomService.viewRoomServiceList.RoomService
import kotlinx.android.synthetic.main.activity_room_service.*
import kotlinx.android.synthetic.main.activity_room_service_main.*

class RoomServiceMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_service_main)
       buttonViewRoomServiceList.setOnClickListener {
            val intent = Intent(buttonViewRoomServiceList.context, RoomService::class.java)
           buttonViewRoomServiceList.context.startActivity(intent)


        }

        buttonViewMorningCallList.setOnClickListener{
            val intent = Intent(buttonViewRoomServiceList.context, MorningCall::class.java)
            buttonViewRoomServiceList.context.startActivity(intent)
        }
    }
}