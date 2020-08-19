package com.admin.mvvmwithoutdagger.ui.main.fragment1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.admin.mvvmwithoutdagger.data.model.db.UserDataModel
import com.admin.mvvmwithoutdagger.ui.base.BaseViewModel
import com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter.ListItemsViewModel
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.internal.functions.ObjectHelper
import io.reactivex.internal.operators.observable.ObservableSingleSingle
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.MediaType
import okhttp3.RequestBody

class FragmentOneViewModel : BaseViewModel<FragmentNavigator>() {

    private val TAG = "FragmentOneViewModel"
    private val openSourceItemsLiveData = MutableLiveData<List<ListItemsViewModel>>()

    // Below two functions are for testing purpose
//    fun getUserData() {
//        compositeDisposable.add(appDataBase.allUsers.subscribeOn(_scheduler_io).observeOn(_scheduler_ui).subscribe({ userDataModels ->
//            Log.d("check_db_size", ": " + userDataModels.size)
//            openSourceItemsLiveData.setValue(userDataModels)
//        }, { }))
//    }

    fun getUserData() {
        compositeDisposable.add(fromObservable<List<UserDataModel>>(appDataBase.allUsers)
                .map({ userDataModels-> userDataModels })
                .flatMap { getViewModelList(it) }
                .subscribeOn(_scheduler_io)
                .observeOn(_scheduler_ui)
                .subscribe( { userDataModels->
                    Log.d("check_db_size", ": " + userDataModels.size)
                    openSourceItemsLiveData.setValue(userDataModels)
                } ,  { Log.d(TAG, "getUserData_throwable: " + it.message) } ))
    }

    fun getListData(): LiveData<List<ListItemsViewModel>?>? {
        return openSourceItemsLiveData
    }

    fun saveUserData(userDataModel: UserDataModel) {
        compositeDisposable.add(appDataBase.insertUser(userDataModel).subscribeOn(_scheduler_io).observeOn(_scheduler_ui).subscribe({ userDataModels ->
            Log.d("check_result_db", ": " + userDataModels!!)
            this@FragmentOneViewModel.getUserData()
        }, { throwable ->
            Log.d("check_result_db_err", ": " + throwable.message)
            this@FragmentOneViewModel.getUserData()
        }))
    }


    fun fetchUsersList() {
        val body = RequestBody.create(MediaType.parse("application/json"), "")
        val disposable = apiServiceWithGsonFactory.getspecilitylist(body)
                .subscribeOn(_scheduler_io)
                .observeOn(_scheduler_ui)
                .subscribe({ response ->
                    if (response != null) {
                        // Store last login time
                        Log.d("check_response", ": " + Gson().toJson(response))
                    } else {
                        Log.d("check_response", ": null response")
                    }
                }, { throwable -> Log.d("check_response_error", ": " + throwable.message) })

        compositeDisposable.add(disposable)
    }


    private fun getViewModelList(repoList: List<UserDataModel>): Single<List<ListItemsViewModel>> {
        return Observable.fromIterable(repoList)
                .map { repo ->
                    ListItemsViewModel(
                            repo.name)
                }.toList()
    }

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> fromObservable(observableSource: ObservableSource<out T>?): Single<T?> {
        ObjectHelper.requireNonNull(observableSource, "observableSource is null")
        return RxJavaPlugins.onAssembly(ObservableSingleSingle(observableSource, null))
    }
}
