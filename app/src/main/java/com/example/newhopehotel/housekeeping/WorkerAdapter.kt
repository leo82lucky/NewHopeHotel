package com.example.newhopehotel.housekeeping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.item_worker.view.*

class WorkerAdapter(
    var workers: List<Worker>
) : RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {
    inner class WorkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_worker, parent, false)
        return WorkerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workers.size
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        holder.itemView.apply {
            ivPicture.setImageDrawable(workers[position].picture)
            tvName.text = workers[position].name

            //calculate no. rooms assigned from database
            val noRoomsAssigned = 0
            if(noRoomsAssigned > 0)
                tvRoomsAssigned.text = noRoomsAssigned.toString() + " rooms assigned."
            else
                tvRoomsAssigned.text = "No rooms assigned."
        }
    }

}