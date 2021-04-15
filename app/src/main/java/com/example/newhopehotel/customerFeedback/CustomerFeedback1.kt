package com.example.newhopehotel.customerFeedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityCustomerFeedback1Binding

class CustomerFeedback1 : AppCompatActivity() {

    //var binding: ActivityCustomerFeedback1Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCustomerFeedback1Binding>(
            this,
            R.layout.activity_customer_feedback1
        )

        setSupportActionBar(binding.customerFeedback)

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.feedback_container, FeedbackScene1())
            }
        }
        setSupportActionBar(binding.customerFeedback)
    }



}