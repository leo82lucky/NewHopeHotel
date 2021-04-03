package com.example.newhopehotel.roomService.viewMorningCallList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityMorningCallBinding

class MorningCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMorningCallBinding>(
            this,
            R.layout.activity_morning_call
        )
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.main_container,MorningCallListFragment())
            }
        }
    }

    override fun onBackPressed() {
        /*//If back is clicked when AddToyFragment is on the screen,
        check whether there are unsaved changes*/
        val currentFrag = supportFragmentManager.findFragmentById(R.id.main_container)
        if (currentFrag is AddMorningCallFragment) {
            currentFrag.onBackClicked()
        } else {
            super.onBackPressed()
        }
    }
}