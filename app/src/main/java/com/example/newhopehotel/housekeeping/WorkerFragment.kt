package com.example.newhopehotel.housekeeping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.fragment_worker.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkerFragment : Fragment(R.layout.fragment_worker) {
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

        val cleaningListFragment = CleaningListFragment()

        val activity = context as AppCompatActivity

        btnCleaningList.setOnClickListener {
            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_container, cleaningListFragment)
                addToBackStack(null)
                commit()
            }
        }

        // change this to get data from database
        var workerList = mutableListOf(
            Worker(R.drawable.worker_image_sample.toDrawable(), "name1"),
            Worker(R.drawable.worker_image_sample.toDrawable(), "name2"),
            Worker(R.drawable.worker_image_sample.toDrawable(), "name3"),
            Worker(R.drawable.worker_image_sample.toDrawable(), "name4"),
            Worker(R.drawable.worker_image_sample.toDrawable(), "name5")
        )

        val adapter = WorkerAdapter(workerList)
        rvWorkers.adapter = adapter
        rvWorkers.layoutManager = LinearLayoutManager(this.context)

        // make it so that clicking on the items on recycler view brings user to 'assignRooms fragment'
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AssignWorkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}