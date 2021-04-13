package com.example.newhopehotel.customerFeedback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.viewed_feedbacks.view.*

class ViewedFeedbackAdapter (var viewedFeedbackList: List<ViewedFeedbackList>
    ) : RecyclerView.Adapter<ViewedFeedbackAdapter.ViewedFeedbackListViewHolder>() {

        inner class ViewedFeedbackListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedFeedbackAdapter.ViewedFeedbackListViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.viewed_feedbacks, parent, false)
            return ViewedFeedbackListViewHolder(view)
        }

        override fun getItemCount(): Int {
            return viewedFeedbackList.size
        }

        override fun onBindViewHolder(holder: ViewedFeedbackAdapter.ViewedFeedbackListViewHolder, position: Int) {
            holder.itemView.apply {
                username.text = viewedFeedbackList[position].question
                date.text = viewedFeedbackList[position].date
                question.text = viewedFeedbackList[position].question
                answer.text = viewedFeedbackList[position].answer
            }
        }
}