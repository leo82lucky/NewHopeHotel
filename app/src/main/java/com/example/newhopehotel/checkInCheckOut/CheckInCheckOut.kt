package com.example.newhopehotel.checkInCheckOut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityCheckInCheckOutBinding

class CheckInCheckOut : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCheckInCheckOutBinding>(
            this,
            R.layout.activity_check_in__check_out
        )
        setSupportActionBar(binding.tbCheckInCheckOut)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.main_container, CicoListFragment())
            }
        }

    }

    override fun onBackPressed() {
        /*//If back is clicked when AddToyFragment is on the screen,
        check whether there are unsaved changes*/
        val currentFrag = supportFragmentManager.findFragmentById(R.id.main_container)
        if (currentFrag is AddCicoFragment) {
            currentFrag.onBackClicked()
        } else {
            super.onBackPressed()
        }
    }

}