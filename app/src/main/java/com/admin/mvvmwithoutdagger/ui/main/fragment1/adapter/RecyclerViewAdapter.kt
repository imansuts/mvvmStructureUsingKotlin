package com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.admin.mvvmwithoutdagger.databinding.SingleRowItemBinding
import com.admin.mvvmwithoutdagger.databinding.SingleRowItemLoadingBinding
import com.admin.mvvmwithoutdagger.ui.base.BaseViewHolder
import com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter.ListItemLoadingViewModel.OpenSourceEmptyItemViewModelListener
import java.util.*

class RecyclerViewAdapter(private val activity: Activity) : RecyclerView.Adapter<BaseViewHolder>() {
    private val listItemsViewModels: MutableList<ListItemsViewModel>
    private var mListener: OpenSourceAdapterListener? = null
    override fun getItemCount(): Int {
        return if (!listItemsViewModels.isEmpty()) {
            listItemsViewModels.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!listItemsViewModels.isEmpty()) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val openSourceViewBinding: SingleRowItemBinding = SingleRowItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(openSourceViewBinding)
            }
            VIEW_TYPE_EMPTY -> {
                val emptyViewBinding: SingleRowItemLoadingBinding = SingleRowItemLoadingBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                ItemLoadingViewHolder(emptyViewBinding)
            }
            else -> {
                val emptyViewBinding: SingleRowItemLoadingBinding = SingleRowItemLoadingBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                ItemLoadingViewHolder(emptyViewBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun clearItems() {
        listItemsViewModels.clear()
    }

    fun addItems(repoList: List<ListItemsViewModel>) {
        listItemsViewModels.addAll(repoList)
        notifyDataSetChanged()
    }

    interface OpenSourceAdapterListener {
        fun onRetryClick()
    }

    fun setListener(listener: OpenSourceAdapterListener?) {
        mListener = listener
    }

    inner class ItemViewHolder(binding: SingleRowItemBinding) : BaseViewHolder(binding.getRoot()), View.OnClickListener {
        private val mBinding: SingleRowItemBinding
        override fun onBind(position: Int) {
            val mOpenSourceItemViewModel = listItemsViewModels[position]
            mBinding.setViewModel(mOpenSourceItemViewModel)
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings()
            mBinding.image.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (listItemsViewModels[adapterPosition].data.get() != null) {
                try {
                    Toast.makeText(activity, listItemsViewModels[adapterPosition].data.get(), Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.d(TAG, "onClick_error: " + e.message)
                }
            }
        }

        init {
            mBinding = binding
        }
    }

    inner class ItemLoadingViewHolder(binding: SingleRowItemLoadingBinding) : BaseViewHolder(binding.getRoot()), OpenSourceEmptyItemViewModelListener {
        private val mBinding: SingleRowItemLoadingBinding
        override fun onBind(position: Int) {
            val mOpenSourceItemViewModel = ListItemLoadingViewModel(this)
            mBinding.setViewModel(mOpenSourceItemViewModel)
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings()
        }

        override fun onRetryClick() {
            Log.d(TAG, "onClick_loading_retry_adapter: " + true)
            mListener!!.onRetryClick()
        }

        init {
            mBinding = binding
        }
    }

    companion object {
        private const val TAG = "RecyclerViewAdapter"
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_NORMAL = 1
    }

    init {
        listItemsViewModels = ArrayList()
    }
}