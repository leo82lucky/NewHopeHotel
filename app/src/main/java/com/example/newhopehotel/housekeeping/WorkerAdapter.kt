package com.example.newhopehotel.housekeeping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            tv_name.text = workers[position].name

            //calculate no. rooms assigned from database
            val noRoomsAssigned = 0
            if(noRoomsAssigned > 0)
                tv_phone_no.text = "Rooms assigned : " + noRoomsAssigned
            else
                tv_phone_no.text = "Rooms assigned : 0"

            setOnClickListener {
                Toast.makeText(context, tv_name.text, Toast.LENGTH_SHORT).show()

                val roomsToCleanFragment = RoomsToCleanFragment()
                val activity = context as AppCompatActivity

                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.main_container, roomsToCleanFragment)
                    addToBackStack(null)
                    commit()
                }

                tv_phone_no.text = "Rooms assigned : 4"
            }
        }
    }
}