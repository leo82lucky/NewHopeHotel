package com.example.newhopehotel.housekeeping

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newhopehotel.R
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CleaningListEntity
import com.example.newhopehotel.database.RegisterEntity
import com.example.newhopehotel.databinding.FragmentWorkerBinding
import kotlinx.android.synthetic.main.fragment_worker.*

const val CHOSEN_WORKER = "chosenWorker"

class WorkerFragment : Fragment(), WorkerAdapter.WorkerClickListener {

    private lateinit var workerViewModel: WorkerViewModel

    private lateinit var mAdapter: WorkerAdapter
    private lateinit var binding: FragmentWorkerBinding
    private var mWorkerList: List<RegisterEntity>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_worker, container, false)

        workerViewModel = ViewModelProvider(this).get(WorkerViewModel::class.java)

        mAdapter = WorkerAdapter(this)

        binding.rvWorkers.adapter = mAdapter
        binding.rvWorkers.layoutManager = LinearLayoutManager(this.context)

        binding.btnCleaningList.setOnClickListener {
            openCleaningListFrag(CleaningListFragment())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.uiState = workerViewModel.uiState

        var temp: LiveData<List<RegisterEntity>>? = workerViewModel.workerList
        temp?.observe(viewLifecycleOwner, { temp2 ->
            if (temp2.isNullOrEmpty()) {
                workerViewModel.uiState.set(UIState.EMPTY)
            } else {
                workerViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.workerList = temp2
                mWorkerList = temp2
            }
        })
    }

    override fun onWorkerClicked(chosenToy: RegisterEntity) {
        val args = Bundle()
        args.putParcelable(CHOSEN_WORKER, chosenToy)
        val frag = RoomsToCleanFragment()
        frag.arguments = args
        openRoomsToCleanFrag(frag)
    }

    private fun openCleaningListFrag(frag: CleaningListFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }

    private fun openRoomsToCleanFrag(frag: RoomsToCleanFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }
}