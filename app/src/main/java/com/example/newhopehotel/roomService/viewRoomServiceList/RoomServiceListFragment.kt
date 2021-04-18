package com.example.newhopehotel.roomService.viewRoomServiceList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.newhopehotel.R
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.RoomServiceEntity
import com.example.newhopehotel.databinding.FragmentRoomServiceListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_room_service_list.*

const val CHOSEN_RS = "chosenRS"

class RoomServiceListFragment : Fragment(), RoomServiceAdapter.RoomServiceClickListener {

    private lateinit var roomServiceActivityViewModel: RoomServiceViewModel

    private lateinit var mAdapter: RoomServiceAdapter
    private lateinit var binding: FragmentRoomServiceListBinding
    private var mRoomServiceList: List<RoomServiceEntity>? = null

    init {
        retainInstance = true
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_service_list, container, false)

        roomServiceActivityViewModel = ViewModelProvider(this).get(RoomServiceViewModel::class.java)

        mAdapter = RoomServiceAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), LinearLayoutManager.VERTICAL
        )
        with(binding.recycler) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }

        binding.fab.setOnClickListener { openAddToyFrag(AddRoomServiceFragment()) }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)

        (activity as RoomService).activateRoomServiceTitle()


        binding.uiState = roomServiceActivityViewModel.uiState

        roomServiceActivityViewModel.roomServiceList?.observe(viewLifecycleOwner, { roomServiceEntries ->
            if (roomServiceEntries.isNullOrEmpty()) {
                roomServiceActivityViewModel.uiState.set(UIState.EMPTY)
                empty_imageview_room_service!!.visibility = View.VISIBLE
                no_data_room_service!!.visibility = View.VISIBLE
            } else {
                empty_imageview_room_service!!.visibility = View.GONE
                no_data_room_service!!.visibility = View.GONE
                roomServiceActivityViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.roomServiceList = roomServiceEntries
                mRoomServiceList =roomServiceEntries
            }
        })

        val coordinator: FrameLayout? = activity?.findViewById(R.id.main_container)
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val roomServiceToErase = mRoomServiceList!![position]

                roomServiceActivityViewModel.deleteRoomService(roomServiceToErase)

                Snackbar.make(viewHolder.itemView, R.string.room_service_snack, Snackbar.LENGTH_LONG).setAction(R.string.undo) {
                    roomServiceActivityViewModel.insertRoomService(roomServiceToErase)


                }.show()


            }
        }).attachToRecyclerView(binding.recycler)
    }



    private fun openAddToyFrag(frag: AddRoomServiceFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }




    override fun onRoomServiceClicked(chosenToy: RoomServiceEntity) {
        val args = Bundle()
        args.putParcelable(CHOSEN_RS, chosenToy)
        val frag = AddRoomServiceFragment()
        frag.arguments = args
        (activity as RoomService).editMode=true
        openAddToyFrag(frag)
    }




}