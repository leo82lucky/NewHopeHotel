package com.example.newhopehotel.checkInCheckOut

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
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.databinding.FragmentCicoListBinding
import org.jetbrains.anko.design.longSnackbar

const val CHOSEN_CICO = "chosenCico"

class CicoListFragment : Fragment(), CicoAdapter.CicoClickListener {

    private lateinit var cicoActivityViewModel: CICOViewModel

    private lateinit var mAdapter: CicoAdapter
    private lateinit var binding: FragmentCicoListBinding
    private var mCicoList: List<CheckInCheckOutEntity>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cico_list, container, false)

        cicoActivityViewModel = ViewModelProvider(this).get(CICOViewModel::class.java)

        mAdapter = CicoAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), LinearLayoutManager.VERTICAL
        )
        with(binding.recycler) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }

        //When fab clicked, open AddToyFragment
        binding.fab.setOnClickListener { openAddToyFrag(AddCicoFragment()) }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //Get the view model instance and pass it to the binding implementation
        binding.uiState = cicoActivityViewModel.uiState

        //Claim list of toys from view model
        cicoActivityViewModel.cicoList?.observe(viewLifecycleOwner, { cicoEntries ->
            if (cicoEntries.isNullOrEmpty()) {
                cicoActivityViewModel.uiState.set(UIState.EMPTY)
            } else {
                cicoActivityViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.cicoList = cicoEntries
                mCicoList = cicoEntries
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
                val cicoToErase = mCicoList!![position]

                //Then delete the toy
                cicoActivityViewModel.deleteCico(cicoToErase)

                //Show a snack bar for undoing delete
                coordinator?.longSnackbar(R.string.record_is_deleted, R.string.undo) {
                    cicoActivityViewModel.insertCico(cicoToErase)
                }
            }
        }).attachToRecyclerView(binding.recycler)
    }

    override fun onCicoClicked(chosenToy: CheckInCheckOutEntity) {
        //Pass chosen toy id to the AddToyFragment
        val args = Bundle()
        args.putParcelable(CHOSEN_CICO, chosenToy)
        val frag = AddCicoFragment()
        frag.arguments = args

        //Open AddToyFragment in edit form
        openAddToyFrag(frag)
    }

    private fun openAddToyFrag(frag: AddCicoFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }
}