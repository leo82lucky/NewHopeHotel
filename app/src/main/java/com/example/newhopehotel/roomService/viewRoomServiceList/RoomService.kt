package com.example.newhopehotel.roomService.viewRoomServiceList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityMorningCallBinding
import com.example.newhopehotel.databinding.ActivityRoomServiceBinding
import com.example.newhopehotel.roomService.viewRoomServiceList.RoomServiceListFragment
import kotlinx.android.synthetic.main.activity_room_service.*

class RoomService : AppCompatActivity() {

    var editMode=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRoomServiceBinding>(
            this,
            R.layout.activity_room_service
        )
        setSupportActionBar(binding.tbRoomService)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.main_container,RoomServiceListFragment())
            }
        }


    }
    fun activateAddRoomServiceTitle()
    {
        tv_room_service_title.visibility = View.GONE
        tv_add_room_service_title!!.visibility = View.VISIBLE
    }
    fun activateRoomServiceTitle()
    {
        tv_room_service_title.visibility = View.VISIBLE
        tv_add_room_service_title!!.visibility = View.GONE
    }
    override fun onBackPressed() {
        val currentFrag = supportFragmentManager.findFragmentById(R.id.main_container)
        if (currentFrag is AddRoomServiceFragment) {
            currentFrag.onBackClicked()
        }
        else {
            super.onBackPressed()
        }
    }

}