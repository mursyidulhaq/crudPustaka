package com.mursyidul.submissionweek8.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mursyidul.submissionweek8.local.user.User
import com.mursyidul.submissionweek8.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val repositoryUser = UserRepository(application.applicationContext)
    var responseUser = MutableLiveData<List<User>>()
    var responseDataUser: LiveData<List<User>> = responseUser

    var responseActionUser = MutableLiveData<User>()
    var  responseDataActionUser :LiveData<User> = responseActionUser

    var isUserError = MutableLiveData<Throwable>()
    var  isDataUserError : LiveData<Throwable> = isUserError

    var isPasswordEmpty = MutableLiveData<Boolean>()
    var isDataPasswordEmpty : LiveData<Boolean> = isPasswordEmpty

    var password_notmatch = MutableLiveData<Boolean>()
    var passwordnotmatch: LiveData<Boolean> = password_notmatch

    var password_less = MutableLiveData<Boolean>()
    var passwordless: LiveData<Boolean> = password_less

    var empty_email = MutableLiveData<Boolean>()
    var emptyemail: LiveData<Boolean> = empty_email

    var empty_name = MutableLiveData<Boolean>()
    var emptyname: LiveData<Boolean> = empty_name

    var wrong_email = MutableLiveData<Boolean>()
    var wrongemail: LiveData<Boolean> = wrong_email

    fun getEmail(email: String, name: String) {
        if  (name.isEmpty()) {
            empty_name.value = true
        } else if (email.isEmpty()) {
            empty_email.value = true
        } else if (cekEmail(email) == false) {
            wrong_email.value = true
        } else {
            repositoryUser.validasiEmail(email, {
                responseActionUser.value = it

            }, {
                isUserError.value = it
            })
        }
    }


    fun loginUser(email: String, password: String) {
        if(email.isEmpty()){
            empty_email.value=true
        }else if(password.isEmpty()){
            isPasswordEmpty.value=true
        }else {
            repositoryUser.login(email, password, {
                responseActionUser.value = it

            }, {
                isUserError.value = it


            })
        }
    }

    fun registerUser(
        id: Int?,
        nama: String,
        email: String,
        password: String,
        passwordKonfirmasi: String
    ) {
        if (password.isEmpty()) {
            isPasswordEmpty.value = true
        } else if (password != passwordKonfirmasi) {
            password_notmatch.value = true
        } else if (password.length < 6) {
            password_less.value = true
        } else {
            repositoryUser.register(id, nama, email, password,passwordKonfirmasi, {
                responseActionUser.value = it
            }, {
                isUserError.value = it

            })
        }
    }






    private fun cekEmail(email: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true
        } else {
            return false
        }
    }





}