package com.example.newhopehotel.customerFeedback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import com.example.newhopehotel.customerFeedback.FeedbackAdapter
import kotlinx.android.synthetic.main.feedbacks.view.*

class FeedbackAdapter (
    var feedbackList: List<FeedbackList>
    ) : RecyclerView.Adapter<FeedbackAdapter.FeedbackListViewHolder>() {

    inner class FeedbackListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackAdapter.FeedbackListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feedbacks, parent, false)
        return FeedbackListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedbackList.size
    }

    override fun onBindViewHolder(holder: FeedbackAdapter.FeedbackListViewHolder, position: Int) {
        holder.itemView.apply {
            question.text = feedbackList[position].question
            date.text = feedbackList[position].date
            username.text = feedbackList[position].username

        }
    }

}