package com.example.newhopehotel.housekeeping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.item_rooms_to_clean.view.*

class RoomsToCleanAdapter(
    var roomsToCleans: List<RoomsToClean>
) : RecyclerView.Adapter<RoomsToCleanAdapter.RoomsToCleanViewHolder>() {

    inner class RoomsToCleanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsToCleanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rooms_to_clean, parent, false)
        return RoomsToCleanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roomsToCleans.size
    }

    override fun onBindViewHolder(holder: RoomsToCleanViewHolder, position: Int) {
        holder.itemView.apply {
            tvRoomName.text = roomsToCleans[position].roomName

            if (roomsToCleans[position].isSelected)
                borderRoomName.setBackgroundColor(resources.getColor(R.color.green_pigment))
            else
                roomsToCleans[position].isSelected = false

            borderRoomName.setOnClickListener {
                roomsToCleans[position].isSelected = !roomsToCleans[position].isSelected

                if (roomsToCleans[position].isSelected)
                    borderRoomName.setBackgroundColor(resources.getColor(R.color.green_pigment))
                else
                    borderRoomName.setBackgroundColor(resources.getColor(R.color.tea_green))
            }
        }
    }
}