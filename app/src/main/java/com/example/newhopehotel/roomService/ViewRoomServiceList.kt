package com.example.newhopehotel.roomService

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.databinding.ActivityMainBinding
import com.example.newhopehotel.housekeeping.AssignWork
import com.example.newhopehotel.roomService.RoomServiceListAdapter
import kotlinx.android.synthetic.main.activity_assign_work_worker_list.*

import kotlinx.android.synthetic.main.activity_recycler_room_service_list.*
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.recyclerView
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.toolbar
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.view.*


class ViewRoomServiceList : AppCompatActivity() {
    val adapter = RoomServiceListAdapter()
   // private lateinit var binding: ActivityMainBinding
    //private lateinit var roomServiceViewModel: RoomServiceViewModel
    //private lateinit var adapter: RoomServiceListAdapter
    val  RSIDList: MutableList<String> =ArrayList()
    val clientNamesList: MutableList<String> =ArrayList()
    val requestList: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_room_service_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
       // binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_room_service_list)
        //val dao = RoomServiceDatabase.getInstance(application).roomServiceDAO
       // val repository = RoomServiceRepository(dao)
        //val factory = RoomServiceViewModelFactory(repository)
       // roomServiceViewModel = ViewModelProvider(this,factory).get(RoomServiceViewModel::class.java)
       // binding
       // binding  = roomServiceViewModel
        //binding.lifecycleOwner = this
       // initRecyclerView()
        //setContentView(R.layout.activity_recycler_room_service_list)
        //val layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = layoutManager

        //recyclerView.adapter = adapter

        val totalHolder = findViewById<TextView>(R.id.totalHolder)
        totalHolder.setText(adapter.itemCount.toString())


        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        addButton.setOnClickListener {
            val intent = Intent(addButton.context, AddNewRoomService::class.java)
            addButton.context.startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        checkAndAddClientNameAndRequest()

    }
    fun checkAndAddClientNameAndRequest()
    {
        if(!intent.getStringExtra("clientName").isNullOrEmpty())
        {
            for (item in clientNamesList.indices)
            {
                adapter.clientNames.add(clientNamesList[item])
                adapter.RSID.add(RSIDList[item])
            }
            adapter.addClientNameAndRSID(intent.getStringExtra("clientName").toString())
            clientNamesList.add(intent.getStringExtra("clientName").toString())
            val stringRSID=adapter.RSID.get( adapter.RSID.size-1).substring(2).toInt()+1
            RSIDList.add("RS"+stringRSID.toString())
        }
        if(!intent.getStringExtra("request").isNullOrEmpty())
        {
            for (item in requestList.indices)
            {
                adapter.request.add(requestList[item])

            }
            adapter.addRequest(intent.getStringExtra("request").toString())
            requestList.add(intent.getStringExtra("request").toString())
        }
        totalHolder.setText(adapter.itemCount.toString())
    }

}