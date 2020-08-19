package com.admin.mvvmwithoutdagger.ui.main

import android.os.Bundle
import android.widget.Toast

import androidx.lifecycle.ViewModelProviders

import com.admin.mvvmwithoutdagger.BR
import com.admin.mvvmwithoutdagger.R
import com.admin.mvvmwithoutdagger.databinding.ActivityMainBinding
import com.admin.mvvmwithoutdagger.ui.base.BaseActivity
import com.admin.mvvmwithoutdagger.ui.main.fragment1.FragmentOne

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private var mainViewModel: MainViewModel? = null

    private var activityMainBinding: ActivityMainBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() {
            mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)
            return mainViewModel as MainViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel!!.navigator = this
        activityMainBinding = viewDataBinding
    }

    override fun onClick() {
        Toast.makeText(this, "Test Click", Toast.LENGTH_SHORT).show()
        checkFragmentInBackstackAndOpen(FragmentOne.newInstance(), activityMainBinding!!.frameLayout.id)
    }
}
