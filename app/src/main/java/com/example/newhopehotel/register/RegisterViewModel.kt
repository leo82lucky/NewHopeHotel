package com.example.newhopehotel.register

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.newhopehotel.database.RegisterEntity
import com.example.newhopehotel.database.HotelRepository
import kotlinx.coroutines.*
import java.util.regex.Pattern


class RegisterViewModel(private val repository: HotelRepository, application: Application) :
    AndroidViewModel(application), Observable {

    init {
        Log.i("MYTAG", "init")
    }


    private var userdata: String? = null

    var userDetailsLiveData = MutableLiveData<Array<RegisterEntity>?>()

    @Bindable
    val inputFirstName = MutableLiveData<String>()

    @Bindable
    val inputLastName = MutableLiveData<String>()

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastFormatUsername = MutableLiveData<Boolean>()

    val errotoastFormatUsername: LiveData<Boolean>
        get() = _errorToastFormatUsername

    private val _errorToastFormatPassword = MutableLiveData<Boolean>()

    val errotoastFormatPassword: LiveData<Boolean>
        get() = _errorToastFormatPassword

    fun sumbitButton() {
        if (inputFirstName.value == null || inputLastName.value == null || inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else if (!inputUsername.value!!.isUsernameValid()) {
            _errorToastFormatUsername.value = true
        } else if (!inputPassword.value!!.isPasswordValid()) {
            _errorToastFormatPassword.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    _errorToastUsername.value = true
                } else {
                    val firstName = inputFirstName.value!!
                    val lastName = inputLastName.value!!
                    val username = inputUsername.value!!
                    val password = inputPassword.value!!
                    insert(RegisterEntity(0, firstName, lastName, username, password, 0))
                    inputFirstName.value = null
                    inputLastName.value = null
                    inputUsername.value = null
                    inputPassword.value = null
                    _navigateto.value = true
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigating() {
        _navigateto.value = false
    }

    fun donetoast() {
        _errorToast.value = false
    }

    fun donetoastUserName() {
        _errorToast.value = false
    }

    fun donetoastFormatUserName() {
        _errorToastFormatUsername.value = false
    }

    fun donetoastFormatPassword() {
        _errorToastFormatPassword.value = false
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    private var PASSWORD_PATTERN: Pattern =
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).{4,}\$")

    private fun CharSequence.isPasswordValid(): Boolean = PASSWORD_PATTERN.matcher(this).find()

    private var USERNAME_PATTERN: Pattern =
        Pattern.compile("^[a-z0-9_-]{3,16}\$")

    private fun CharSequence.isUsernameValid(): Boolean = USERNAME_PATTERN.matcher(this).find()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}