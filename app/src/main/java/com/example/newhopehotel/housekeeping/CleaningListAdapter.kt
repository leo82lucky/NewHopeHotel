package com.example.newhopehotel.housekeeping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.CleaningListEntity
import com.example.newhopehotel.databinding.ItemCleaningListBinding
import kotlinx.android.synthetic.main.item_cleaning_list.view.*

class CleaningListAdapter(
    private val mListener: CleaningListClickListener
) : RecyclerView.Adapter<CleaningListAdapter.CleaningListViewHolder>() {

    var cleaningList: List<CleaningListEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleaningListViewHolder = CleaningListViewHolder.from(parent)

    override fun getItemCount(): Int {
        return cleaningList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CleaningListViewHolder, position: Int) = holder.bind(cleaningList?.get(position), mListener)

    class CleaningListViewHolder(private val binding: ItemCleaningListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentCleaningList: CleaningListEntity?, clickListener: CleaningListClickListener) {
            binding.cleaningListItem = currentCleaningList
            binding.cleaningListItemClick = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): CleaningListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ItemCleaningListBinding>(
                    layoutInflater,
                    R.layout.item_cleaning_list,
                    parent,
                    false
                )
                return CleaningListViewHolder(binding)
            }
        }
    }

    interface CleaningListClickListener {
        fun onCleaningListClicked(chosenToy: CleaningListEntity)
    }
}