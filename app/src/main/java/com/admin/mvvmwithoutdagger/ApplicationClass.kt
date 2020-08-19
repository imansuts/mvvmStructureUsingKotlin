package com.admin.mvvmwithoutdagger

import android.app.Application

import androidx.room.Room

import com.admin.mvvmwithoutdagger.data.datasource.api.ApiClient
import com.admin.mvvmwithoutdagger.data.datasource.api.ApiService
import com.admin.mvvmwithoutdagger.data.datasource.db.AppDataBase
import com.admin.mvvmwithoutdagger.data.datasource.sharedpref.AppSharedPref
import com.admin.mvvmwithoutdagger.utils.AppConstants

class ApplicationClass : Application() {
    var appDatabase: AppDataBase? = null
        private set
    var appSharedPref: AppSharedPref? = null
        private set


    val apiServiceWithJacksonFactory: ApiService
        get() = ApiClient.retrofit(this, getString(R.string.api_base), ApiClient.CONVERTER_TYPE_JACKSON)!!.create(ApiService::class.java!!)

    val apiServiceWithGsonFactory: ApiService
        get() = ApiClient.retrofit(this, getString(R.string.api_base), ApiClient.CONVERTER_TYPE_GSON)!!.create(ApiService::class.java!!)


    override fun onCreate() {
        super.onCreate()
        instance = this
        appSharedPref = AppSharedPref(this, AppConstants.SHARED_PREF_NAME)
        appDatabase = Room.databaseBuilder(this,
                AppDataBase::class.java, AppConstants.DB_NAME).build()
    }

    companion object {

        @get:Synchronized
        var instance: ApplicationClass? = null
            private set
    }
}
