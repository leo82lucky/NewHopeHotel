package com.example.newhopehotel.customerFeedback

import android.app.Application
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.FeedbackEntity
import com.example.newhopehotel.database.HotelRepository
import com.example.newhopehotel.utils.provideRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FeedbackEditViewModel(application: Application) :
    AndroidViewModel(application) {

    private val mRepo: HotelRepository = provideRepository(application)

    val uiState = ObservableField(UIState.LOADING)

    fun getFeedbackListByUserID(feedbackID: Int): LiveData<FeedbackEntity> {
        return mRepo.getFeedbackListByUserID(feedbackID)
    }

    fun insertFeedbackList(feedbackList: FeedbackEntity) {
        mRepo.insertFeedbackList(feedbackList)
    }

    fun updateFeedbackList(feedbackList: FeedbackEntity){
        mRepo.updateFeedbackList(feedbackList)
    }



}