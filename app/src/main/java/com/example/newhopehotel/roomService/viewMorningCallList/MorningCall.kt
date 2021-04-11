package com.example.newhopehotel.roomService.viewMorningCallList

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityMorningCallBinding
import com.example.newhopehotel.roomService.viewRoomServiceList.AddRoomServiceFragment
import kotlinx.android.synthetic.main.activity_morning_call.*
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.*


class MorningCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = DataBindingUtil.setContentView<ActivityMorningCallBinding>(
            this,
            R.layout.activity_morning_call
        )

             if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.main_container, MorningCallListFragment())
            }
        }
        setSupportActionBar(binding.tbMorningCall)
    }




}