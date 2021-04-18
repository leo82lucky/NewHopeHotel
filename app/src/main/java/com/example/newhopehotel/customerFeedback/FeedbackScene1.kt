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

const val CHOSEN_FEEDBACK = "chosenFeedback"

class FeedbackScene1 : Fragment(), FeedbackAdapter.FeedbackEditClickListener, ViewedFeedbackAdapter.ViewedFeedbackEditClickListener {

    private lateinit var feedbackListViewModel: FeedbackScene1ViewModel
    private lateinit var viewedFeedbackListViewModel: FeedbackScene1ViewModel

    private lateinit var mAdapter: FeedbackAdapter
    private lateinit var viewedAdapter: ViewedFeedbackAdapter
    private lateinit var binding: FragmentCustomerFeedback1Binding
    private var mFeedbackList: List<FeedbackEntity>? = null
    private var mViewedFeedbackList: List<FeedbackEntity>? = null

    private var fb: Boolean = false
    private var vFb: Boolean = false

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
                    "Where is New Hope Hotel?",
                    "New Hope Hotel is located at 8 Gat Lebuh Gereja\n" +
                            "10300, Penang, Malaysia."
                )
            )
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    2,
                    "Lebron",
                    "10/11/2021",
                    "How much does a Deluxe Room Cost per night?",
                    "It is RM 900 per night for a Deluxe Hotel Room"
                )
            )
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    3,
                    "Jones Bong",
                    "11/11/2021",
                    "What sort of room service are there?",
                    "We provide all sorts of room service, from food delivery to room maintainence."
                )
            )
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    4,
                    "Oly Keng Tan",
                    "11/11/2021",
                    "Where can I book a room?",
                    "You may book a room from our official website at www.NewHopeHotel.com"
                )
            )
            feedbackListViewModel.insertFeedbackList(
                FeedbackEntity(
                    5,
                    "Mark Job",
                    "5/11/2021",
                    "Are breakfast and dinner provided?",
                    "Yes we do provide breakfast and dinner."
                )
            )
           feedbackListViewModel.insertFeedbackList(
               FeedbackEntity(
                   6,
                   "Collin Jr",
                   "6/11/2021",
                   "What are the tourist attractions close to this Hotel?",
                   "The Penang Bridge and Penang Beach"
               )
           )
           feedbackListViewModel.insertFeedbackList(
               FeedbackEntity(
                   7,
                   "Ip Mao",
                   "3/11/2021",
                   "Are there any Kung fu centers nearby the hotel?",
                   "No but we do have Gym Facilities"
               )
           )
           feedbackListViewModel.insertFeedbackList(
               FeedbackEntity(
                   8,
                   "Razore",
                   "4/11/2021",
                   "Are there any place where I can go scuba diving nearby the hotel ?",
                   ""
               )
           )
           feedbackListViewModel.insertFeedbackList(
               FeedbackEntity(
                   9,
                   "Alakazam",
                   "9/11/2021",
                   "Does The Hotel Have Any Magic Shows ?",
                   ""
               )
           )
           feedbackListViewModel.insertFeedbackList(
               FeedbackEntity(
                   10,
                   "Patrick Lau",
                   "12/11/2021",
                   "Are Swimsuits sold at the hotel?",
                   ""
               )
           )

        }

        fb= true;
        binding.rvFeedbackList.adapter = mAdapter
        binding.rvFeedbackList.layoutManager = LinearLayoutManager(this.context)

        binding.fbButton.setOnClickListener{
            fb=true
            vFb=false
            binding.rvFeedbackList.adapter = mAdapter
            binding.rvFeedbackList.layoutManager = LinearLayoutManager(this.context)
        }

        binding.viewedFbButton.setOnClickListener{
            fb=false
            vFb=true
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

                if(fb) {
                    val feedbackListToErase = mFeedbackList!![position]
                    feedbackListViewModel.deleteFeedbackList(feedbackListToErase)
                    feedbackListViewModel.updateFeedbackList(feedbackListToErase)
                }

                if(vFb) {
                    val viewedFeedbackListToErase = mViewedFeedbackList!![position]
                    viewedFeedbackListViewModel.deleteFeedbackList(viewedFeedbackListToErase)
                    viewedFeedbackListViewModel.updateFeedbackList(viewedFeedbackListToErase)
                }
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