package com.example.newhopehotel.housekeeping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_assign_work_worker_list.*

class AssignWork : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_work_worker_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = WorkerListAdapter()
        recyclerView.adapter = adapter
    }
}