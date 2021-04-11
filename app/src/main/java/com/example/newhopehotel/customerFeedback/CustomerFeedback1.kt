package com.example.newhopehotel.customerFeedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityMainBinding
import com.example.newhopehotel.databinding.ActivityMorningCallBinding
import com.example.newhopehotel.roomService.viewMorningCallList.MorningCallListFragment

class CustomerFeedback1 : AppCompatActivity() {

    var binding:ActivityMainBinding?=null

    val feedback = Feedback()
    val feedbackEdit = FeedbackEdit()
    val feedbackScene = FeedbackScene()
    val viewedFeedback = ViewedFeedback()

    //override fun onCreate(savedInstanceState: Bundle?) {
       // super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_customer_feedback1)

    //}

    override fun getSupportFragmentManager(): FragmentManager {
        return super.getSupportFragmentManager()
    }

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