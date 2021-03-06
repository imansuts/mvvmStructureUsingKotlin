package com.admin.mvvmwithoutdagger.data.datasource.db

import android.util.Log

import com.admin.mvvmwithoutdagger.data.model.db.UserDataModel

import io.reactivex.Observable
import io.reactivex.Single

class AppDbHelper(private val mAppDatabase: AppDataBase?) {


//    val allUsers: Observable<List<UserDataModel>>
//        get() = Observable.fromCallable {
//            Log.d("inserted_data_size", ": " + mAppDatabase!!.userDao().loadAll().size)
//            mAppDatabase.userDao().loadAll()
//        }

    val allUsers: Single<List<UserDataModel>>
        get() = Single.fromCallable {
            Log.d("inserted_data_size", ": " + mAppDatabase!!.userDao().loadAll().size)
            mAppDatabase.userDao().loadAll()
        }

    fun insertUser(user: UserDataModel): Observable<Boolean> {
        return Observable.fromCallable {
            Log.d("check_inserted_data", ": " + user.name!!)
            mAppDatabase!!.userDao().insert(user)
            true
        }
    }
}
