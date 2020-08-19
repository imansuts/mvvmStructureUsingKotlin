package com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter

import androidx.databinding.ObservableField

class ListItemsViewModel(data: String?) {
    val data = ObservableField<String?>()

    init {
        /*if (data==null || TextUtils.isEmpty(data)){
            data = "Default Text";
        }*/
        this.data.set(data)
    }
}