package com.example.newhopehotel.housekeeping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.RoomToCleanEntity
import com.example.newhopehotel.databinding.ItemRoomsToCleanBinding
import kotlinx.android.synthetic.main.item_rooms_to_clean.view.*

class RoomsToCleanAdapter(
    private val mListener: RoomToCleanClickListener
) : RecyclerView.Adapter<RoomsToCleanAdapter.RoomsToCleanViewHolder>() {

    var roomToCleanList: List<RoomToCleanEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsToCleanViewHolder = RoomsToCleanViewHolder.from(parent)

    override fun getItemCount(): Int {
        return roomToCleanList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RoomsToCleanViewHolder, position: Int) = holder.bind(roomToCleanList?.get(position), mListener)

    class RoomsToCleanViewHolder(private val binding: ItemRoomsToCleanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentRoomToClean: RoomToCleanEntity?, clickListener: RoomToCleanClickListener) {
            binding.roomToCleanItem = currentRoomToClean
            binding.roomToCleanItemClick = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RoomsToCleanViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ItemRoomsToCleanBinding>(
                    layoutInflater,
                    R.layout.item_rooms_to_clean,
                    parent,
                    false
                )
                return RoomsToCleanViewHolder(binding)
            }
        }
    }

    interface RoomToCleanClickListener {
        fun onRoomToCleanClicked(chosenToy: RoomToCleanEntity)
    }
}