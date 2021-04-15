package com.example.newhopehotel.housekeeping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.RegisterEntity
import com.example.newhopehotel.databinding.ItemWorkerBinding
import kotlinx.android.synthetic.main.item_worker.view.*


class WorkerAdapter(
    private val mListener: WorkerClickListener
) : RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {

    var workerList: List<RegisterEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder = WorkerViewHolder.from(parent)

    override fun getItemCount(): Int {
        return workerList?.size ?: 0
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) = holder.bind(workerList?.get(position), mListener)

    class WorkerViewHolder(private val binding: ItemWorkerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentWorker: RegisterEntity?, clickListener: WorkerClickListener) {
            binding.workerItem = currentWorker
            binding.workerItemClick = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): WorkerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ItemWorkerBinding>(
                    layoutInflater,
                    R.layout.item_worker,
                    parent,
                    false
                )
                return WorkerViewHolder(binding)
            }
        }
    }

    interface WorkerClickListener {
        fun onWorkerClicked(chosenToy: RegisterEntity)
    }
}