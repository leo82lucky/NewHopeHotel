package com.example.newhopehotel.customerFeedback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.database.FeedbackEntity
import com.example.newhopehotel.databinding.FeedbacksBinding
import com.example.newhopehotel.databinding.ViewedFeedbacksBinding
import kotlinx.android.synthetic.main.viewed_feedbacks.view.*

class ViewedFeedbackAdapter (
    private val mListener: FeedbackScene1
    ) : RecyclerView.Adapter<ViewedFeedbackAdapter.ViewedFeedbackListViewHolder>() {

    var viewedFeedbackList: List<FeedbackEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedFeedbackAdapter.ViewedFeedbackListViewHolder = ViewedFeedbackAdapter.ViewedFeedbackListViewHolder.from(parent)

    override fun getItemCount(): Int {
        return viewedFeedbackList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewedFeedbackAdapter.ViewedFeedbackListViewHolder, position: Int) = holder.bind(viewedFeedbackList?.get(position), mListener)

    class ViewedFeedbackListViewHolder(private val binding: ViewedFeedbacksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentViewedFeedbackList: FeedbackEntity?, clickListener: ViewedFeedbackAdapter.ViewedFeedbackEditClickListener) {

            binding.viewedFeedbackListItem = currentViewedFeedbackList
            binding.fbEditItemClick = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewedFeedbackListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ViewedFeedbacksBinding>(
                    layoutInflater,
                    R.layout.viewed_feedbacks,
                    parent,
                    false
                )
                return ViewedFeedbackListViewHolder(binding)
            }
        }
    }

    interface ViewedFeedbackEditClickListener {
        fun onViewedFeedbackEditClicked(chosenToy: FeedbackEntity)
    }
}