package com.example.newhopehotel.roomService.viewRoomServiceList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityRoomServiceBinding

class RoomService : AppCompatActivity() {
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

    override fun onBackPressed() {
        /*//If back is clicked when AddToyFragment is on the screen,
        check whether there are unsaved changes*/
        val currentFrag = supportFragmentManager.findFragmentById(R.id.main_container)
        if (currentFrag is AddRoomServiceFragment) {
            currentFrag.onBackClicked()
        } else {
            super.onBackPressed()
        }
    }
}