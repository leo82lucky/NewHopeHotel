package com.example.newhopehotel.checkInCheckOut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.LiveData
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

        binding.fab.setOnClickListener { openAddCicoFrag(AddCicoFragment()) }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.uiState = cicoActivityViewModel.uiState

        arrangeCicoListByStatus(cicoActivityViewModel.cicoList)

        binding.buttonAllStatus.setOnClickListener {
            binding.buttonAllStatus.setBackgroundColor(resources.getColor(R.color.green_pigment))
            binding.buttonAvailableStatus.setBackgroundColor(resources.getColor(R.color.independence_grey))
            binding.buttonUnavailableStatus.setBackgroundColor(resources.getColor(R.color.independence_grey))
            arrangeCicoListByStatus(cicoActivityViewModel.cicoList)
        }

        binding.buttonAvailableStatus.setOnClickListener {
            binding.buttonAllStatus.setBackgroundColor(resources.getColor(R.color.independence_grey))
            binding.buttonAvailableStatus.setBackgroundColor(resources.getColor(R.color.green_pigment))
            binding.buttonUnavailableStatus.setBackgroundColor(resources.getColor(R.color.independence_grey))
            arrangeCicoListByStatus(cicoActivityViewModel.cicoStatusAvailable)
        }

        binding.buttonUnavailableStatus.setOnClickListener {
            binding.buttonAllStatus.setBackgroundColor(resources.getColor(R.color.independence_grey))
            binding.buttonAvailableStatus.setBackgroundColor(resources.getColor(R.color.independence_grey))
            binding.buttonUnavailableStatus.setBackgroundColor(resources.getColor(R.color.green_pigment))
            arrangeCicoListByStatus(cicoActivityViewModel.cicoStatusUnavailable)
        }

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

                val cicoToErase = mCicoList!![position]

                cicoActivityViewModel.deleteCico(cicoToErase)

                coordinator?.longSnackbar(R.string.record_is_deleted, R.string.undo) {
                    cicoActivityViewModel.insertCico(cicoToErase)
                }
            }
        }).attachToRecyclerView(binding.recycler)
    }

    override fun onCicoClicked(chosenToy: CheckInCheckOutEntity) {
        val args = Bundle()
        args.putParcelable(CHOSEN_CICO, chosenToy)
        val frag = AddCicoFragment()
        frag.arguments = args
        openAddCicoFrag(frag)

    }

    private fun openAddCicoFrag(frag: AddCicoFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }

    private fun arrangeCicoListByStatus(status: LiveData<List<CheckInCheckOutEntity>>?) {
        status?.observe(viewLifecycleOwner, { cicoByStatus ->
            if (cicoByStatus.isNullOrEmpty()) {
                cicoActivityViewModel.uiState.set(UIState.EMPTY)
            } else {
                cicoActivityViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.cicoList = cicoByStatus
                mCicoList = cicoByStatus
            }
        })
    }
}