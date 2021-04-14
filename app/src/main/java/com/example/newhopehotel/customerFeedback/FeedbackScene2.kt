package com.example.newhopehotel.customerFeedback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.fragment_customer_feedback1.*
import kotlinx.android.synthetic.main.fragment_customer_feedback_2.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [customer_feedback_2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackScene2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedbackFragment = FeedbackScene2()

        val activity = context as AppCompatActivity

//        btnAssignWork.setOnClickListener {
//            activity.supportFragmentManager.beginTransaction().apply {
//                replace(R.id.houseKeepingFragmentHolder, workerFragment)
//                addToBackStack(null)
//                commit()
//            }
//        }

        // change this to get data from database
//        var cleaningList = mutableListOf(
//            CleaningList("Room1", "8am",false),
//            CleaningList("Room2", "12pm",false),
//            CleaningList("Room3", "4pm",false),
//            CleaningList("Room4", "8pm",false),
//            CleaningList("Room5", "8am",false)
//        )

        var viewedFeedbackList = mutableListOf(

            ViewedFeedbackList("John","12/2/2021","Testing 1", "Question 1")

        )

        val adapter = ViewedFeedbackAdapter(viewedFeedbackList)
        rvViewedFBList.adapter = adapter
        rvViewedFBList.layoutManager = LinearLayoutManager(this.context)

        //btnMarkAsDone.setOnClickListener {
        //update database
        //remove done task
        //}
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment customer_feedback_2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FeedbackScene2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}