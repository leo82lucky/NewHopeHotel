package com.example.newhopehotel.roomService.viewMorningCallList

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.databinding.MorningCallListItemBinding
import kotlinx.android.synthetic.main.morning_call_list_item.*


class MorningCallAdapter(
    private val mListener: MorningCallClickListener,
    activityContext: Context
) :
    RecyclerView.Adapter<MorningCallAdapter.MorningCallViewHolder>() {

    val activityContextThis = activityContext

    var morningCallList: List<CheckInCheckOutEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MorningCallViewHolder {
        val activityContext = activityContextThis
        return MorningCallViewHolder.from(parent, activityContext)
    }

    override fun onBindViewHolder(holder: MorningCallViewHolder, position: Int) =
        holder.bind(morningCallList?.get(position), mListener)

    override fun getItemCount(): Int {
        return morningCallList?.size ?: 0
    }

    class MorningCallViewHolder(
        private val binding: MorningCallListItemBinding,
        activityContext: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        var callBtn: Button = itemView.findViewById(R.id.btn_call)
        var activtyContextThis = activityContext
        fun bind(
            currentMorningCall: CheckInCheckOutEntity?,
            clickListener: MorningCallClickListener

        ) {

            binding.morningCallItem = currentMorningCall
            binding.morningCallItemClick = clickListener
            binding.executePendingBindings()
            callBtn.setOnClickListener {

                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse(
                        "tel:" + Uri.encode(
                            binding.morningCallItem!!.contactNo.toString().trim()
                        )
                    )
                )
                activtyContextThis.startActivity(intent)

            }
        }

        companion object {
            fun from(parent: ViewGroup, activityContext: Context): MorningCallViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = DataBindingUtil
                    .inflate<MorningCallListItemBinding>(
                        layoutInflater, R.layout.morning_call_list_item,
                        parent, false
                    )


                return MorningCallViewHolder(binding, activityContext)
            }


        }
    }

    interface MorningCallClickListener {
        fun onMorningCallClicked(chosenToy: CheckInCheckOutEntity)
    }


}
