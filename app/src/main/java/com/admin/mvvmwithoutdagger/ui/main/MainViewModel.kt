package com.admin.mvvmwithoutdagger.ui.main

import android.content.Context
import android.view.View

import com.admin.mvvmwithoutdagger.ui.base.BaseViewModel

class MainViewModel(var context: Context) : BaseViewModel<MainNavigator>() {

    fun onViewClick(view: View) {
        navigator.onClick()
    }
}
