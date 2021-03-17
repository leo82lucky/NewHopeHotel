package com.example.newhopehotel.housekeeping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_housekeeping.*

class Housekeeping : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_housekeeping)

        assignWorkButton.setOnClickListener {
            val intent = Intent(assignWorkButton.context, AssignWork::class.java)
            assignWorkButton.context.startActivity(intent)
        }
    }
}