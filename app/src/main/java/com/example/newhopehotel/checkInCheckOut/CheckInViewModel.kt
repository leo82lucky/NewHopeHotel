package com.example.newhopehotel.checkInCheckOut


import android.app.Application
import android.util.Log
import android.widget.EditText
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
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


class CheckInViewModel(private val repository: HotelRepository, application: Application) :
    AndroidViewModel(application), Observable {

    init {
        Log.i("MYTAG", "check in VM init")
    }

    var customersLiveData = MutableLiveData<Array<CheckInCheckOutEntity>?>()

    @Bindable
    val inputCustomerName = MutableLiveData<String>()

    @Bindable
    val inputIC = MutableLiveData<String>()

    @Bindable
    val inputContactNo = MutableLiveData<String>()

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

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _navigatetoCICOList = MutableLiveData<Boolean>()

    val navigatetoCICOList: LiveData<Boolean>
        get() = _navigatetoCICOList

    fun confirmButton() {
        Log.i("MYTAG", "Inside CONFIRM BUTTON")
        if (inputCustomerName.value == null || inputIC.value == null || inputContactNo.value == null
        ) {
            Log.i("MYTAG", "Name:" + inputCustomerName.value)
            Log.i("MYTAG", "Name:" + inputIC.value)
            Log.i("MYTAG", "Name:" + inputContactNo.value)
            _errorToast.value = true
        } else {
            uiScope.launch {
                val customerNames = repository.getCustomerName(inputCustomerName.value!!)

                Log.i("MYTAG", customersLiveData.value.toString() + "ASDFASDFASDFASDF")
                Log.i("MYTAG", "In confirmButton()")
                val customerName = inputCustomerName.value!!
                val ic = inputIC.value!!
                val contactNo = inputContactNo.value!!
//                val roomID = inputRoomID.value!!
//                val roomType = inputRoomType.value!!
//                val roomStatus = inputRoomStatus.value!!
//                val roomCardNo = inputRoomCardNo.value!!
//                val checkInTime = inputCheckInTime.value!!
//                val checkInDate = inputCheckInDate.value!!
//                val morningCall = inputMorningCall.value!!
//                val checkOutTime = inputCheckOutTime.value!!
//                val checkOutDate = inputCheckOutDate.value!!

                Log.i("MYTAG", "Confirm button clikced")
                insert(
                    CheckInCheckOutEntity(
                        0,
                        customerName,
                        ic,
                        contactNo
//                        roomID,
//                        roomType,
//                        roomStatus,
//                        roomCardNo
//                        morningCall
                    )
                )
                inputCustomerName.value = null
                inputIC.value = null
                inputContactNo.value = null
//                inputRoomID.value = null
//                inputRoomType.value = null
//                inputRoomStatus.value = null
//                inputRoomCardNo.value = null
//                inputCheckInTime.value = null
//                inputCheckInDate.value = null
//                inputCheckOutTime.value = null
//                inputCheckOutDate.value = null
//                inputMorningCall.value = null
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
        repository.insertCustomer(customer)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

//    @BindingAdapter("android:text", requireAll = false)
//    fun EditText.bindAnyToString(value: Any?) {
//        value?.let {
//            setText(value.toString())
//        }
//    }
//
//    @InverseBindingAdapter(attribute = "android:text")
//    fun EditText.getIntFromBinding(): Int? {
//        val result=text.toString()
//
//        return try {
//            result.toInt()
//        }catch (e:Exception){
//            0
//        }
//    }

    //    @InverseBindingAdapter(attribute = "android:selectedItemPosition")
//    fun getSpinnerItem(spinner: Spinner): String? {
//        return spinner.selectedItem as String
//    }
//
//    @BindingAdapter("android:selectedItemPosition", requireAll = false)
//    fun Spinner.bindSpinnerItemToString(value: Any?) {
//        value?.let {
//            value.toString()
//        }
//    }

//    @InverseBindingAdapter(attribute = "android:text")
//    fun getText(view: EditText): Int {
//        return view.text.toString().toInt()
//    }

}