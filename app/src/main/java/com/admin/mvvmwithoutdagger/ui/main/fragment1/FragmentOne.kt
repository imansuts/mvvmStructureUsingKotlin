package com.admin.mvvmwithoutdagger.ui.main.fragment1

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import com.admin.mvvmwithoutdagger.BR
import com.admin.mvvmwithoutdagger.R
import com.admin.mvvmwithoutdagger.data.model.db.UserDataModel
import com.admin.mvvmwithoutdagger.databinding.FragmentOneBinding
import com.admin.mvvmwithoutdagger.ui.base.BaseFragment
import com.admin.mvvmwithoutdagger.ui.main.fragment1.adapter.RecyclerViewAdapter


class FragmentOne : BaseFragment<FragmentOneBinding, FragmentOneViewModel>(), FragmentNavigator {

    private var fragmentOneBinding: FragmentOneBinding? = null
    private var fragmentOneViewModel: FragmentOneViewModel? = null


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_one

    override val viewModel: FragmentOneViewModel
        get() {
            fragmentOneViewModel = ViewModelProviders.of(this).get(FragmentOneViewModel::class.java!!)
            return fragmentOneViewModel as FragmentOneViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentOneViewModel!!.navigator = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentOneBinding = viewDataBinding

        // Below code is for checking of retrofit, is it working properly with MVVM or not
//        fragmentOneViewModel!!.fetchUsersList()   // it is for testing of frtching data from url.. replace base url and api key (if any-optional) with yours then call api

        setUpRecycler()
        viewModel.getUserData()

        fragmentOneBinding!!.tvSubmit.setOnClickListener {
            val s = fragmentOneBinding!!.etName.text.toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(s)) {
                val userDataModel = UserDataModel()
                userDataModel.createdAt = System.currentTimeMillis().toString()
                userDataModel.name = s
                fragmentOneViewModel!!.saveUserData(userDataModel)

                // for testing of language change
                /*if (fragmentOneViewModel?.appSharedPref?.language.equals("en")){
                    baseActivity?.setNewLocale("ar")
                }else{
                    baseActivity?.setNewLocale("en")
                }*/

            } else {
                Toast.makeText(baseActivity, "Please Enter Name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        fun newInstance(): FragmentOne {
            val args = Bundle()
            val fragment = FragmentOne()
            fragment.arguments = args
            return fragment
        }
    }

    private fun setUpRecycler() {
        val mOpenSourceAdapter = RecyclerViewAdapter(baseActivity!!)
        fragmentOneBinding!!.recyclerView.layoutManager = LinearLayoutManager(baseActivity)
        fragmentOneBinding!!.recyclerView.itemAnimator = DefaultItemAnimator()
        fragmentOneBinding!!.recyclerView.adapter = mOpenSourceAdapter
    }
}
