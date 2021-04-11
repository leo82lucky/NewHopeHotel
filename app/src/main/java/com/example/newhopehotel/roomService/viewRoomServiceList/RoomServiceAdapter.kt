package com.example.newhopehotel.roomService.viewRoomServiceList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.RoomServiceEntity
import com.example.newhopehotel.databinding.RoomServiceListItemBinding

class RoomServiceAdapter(private val mListener: RoomServiceClickListener) :
    RecyclerView.Adapter<RoomServiceAdapter.RoomServiceViewHolder>() {

    var roomServiceList: List<RoomServiceEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomServiceViewHolder =
        RoomServiceViewHolder.from(parent)


    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return roomServiceList?.size ?: 0
    }

    class RoomServiceViewHolder(private val binding: RoomServiceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var formattedRSID:String=""
        fun bind(currentRoomService: RoomServiceEntity?, clickListener:RoomServiceClickListener) {
            binding.roomServiceItem = currentRoomService
            binding.roomServiceItemClick= clickListener
            formatIDText(binding.roomServiceItem!!.rsId)
            binding.tvRoomServiceId.setText(formattedRSID )
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): RoomServiceViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil
                    .inflate<RoomServiceListItemBinding>(
                        layoutInflater, R.layout.room_service_list_item,
                        parent, false
                    )
                return RoomServiceViewHolder(binding)
            }
        }
        fun formatIDText(currentNo:Int)
        {

            if(currentNo<10)
            {
                formattedRSID="RS00"+currentNo.toString()
                return
            }
            if(currentNo<100)
            {
                formattedRSID="RS0"+currentNo.toString()
                return
            }
            if(currentNo<1000)
            {
                formattedRSID="RS"+currentNo.toString()
                return
            }
        }
    }

    interface RoomServiceClickListener {
        fun onRoomServiceClicked(chosenToy: RoomServiceEntity)

    }




    override fun onBindViewHolder(holder: RoomServiceAdapter.RoomServiceViewHolder, position: Int) {
        holder.bind(roomServiceList?.get(position), mListener)
    }

}

