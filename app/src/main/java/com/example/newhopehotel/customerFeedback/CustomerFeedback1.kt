package com.example.newhopehotel.customerFeedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.newhopehotel.R

class CustomerFeedback1 : AppCompatActivity() {

    val feedback = Feedback()
    val feedbackEdit = FeedbackEdit()
    val feedbackScene = FeedbackScene()
    val viewedFeedback = ViewedFeedback()

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_feedback1)
    }

    override fun getSupportFragmentManager(): FragmentManager {
        return super.getSupportFragmentManager()
    }



}