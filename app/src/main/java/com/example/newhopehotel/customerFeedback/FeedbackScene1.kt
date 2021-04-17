package com.example.newhopehotel.customerFeedback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.FeedbackEntity
import com.example.newhopehotel.databinding.FragmentCustomerFeedback1Binding
import com.example.newhopehotel.housekeeping.CHOSEN_WORKER
import com.example.newhopehotel.housekeeping.RoomsToCleanFragment
import org.jetbrains.anko.design.longSnackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedbackEdit.newInstance] factory method to
 * create an instance of this fragment.
 */
const val CHOSEN_FEEDBACK = "chosenFeedback"

class FeedbackScene1 : Fragment(), FeedbackAdapter.FeedbackEditClickListener, ViewedFeedbackAdapter.ViewedFeedbackEditClickListener {

    private lateinit var feedbackListViewModel: FeedbackScene1ViewModel
    private lateinit var viewedFeedbackListViewModel: FeedbackScene1ViewModel

    private lateinit var mAdapter: FeedbackAdapter
    private lateinit var viewedAdapter: ViewedFeedbackAdapter
    private lateinit var binding: FragmentCustomerFeedback1Binding
    private var mFeedbackList: List<FeedbackEntity>? = null
    private var mViewedFeedbackList: List<FeedbackEntity>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_feedback1, container, false)

        feedbackListViewModel = ViewModelProvider(this).get(FeedbackScene1ViewModel::class.java)
        viewedFeedbackListViewModel = ViewModelProvider(this).get(FeedbackScene1ViewModel::class.java)

        mAdapter = FeedbackAdapter(this)
        viewedAdapter = ViewedFeedbackAdapter(this)

        if(feedbackListViewModel.feedbackList == null) {
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    1,
                    "Jonhny",
                    "10/11/2021",
                    "Hi",
                    "Hello"
                )
            )
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    2,
                    "Johnson",
                    "10/11/2021",
                    "Testing 2",
                    ""
                )
            )
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    3,
                    "John",
                    "11/11/2021",
                    "Testing 3",
                    ""
                )
            )
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    4,
                    "Johnii",
                    "11/11/2021",
                    "Testing 3",
                    "Hi"
                )
            )
        }

        binding.rvFeedbackList.adapter = mAdapter
        binding.rvFeedbackList.layoutManager = LinearLayoutManager(this.context)

        binding.fbButton.setOnClickListener{
            binding.rvFeedbackList.adapter = mAdapter
            binding.rvFeedbackList.layoutManager = LinearLayoutManager(this.context)
        }

        binding.viewedFbButton.setOnClickListener{
            binding.rvFeedbackList.adapter = viewedAdapter
            binding.rvFeedbackList.layoutManager = LinearLayoutManager(this.context)
        }

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

                val feedbackListToErase = mFeedbackList!![position]

                feedbackListViewModel.deleteFeedbackList(feedbackListToErase)
                feedbackListViewModel.updateFeedbackList(feedbackListToErase)

//                coordinator?.longSnackbar("One task completed", "UNDO") {
//                    feedbackListViewModel.insertCleaningList(cleaningListToErase)
//                }
            }
        }).attachToRecyclerView(binding.rvFeedbackList)

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

                val viewedFeedbackListToErase = mViewedFeedbackList!![position]

                viewedFeedbackListViewModel.deleteFeedbackList(viewedFeedbackListToErase)
                viewedFeedbackListViewModel.updateFeedbackList(viewedFeedbackListToErase)
//                coordinator?.longSnackbar("One task completed", "UNDO") {
//                    feedbackListViewModel.insertCleaningList(cleaningListToErase)
//                }
            }
        }).attachToRecyclerView(binding.rvFeedbackList)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.uiState = feedbackListViewModel.uiState

        var temp: LiveData<List<FeedbackEntity>>? = feedbackListViewModel.selectFeedback("")
        temp?.observe(viewLifecycleOwner, { temp2 ->
            if (temp2.isNullOrEmpty()) {
                feedbackListViewModel.uiState.set(UIState.EMPTY)
            } else {
                feedbackListViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.feedbackList = temp2
                mFeedbackList = temp2
            }
        })

        var temp3: LiveData<List<FeedbackEntity>>? = viewedFeedbackListViewModel.selectViewedFeedback("")
        temp3?.observe(viewLifecycleOwner, { temp4 ->
            if (temp4.isNullOrEmpty()) {
                viewedFeedbackListViewModel.uiState.set(UIState.EMPTY)
            } else {
                viewedFeedbackListViewModel.uiState.set(UIState.HAS_DATA)
                viewedAdapter.viewedFeedbackList = temp4
                mViewedFeedbackList = temp4
            }
        })

        //arrangeCleaningListByTest(cleaningListViewModel.cleaningList)
    }

    override fun onFeedbackEditClicked(chosenToy: FeedbackEntity) {
        val args = Bundle()
        args.putParcelable(CHOSEN_FEEDBACK, chosenToy)
        val frag = FeedbackEdit()
        frag.arguments = args
        openFeedbackEdit(frag)
    }

    override fun onViewedFeedbackEditClicked(chosenToy: FeedbackEntity) {
        val args = Bundle()
        args.putParcelable(CHOSEN_FEEDBACK, chosenToy)
        val frag = FeedbackEdit()
        frag.arguments = args
        openFeedbackEdit(frag)
    }

    private fun openFeedbackEdit(frag: FeedbackEdit) {
        fragmentManager?.transaction {
            replace(R.id.feedback_container, frag)
            addToBackStack(null)
        }
    }




}