package com.example.newhopehotel.roomService


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.*
import kotlinx.android.synthetic.main.activity_recycler_room_service_list.view.*
import kotlinx.android.synthetic.main.room_service_list_layout.view.*

class RoomServiceListAdapter : RecyclerView.Adapter<RoomServiceListAdapter.ViewHolder>()
{
    val  RSID: MutableList<String> = mutableListOf("RS001", "RS002", "RS003","RS004","RS005","RS006","RS007","RS008")
    val clientNames: MutableList<String> = mutableListOf("Ali", "Abu", "Ran","Kong","John","Pong","Wong","Song")
    val request: MutableList<String> =mutableListOf("Need a bottle of water", "Need a towel", "Need Dinner","The toilet is broken, it needs to be repaired","Need a TV in the room, the last one has been stolen!","Wants buffet lunch brought to the room","Having problem with a wild cat that came in","The bed is dirty, wash the bed.")
    private val clientImages=intArrayOf(R.drawable.worker_image_sample, R.drawable.worker_image_sample, R.drawable.worker_image_sample)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.room_service_list_layout, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  clientNames.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.client_name_holder.text = clientNames[position]
        holder.itemView.client_image.setImageResource(clientImages[0])
        holder.itemView.RSID_holder.text =  RSID[position]
        holder.itemView.request_holder.text = request[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }
    }
    fun addRequest(clientRequest:String)
    {
        request.add(clientRequest)
    }
    fun addClientNameAndRSID(name:String)
    {
        clientNames.add(name)
        val stringRSID=RSID.get( RSID.size-1).substring(2).toInt()+1
        RSID.add("RS"+stringRSID)
    }
}