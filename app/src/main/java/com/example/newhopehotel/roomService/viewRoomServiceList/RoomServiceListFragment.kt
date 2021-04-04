package com.example.newhopehotel.roomService.viewRoomServiceList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.newhopehotel.R
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.MorningCallEntity
import com.example.newhopehotel.database.RoomServiceEntity
import com.example.newhopehotel.databinding.FragmentRoomServiceListBinding
import org.jetbrains.anko.design.longSnackbar

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

        //When fab clicked, open AddToyFragment
        binding.fab.setOnClickListener { openAddToyFrag(AddRoomServiceFragment()) }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //Get the view model instance and pass it to the binding implementation
        binding.uiState = roomServiceActivityViewModel.uiState

        //Claim list of toys from view model

        roomServiceActivityViewModel.roomServiceList?.observe(viewLifecycleOwner, { roomServiceEntries ->
            if (roomServiceEntries.isNullOrEmpty()) {
                roomServiceActivityViewModel.uiState.set(UIState.EMPTY)
            } else {
                roomServiceActivityViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.roomServiceList = roomServiceEntries
                mRoomServiceList =roomServiceEntries
            }
        })

        //Attach an ItemTouchHelper for swipe-to-delete functionality
        val coordinator: CoordinatorLayout? = activity?.findViewById(R.id.main_coordinator)
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

                //First take a backup of the toy to erase
                //If user is swiping an item, we can assume that list is not null
                val roomServiceToErase = mRoomServiceList!![position]

                //Then delete the toy
                roomServiceActivityViewModel.deleteRoomService(roomServiceToErase)

                //Show a snack bar for undoing delete
                coordinator?.longSnackbar(R.string.room_service_snack, R.string.undo) {
                    roomServiceActivityViewModel.insertRoomService(roomServiceToErase)
                }
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
        //Pass chosen toy id to the AddToyFragment
        val args = Bundle()
        args.putParcelable(CHOSEN_RS, chosenToy)
        val frag = AddRoomServiceFragment()
        frag.arguments = args

        //Open AddToyFragment in edit form
        openAddToyFrag(frag)
    }




}