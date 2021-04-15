package com.example.newhopehotel.customerFeedback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.FeedbackEntity
import com.example.newhopehotel.databinding.FeedbacksBinding

class FeedbackAdapter(
    private val mListener: FeedbackScene1
    ) : RecyclerView.Adapter<FeedbackAdapter.FeedbackListViewHolder>() {

    var feedbackList: List<FeedbackEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackAdapter.FeedbackListViewHolder = FeedbackAdapter.FeedbackListViewHolder.from(parent)

    override fun getItemCount(): Int {
        return feedbackList?.size ?: 0
    }

    override fun onBindViewHolder(holder: FeedbackAdapter.FeedbackListViewHolder, position: Int) = holder.bind(feedbackList?.get(position), mListener)

    class FeedbackListViewHolder(private val binding: FeedbacksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentFeedbackList: FeedbackEntity?, clickListener: FeedbackAdapter.FeedbackEditClickListener) {
            binding.feedbackListItem = currentFeedbackList
            binding.fbEditItemClick = clickListener
            //binding.cleaningListItem = currentCleaningList
            //binding.cleaningListItemClick = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): FeedbackListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<FeedbacksBinding>(
                    layoutInflater,
                    R.layout.feedbacks,
                    parent,
                    false
                )
                return FeedbackListViewHolder(binding)
            }
        }
    }

    interface FeedbackEditClickListener {
        fun onFeedbackEditClicked(chosenToy: FeedbackEntity)
    }

}