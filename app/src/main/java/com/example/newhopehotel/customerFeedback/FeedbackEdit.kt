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
import androidx.lifecycle.ViewModelProvider
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback_edit, container, false)

        fbEditViewModel = ViewModelProvider(this).get(FeedbackEditViewModel::class.java)

        binding.answerFeedback.setText(mFeedbackList?.answer)
        binding.date.text = mFeedbackList?.date
        binding.question.text = mFeedbackList?.question
        binding.username.text = mFeedbackList?.username

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        binding.uiState = fbEditViewModel.uiState

        var temp: LiveData<FeedbackEntity>? = arguments?.getParcelable(CHOSEN_FEEDBACK)
        temp?.observe(viewLifecycleOwner, { temp2 ->
            if (temp2 == null) {
                fbEditViewModel.uiState.set(UIState.EMPTY)
            } else {
                fbEditViewModel.uiState.set(UIState.HAS_DATA)
                mFeedbackList = temp2
                Toast.makeText(context,mFeedbackList.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }


}