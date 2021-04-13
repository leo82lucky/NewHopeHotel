package com.example.newhopehotel.customerFeedback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.checkInCheckOut.CICOViewModel
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.FeedbackEntity
import com.example.newhopehotel.databinding.FragmentCicoListBinding
import com.example.newhopehotel.housekeeping.CleaningList
import com.example.newhopehotel.housekeeping.CleaningListAdapter
import com.example.newhopehotel.housekeeping.WorkerFragment
import kotlinx.android.synthetic.main.fragment_cleaning_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackEdit.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedbackScene1 : Fragment(R.layout.fragment_customer_feedback1) {
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

        val feedbackFragment = FeedbackScene1()

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

        var feedbackList = mutableListOf(

            FeedbackList("John","12/2/2021","Testing 1")

        )

        val adapter = FeedbackAdapter(feedbackList)
        rvCleaningList.adapter = adapter
        rvCleaningList.layoutManager = LinearLayoutManager(this.context)

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
         * @return A new instance of fragment FeedbackEdit.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FeedbackEdit().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}