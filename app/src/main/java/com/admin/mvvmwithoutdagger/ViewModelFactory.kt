package com.admin.mvvmwithoutdagger

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.admin.mvvmwithoutdagger.ui.main.MainViewModel
import com.admin.mvvmwithoutdagger.ui.main.fragment1.FragmentOneViewModel


class ViewModelFactory(var context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(context) as T
        }
        if (modelClass.isAssignableFrom(FragmentOneViewModel::class.java)) {
            return FragmentOneViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}