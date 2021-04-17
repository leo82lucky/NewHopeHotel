package com.example.newhopehotel.housekeeping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityHousekeepingBinding
import kotlinx.android.synthetic.main.activity_housekeeping.*

class Housekeeping : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHousekeepingBinding>(
            this,
            R.layout.activity_housekeeping
        )
        setSupportActionBar(binding.tbHousekeeping)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.main_container, CleaningListFragment())
            }
        }

    }

    override fun onBackPressed() {
            super.onBackPressed()
    }
}