package com.example.newhopehotel.roomService.viewMorningCallList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.MorningCallEntity
import com.example.newhopehotel.databinding.MorningCallListItemBinding

class MorningCallAdapter(private val mListener: MorningCallClickListener) :
    RecyclerView.Adapter<MorningCallAdapter.MorningCallViewHolder>() {

    var morningCallList: List<MorningCallEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MorningCallViewHolder =
        MorningCallViewHolder.from(parent)

    override fun onBindViewHolder(holder: MorningCallViewHolder, position: Int) =
        holder.bind(morningCallList?.get(position), mListener)

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return morningCallList?.size ?: 0
    }

    class MorningCallViewHolder(private val binding: MorningCallListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentMorningCall: MorningCallEntity?, clickListener:MorningCallClickListener) {
            binding.morningCallItem = currentMorningCall
            binding.morningCallItemClick= clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MorningCallViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil
                    .inflate<MorningCallListItemBinding>(
                        layoutInflater, R.layout.morning_call_list_item,
                        parent, false
                    )
                return MorningCallViewHolder(binding)
            }
        }
    }

    interface MorningCallClickListener {
        fun onMorningCallClicked(chosenToy: MorningCallEntity)
    }

}
