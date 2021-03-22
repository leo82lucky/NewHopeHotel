package com.example.newhopehotel.housekeeping

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.worker_list_layout.view.*

class WorkerListAdapter : RecyclerView.Adapter<WorkerListAdapter.ViewHolder>()
{
    // find a way to read data from database into these arrays
    private val workerNames = arrayOf("test name 1", "test name 2", "test name 3")
    private val roomsAssigned = intArrayOf(4, 1, 0)
    private val workerImages = intArrayOf(R.drawable.worker_image_sample, R.drawable.worker_image_sample, R.drawable.worker_image_sample)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.worker_list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return workerNames.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.worker_name.text = workerNames[position]
        if (roomsAssigned[position] > 0)
            holder.itemView.rooms_assigned.text = "" + roomsAssigned[position] + " rooms assigned"
        else
            holder.itemView.rooms_assigned.text = "Tap to assign work"
        holder.itemView.worker_image.setImageResource(workerImages[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, RoomCleaningTime::class.java)
                itemView.context.startActivity(intent)

                //set this worker as selected
            }
        }
    }
}