package com.example.newhopehotel.housekeeping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.fragment_rooms_to_clean.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoomsToCleanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomsToCleanFragment : Fragment(R.layout.fragment_rooms_to_clean) {
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

        // get data from database
        var roomsToCleanList = listOf(
            mutableListOf(
                RoomsToClean("A 101", false),
                RoomsToClean("A 102", false),
                RoomsToClean("A 103", false),
                RoomsToClean("A 104", false),
                RoomsToClean("A 105", false),
                RoomsToClean("A 106", false),
                RoomsToClean("A 107", false),
                RoomsToClean("A 108", false),
                RoomsToClean("A 109", false),
                RoomsToClean("A 110", false),
                RoomsToClean("A 111", false),
                RoomsToClean("A 112", false),
                RoomsToClean("A 113", false),
                RoomsToClean("A 114", false)
            ),
            mutableListOf(
                RoomsToClean("B 101", false),
                RoomsToClean("B 102", false),
                RoomsToClean("B 103", false),
                RoomsToClean("B 104", false),
                RoomsToClean("B 105", false),
                RoomsToClean("B 106", false),
                RoomsToClean("B 107", false),
                RoomsToClean("B 108", false),
                RoomsToClean("B 109", false),
                RoomsToClean("B 110", false),
                RoomsToClean("B 111", false),
                RoomsToClean("B 112", false),
                RoomsToClean("B 113", false),
                RoomsToClean("B 114", false)
            ),
            mutableListOf(
                RoomsToClean("C 101", false),
                RoomsToClean("C 102", false),
                RoomsToClean("C 103", false),
                RoomsToClean("C 104", false),
                RoomsToClean("C 105", false),
                RoomsToClean("C 106", false),
                RoomsToClean("C 107", false),
                RoomsToClean("C 108", false),
                RoomsToClean("C 109", false),
                RoomsToClean("C 110", false),
                RoomsToClean("C 111", false),
                RoomsToClean("C 112", false),
                RoomsToClean("C 113", false),
                RoomsToClean("C 114", false)
            ),
            mutableListOf(
                RoomsToClean("D 101", false),
                RoomsToClean("D 102", false),
                RoomsToClean("D 103", false),
                RoomsToClean("D 104", false),
                RoomsToClean("D 105", false),
                RoomsToClean("D 106", false),
                RoomsToClean("D 107", false),
                RoomsToClean("D 108", false),
                RoomsToClean("D 109", false),
                RoomsToClean("D 110", false),
                RoomsToClean("D 111", false),
                RoomsToClean("D 112", false),
                RoomsToClean("D 113", false),
                RoomsToClean("D 114", false)
            )
        )

        val adapter = RoomsToCleanAdapter(roomsToCleanList[0])
        rvRoomsToClean.adapter = adapter
        rvRoomsToClean.layoutManager = GridLayoutManager(this.context, 4, GridLayoutManager.VERTICAL, false)

        btn8am.setOnClickListener {
            val adapter = RoomsToCleanAdapter(roomsToCleanList[0])
            rvRoomsToClean.adapter = adapter
            rvRoomsToClean.layoutManager = GridLayoutManager(this.context, 4, GridLayoutManager.VERTICAL, false)
        }

        btn12pm.setOnClickListener {
            val adapter = RoomsToCleanAdapter(roomsToCleanList[1])
            rvRoomsToClean.adapter = adapter
            rvRoomsToClean.layoutManager = GridLayoutManager(this.context, 4, GridLayoutManager.VERTICAL, false)
        }

        btn4pm.setOnClickListener {
            val adapter = RoomsToCleanAdapter(roomsToCleanList[2])
            rvRoomsToClean.adapter = adapter
            rvRoomsToClean.layoutManager = GridLayoutManager(this.context, 4, GridLayoutManager.VERTICAL, false)
        }

        btn8pm.setOnClickListener {
            val adapter = RoomsToCleanAdapter(roomsToCleanList[3])
            rvRoomsToClean.adapter = adapter
            rvRoomsToClean.layoutManager = GridLayoutManager(this.context, 4, GridLayoutManager.VERTICAL, false)
        }

        btnAssign.setOnClickListener {
            // update database
            // remove selected rooms
            // go back to WorkerFragment
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoomsToCleanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoomsToCleanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}