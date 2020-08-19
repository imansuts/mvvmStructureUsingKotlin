package com.admin.mvvmwithoutdagger.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter.ListItemsViewModel
import com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter.RecyclerViewAdapter

object BindingUtils {

    @JvmStatic
    @BindingAdapter("adapter")
    fun addOpenSourceItems(recyclerView: RecyclerView, openSourceItems: List<ListItemsViewModel>?) {
        val adapter = recyclerView.adapter as RecyclerViewAdapter?
        if (adapter != null) {
            adapter.clearItems()
            adapter.addItems(openSourceItems!!)
        }
    }
}