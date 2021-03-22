package com.example.newhopehotel.checkInCheckOut

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.databinding.ListItemBinding
import com.example.newhopehotel.userDetails.MyviewHolder

class CICORecycleViewAdapter(private val customersList: List<CheckInCheckOutEntity>) :
    RecyclerView.Adapter<CustomerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.cico_list_item, parent, false)
        return CustomerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customersList.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bind(customersList[position])

    }


}

class CustomerViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(customer: CheckInCheckOutEntity) {
        binding.FirstNameTextView.text = customer.customerName
        binding.secondNameTextView.text = customer.roomID
        binding.userTextField.text = customer.roomType
    }

}