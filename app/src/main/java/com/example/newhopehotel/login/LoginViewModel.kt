package com.example.newhopehotel.login

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newhopehotel.database.HotelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: HotelRepository, application: Application) :
    AndroidViewModel(application), Observable {

    val users = repository.users

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoRegister = MutableLiveData<Boolean>()

    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword

    private val _navigatetoHomePage = MutableLiveData<Boolean>()
    val navigateToHomePage: LiveData<Boolean>
        get() = _navigatetoHomePage

    private val _navigatetoHomePageOnlyHousekeeping = MutableLiveData<Boolean>()
    val navigatetoHomePageOnlyHousekeeping: LiveData<Boolean>
        get() = _navigatetoHomePageOnlyHousekeeping

    fun signUP() {
        _navigatetoRegister.value = true
    }

    fun loginButton() {
        if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (inputUsername.value == "admin") {
                    if (inputPassword.value == "admin123") {
                        inputUsername.value = null
                        inputPassword.value = null
                        _navigatetoHomePage.value = true
                    } else {
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    if (usersNames != null) {
                        if (usersNames.passwrd == inputPassword.value) {
                            inputUsername.value = null
                            inputPassword.value = null
                            _navigatetoHomePageOnlyHousekeeping.value = true
                        } else {
                            _errorToastInvalidPassword.value = true
                        }
                    } else {
                        _errorToastUsername.value = true
                    }
                }
            }
        }
    }


    fun doneNavigatingRegister() {
        _navigatetoRegister.value = false
    }

    fun doneNavigatingHomePage() {
        _navigatetoHomePage.value = false
    }

    fun doneNavigatingHomePageOnlyHousekeeping() {
        _navigatetoHomePageOnlyHousekeeping.value = false
    }

    fun donetoast() {
        _errorToast.value = false
    }


    fun donetoastErrorUsername() {
        _errorToastUsername.value = false
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword.value = false
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}
