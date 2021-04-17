package com.example.newhopehotel.customerFeedback

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import com.example.newhopehotel.R
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.FeedbackEntity
import com.example.newhopehotel.database.RegisterEntity
import com.example.newhopehotel.databinding.FragmentFeedbackEditBinding
import com.example.newhopehotel.databinding.FragmentWorkerBinding
import com.example.newhopehotel.housekeeping.WorkerAdapter
import com.example.newhopehotel.housekeeping.WorkerViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*


class FeedbackEdit : Fragment() {
    private lateinit var fbEditViewModel: FeedbackEditViewModel

    private lateinit var binding: FragmentFeedbackEditBinding
    private var mFeedbackList: FeedbackEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mFeedbackList = arguments?.getParcelable(CHOSEN_FEEDBACK)

        Toast.makeText(context, mFeedbackList?.question,Toast.LENGTH_SHORT).show()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback_edit, container, false)

        binding.answerFeedback.setText(mFeedbackList?.answer)
        binding.date.text = mFeedbackList?.date
        binding.question.text = mFeedbackList?.question
        binding.username.text = mFeedbackList?.username

        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//
//        binding.uiState = fbEditViewModel.uiState
//
//        var temp: LiveData<List<FeedbackEntity>>? = feedbackListViewModel.selectFeedback("")
//        temp?.observe(viewLifecycleOwner, { temp2 ->
//            if (temp2.isNullOrEmpty()) {
//                feedbackListViewModel.uiState.set(UIState.EMPTY)
//            } else {
//                feedbackListViewModel.uiState.set(UIState.HAS_DATA)
//                mAdapter.feedbackList = temp2
//                mFeedbackList = temp2
//            }
//        })
//    }


}