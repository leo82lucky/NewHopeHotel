package com.example.newhopehotel.checkInCheckOut

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.databinding.CicoListItemBinding

class CicoAdapter(private val mListener: CicoClickListener) :
    RecyclerView.Adapter<CicoAdapter.CicoViewHolder>() {

    var cicoList: List<CheckInCheckOutEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CicoViewHolder =
        CicoViewHolder.from(parent)

    override fun onBindViewHolder(holder: CicoViewHolder, position: Int) =
        holder.bind(cicoList?.get(position), mListener)

    override fun getItemCount(): Int {
        return cicoList?.size ?: 0
    }

    class CicoViewHolder(private val binding: CicoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentCico: CheckInCheckOutEntity?, clickListener: CicoClickListener) {
            binding.cicoItem = currentCico
            binding.cicoItemClick = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CicoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil
                    .inflate<CicoListItemBinding>(
                        layoutInflater, R.layout.cico_list_item,
                        parent, false
                    )
                return CicoViewHolder(binding)
            }
        }
    }

    interface CicoClickListener {
        fun onCicoClicked(chosenToy: CheckInCheckOutEntity)
    }

}
