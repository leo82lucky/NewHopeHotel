package com.example.newhopehotel.housekeeping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.item_cleaning_list.view.*

class CleaningListAdapter(
    var cleaningLists: List<CleaningList>
) : RecyclerView.Adapter<CleaningListAdapter.CleaningListViewHolder>() {

    inner class CleaningListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleaningListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cleaning_list, parent, false)
        return CleaningListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cleaningLists.size
    }

    override fun onBindViewHolder(holder: CleaningListViewHolder, position: Int) {
        holder.itemView.apply {
            tvRoom.text = cleaningLists[position].room
            tvTime.text = cleaningLists[position].time
            cbDone.isChecked = cleaningLists[position].isChecked
        }
    }
}