package com.example.newhopehotel.checkInCheckOut

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.HotelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.sql.Date
import java.sql.Time

class CheckInViewModel(private val repository: HotelRepository, application: Application) :
    AndroidViewModel(application), Observable {

    var customersLiveData = MutableLiveData<Array<CheckInCheckOutEntity>?>()

    @Bindable
    val inputCustomerName = MutableLiveData<String>()

    @Bindable
    val inputIC = MutableLiveData<Int>()

    @Bindable
    val inputContactNo = MutableLiveData<Int>()

    @Bindable
    val inputRoomID = MutableLiveData<String>()

    @Bindable
    val inputRoomType = MutableLiveData<String>()

    @Bindable
    val inputRoomStatus = MutableLiveData<String>()

    @Bindable
    val inputRoomCardNo = MutableLiveData<String>()

//    @Bindable
//    val inputCheckInTime = MutableLiveData<Time>()
//
//    @Bindable
//    val inputCheckInDate = MutableLiveData<Date>()

    @Bindable
    val inputMorningCall = MutableLiveData<Boolean>()

//    @Bindable
//    val inputCheckOutTime = MutableLiveData<Time>()
//
//    @Bindable
//    val inputCheckOutDate = MutableLiveData<Date>()

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoCICOList = MutableLiveData<Boolean>()

    val navigatetoCICOList: LiveData<Boolean>
        get() = _navigatetoCICOList

    fun confirmButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (inputCustomerName.value == null || inputIC.value == null ||
            inputContactNo.value == null || inputRoomID.value == null || inputRoomType.value == null
            || inputRoomStatus.value == null || inputRoomCardNo.value == null || inputMorningCall.value == null
        ) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val customerNames = repository.getUserName(inputCustomerName.value!!)

                Log.i("MYTAG", customersLiveData.value.toString() + "ASDFASDFASDFASDF")
                Log.i("MYTAG", "In confirmButton()")
                val customerName = inputCustomerName.value!!
                val ic = inputIC.value!!
                val contactNo = inputContactNo.value!!
                val roomID = inputRoomID.value!!
                val roomType = inputRoomType.value!!
                val roomStatus = inputRoomStatus.value!!
                val roomCardNo = inputRoomCardNo.value!!
//                val checkInTime = inputCheckInTime.value!!
//                val checkInDate = inputCheckInDate.value!!
                val morningCall = inputMorningCall.value!!
//                val checkOutTime = inputCheckOutTime.value!!
//                val checkOutDate = inputCheckOutDate.value!!

                Log.i("MYTAG", "Confirm button clikced")
                insert(
                    CheckInCheckOutEntity(
                        0,
                        customerName,
                        ic,
                        contactNo,
                        roomID,
                        roomType,
                        roomStatus,
                        roomCardNo,
                        morningCall
                    )
                )
                inputCustomerName.value = null
                inputIC.value = null
                inputContactNo.value = null
                inputRoomID.value = null
                inputRoomType.value = null
                inputRoomStatus.value = null
                inputRoomCardNo.value = null
//                inputCheckInTime.value = null
//                inputCheckInDate.value = null
//                inputCheckOutTime.value = null
//                inputCheckOutDate.value = null
                inputMorningCall.value = null
                _navigatetoCICOList.value = true

            }
        }


    }

    fun doneNavigatingCICOList() {
        _navigatetoCICOList.value = false
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done toasting")
    }

    private fun insert(customer: CheckInCheckOutEntity): Job = viewModelScope.launch {
        repository.insert(customer)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}