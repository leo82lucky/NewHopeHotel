package com.example.newhopehotel.roomService.viewMorningCallList

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
import com.example.newhopehotel.databinding.FragmentMorningCallListBinding
import org.jetbrains.anko.design.longSnackbar

const val CHOSEN_MC = "chosenMC"

class MorningCallListFragment : Fragment(), MorningCallAdapter.MorningCallClickListener {

    private lateinit var morningCallActivityViewModel: MorningCallViewModel

    private lateinit var mAdapter: MorningCallAdapter
    private lateinit var binding: FragmentMorningCallListBinding
    private var mMorningCallList: List<MorningCallEntity>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_morning_call_list, container, false)

        morningCallActivityViewModel = ViewModelProvider(this).get(MorningCallViewModel::class.java)

        mAdapter = MorningCallAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), LinearLayoutManager.VERTICAL
        )
        with(binding.recycler) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }

        //When fab clicked, open AddToyFragment
        binding.fab.setOnClickListener { openAddToyFrag(AddMorningCallFragment()) }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //Get the view model instance and pass it to the binding implementation
        binding.uiState = morningCallActivityViewModel.uiState

        //Claim list of toys from view model

        morningCallActivityViewModel.morningCallList?.observe(viewLifecycleOwner, { morningCallEntries ->
            if (morningCallEntries.isNullOrEmpty()) {
                morningCallActivityViewModel.uiState.set(UIState.EMPTY)
            } else {
                morningCallActivityViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.morningCallList = morningCallEntries
                mMorningCallList = morningCallEntries
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
                val morningCallToErase = mMorningCallList!![position]

                //Then delete the toy
                morningCallActivityViewModel.deleteMorningCall(morningCallToErase)

                //Show a snack bar for undoing delete
                coordinator?.longSnackbar(R.string.morning_call_snack, R.string.undo) {
                    morningCallActivityViewModel.insertMorningCall(morningCallToErase)
                }
            }
        }).attachToRecyclerView(binding.recycler)
    }



    private fun openAddToyFrag(frag: AddMorningCallFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }



    override fun onMorningCallClicked(chosenToy: MorningCallEntity) {
        //Pass chosen toy id to the AddToyFragment
        val args = Bundle()
        args.putParcelable(CHOSEN_MC, chosenToy)
        val frag = AddMorningCallFragment()
        frag.arguments = args

        //Open AddToyFragment in edit form
        openAddToyFrag(frag)
    }
}