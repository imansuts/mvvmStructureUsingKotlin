package com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter

class ListItemLoadingViewModel(private val mListener: OpenSourceEmptyItemViewModelListener) {
    fun onRetryClick() {
        mListener.onRetryClick()
    }

    interface OpenSourceEmptyItemViewModelListener {
        fun onRetryClick()
    }

}