package com.admin.mvvmwithoutdagger.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

import com.admin.mvvmwithoutdagger.ApplicationClass
import com.admin.mvvmwithoutdagger.data.datasource.api.ApiService
import com.admin.mvvmwithoutdagger.data.datasource.db.AppDbHelper
import com.admin.mvvmwithoutdagger.data.datasource.sharedpref.AppSharedPref

import java.lang.ref.WeakReference

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel<N> : ViewModel() {

    protected val compositeDisposable: CompositeDisposable

    val isLoading = ObservableBoolean()

    private var mNavigator: WeakReference<N>? = null

    var navigator: N
        get() = mNavigator!!.get()!!
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    protected val apiServiceWithJacksonFactory: ApiService
        get() = ApplicationClass.instance!!.apiServiceWithJacksonFactory

    val apiServiceWithGsonFactory: ApiService
        get() = ApplicationClass.instance!!.apiServiceWithGsonFactory

    val appSharedPref: AppSharedPref?
        get() = ApplicationClass.instance!!.appSharedPref

    protected val appDataBase: AppDbHelper
        get() = AppDbHelper(ApplicationClass.instance!!.appDatabase)


    protected val _scheduler_computation: Scheduler
        get() = Schedulers.computation()

    protected val _scheduler_io: Scheduler
        get() = Schedulers.io()

    val _scheduler_ui: Scheduler
        get() = AndroidSchedulers.mainThread()

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }
}
