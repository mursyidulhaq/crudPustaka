package com.mursyidul.submissionweek8.repository

import android.content.Context
import com.mursyidul.submissionweek8.helper.SessionManager
import com.mursyidul.submissionweek8.local.pustaka.PustakaDb
import com.mursyidul.submissionweek8.local.user.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository(val context: Context) {
    private var pustakaDb=PustakaDb.getInstance(context)
    val session = SessionManager(context)

fun login(email :String,password :String,responseHandler: (User)->Unit,errorHandler: (Throwable)->Unit){
Observable.fromCallable { pustakaDb.userDao().login(email, password) }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe({
        item ->
        responseHandler(item)
        session.nama=item.user_name
        session.login = true
    },{
        errorHandler(it)
    })
}
    fun register(id :Int?, name :String, email: String, password: String,passwordkonfirmasi:String, responseHandler: (User) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { pustakaDb.userDao().register(User(id,name,email,password)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    item->
                responseHandler(User(id,name,email,password))
            },{
                errorHandler(it)

            })
    }

    fun validasiEmail(email: String, responseHandler: (User) -> Unit, errorHandler: (Throwable) -> Unit){
        Observable.fromCallable { pustakaDb.userDao().getUser(email) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                item->
                responseHandler(item)
            },{
                errorHandler(it)

            })
    }
}