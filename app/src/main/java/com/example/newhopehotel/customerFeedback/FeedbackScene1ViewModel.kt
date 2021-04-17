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

    fun getFeedbackListByUserID(feedbackID: Int): LiveData<FeedbackEntity> {
        return mRepo.getFeedbackListByUserID(feedbackID)
    }

    fun insertFeedbackList(feedbackList: FeedbackEntity) {
        mRepo.insertFeedbackList(feedbackList)
    }

    fun deleteFeedbackList(feedbackList: FeedbackEntity) {
        mRepo.deleteFeedbackList(feedbackList)
    }

    fun selectFeedback(str: String): LiveData<List<FeedbackEntity>>{
        return mRepo.getSelectedFeedbacks(str)
    }

    fun selectViewedFeedback(str: String): LiveData<List<FeedbackEntity>>{
        return mRepo.getSelectedViewedFeedbacks(str)
    }

    fun updateFeedbackList(feedbackList: FeedbackEntity){
        mRepo.updateFeedbackList(feedbackList)
    }

}