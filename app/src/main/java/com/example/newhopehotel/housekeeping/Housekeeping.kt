package com.example.newhopehotel.housekeeping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_housekeeping.*
import kotlinx.android.synthetic.main.activity_room_service.*

class Housekeeping : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_housekeeping)
        setSupportActionBar(toolbar)

        val cleaningListFragment = CleaningListFragment()
        val assignWorkFragment = AssignWorkFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.houseKeepingFragmentHolder, cleaningListFragment)
            commit()
        }

        btnCleaningList.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.houseKeepingFragmentHolder, cleaningListFragment)
                addToBackStack(null)
                commit()
            }
        }

        btnAssignWork.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.houseKeepingFragmentHolder, assignWorkFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

}