package com.example.newhopehotel.checkInCheckOut

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.databinding.CicoListItemBinding

class CICORecycleViewAdapter() :
    ListAdapter<CheckInCheckOutEntity, CICORecycleViewAdapter.CustomerViewHolder>(CICODiffCallback()) {
    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder.from(parent)
    }

    class CustomerViewHolder private constructor(val binding: CicoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(customer: CheckInCheckOutEntity) {
            binding.CustomerNameTextView.text = customer.customerName
            binding.ICTextView.text = customer.icNo
            binding.ContactNoTextView.text = customer.contactNo
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CustomerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CicoListItemBinding.inflate(layoutInflater, parent, false)
                return CustomerViewHolder(binding)
            }
        }
    }
}

class CICODiffCallback : DiffUtil.ItemCallback<CheckInCheckOutEntity>() {

    override fun areItemsTheSame(
        oldItem: CheckInCheckOutEntity,
        newItem: CheckInCheckOutEntity
    ): Boolean {
        return oldItem.custId == newItem.custId
    }


    override fun areContentsTheSame(
        oldItem: CheckInCheckOutEntity,
        newItem: CheckInCheckOutEntity
    ): Boolean {
        return oldItem == newItem
    }


}


//class CICORecycleViewAdapter(private val customersList: List<CheckInCheckOutEntity>) :
//    RecyclerView.Adapter<CustomerViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding: CicoListItemBinding =
//            DataBindingUtil.inflate(layoutInflater, R.layout.cico_list_item, parent, false)
//        return CustomerViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return customersList.size
//    }
//
//    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
//        holder.bind(customersList[position])
//    }
//}
//
//class CustomerViewHolder(private val binding: CicoListItemBinding) :
//    RecyclerView.ViewHolder(binding.root) {
//
//    fun bind(customer: CheckInCheckOutEntity) {
//        binding.CustomerNameTextView.text = customer.customerName
//        binding.ICTextView.text=customer.icNo
//        binding.ContactNoTextView.text=customer.contactNo
//        binding.executePendingBindings()
//    }
//}