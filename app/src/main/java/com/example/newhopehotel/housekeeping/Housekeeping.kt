package com.example.newhopehotel.housekeeping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_housekeeping.*


class Housekeeping : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_housekeeping)
        //setSupportActionBar(toolbar)

        val cleaningListFragment = CleaningListFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.houseKeepingFragmentHolder, cleaningListFragment)
            commit()
        }

    }

}