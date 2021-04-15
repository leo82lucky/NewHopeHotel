package com.example.newhopehotel.customerFeedback

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.FeedbackEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.utils.provideRepository


class FeedbackScene1ViewModel(application: Application) :
    AndroidViewModel(application) {

    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    var feedbackList: LiveData<List<FeedbackEntity>>? = null
        get() {
            return field ?: mRepo.feedbackList.also { field = it }
        }

    var feedbackListOfUserID: LiveData<List<FeedbackEntity>>? = null
        get() {
            return field ?: mRepo.getFeedbackListByUserID(1).also { field = it }
        }

    fun getFeedbackListByUserID(feedbackID: Int): LiveData<List<FeedbackEntity>> {
        return mRepo.getFeedbackListByUserID(feedbackID)
    }

    fun insertFeedbackList(feedbackList: FeedbackEntity) {
        mRepo.insertFeedbackList(feedbackList)
    }

    fun deleteFeedbackList(feedbackList: FeedbackEntity) {
        mRepo.deleteFeedbackList(feedbackList)
    }

}