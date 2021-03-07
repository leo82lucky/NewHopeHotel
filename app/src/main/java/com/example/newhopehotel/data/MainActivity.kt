package com.example.newhopehotel.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED VARIABLE")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    }

}