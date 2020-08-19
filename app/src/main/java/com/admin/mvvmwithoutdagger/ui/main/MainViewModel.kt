package com.admin.mvvmwithoutdagger.ui.main

import android.view.View

import com.admin.mvvmwithoutdagger.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<MainNavigator>() {

    fun onViewClick(view: View) {
        navigator.onClick()
    }
}
