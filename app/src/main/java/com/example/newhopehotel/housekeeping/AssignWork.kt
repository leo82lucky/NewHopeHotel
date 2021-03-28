package com.example.newhopehotel.housekeeping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_assign_work_worker_list.*
import kotlinx.android.synthetic.main.activity_assign_work_worker_list.recyclerView
import kotlinx.android.synthetic.main.activity_assign_work_worker_list.toolbar
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.*

class AssignWork : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_work_worker_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = WorkerListAdapter()
        recyclerView.adapter = adapter

        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
}