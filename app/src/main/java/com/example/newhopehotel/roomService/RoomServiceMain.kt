package com.example.newhopehotel.roomService



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newhopehotel.R
import com.example.newhopehotel.roomService.viewMorningCallList.MorningCall
import kotlinx.android.synthetic.main.activity_room_service.*

class RoomServiceMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_service)
        // set toolbar as support action bar
        setSupportActionBar(toolbar)

       buttonViewRoomServiceList.setOnClickListener {
            val intent = Intent(buttonViewRoomServiceList.context, ViewRoomServiceList::class.java)
           buttonViewRoomServiceList.context.startActivity(intent)


        }

        buttonViewMorningCallList.setOnClickListener{
            val intent = Intent(buttonViewRoomServiceList.context, MorningCall::class.java)
            buttonViewRoomServiceList.context.startActivity(intent)
        }
    }
}