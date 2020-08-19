package com.admin.mvvmwithoutdagger.data.datasource.sharedpref

import android.content.Context
import android.content.SharedPreferences

class AppSharedPref(context: Context, prefFileName: String) {

    private val mPrefs: SharedPreferences


    var accessToken: String?
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()


    var currentUserEmail: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
        set(email) = mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    fun deleteCurrentUserEmail() {
        mPrefs.edit().remove(PREF_KEY_CURRENT_USER_EMAIL).apply()
    }

    companion object {

        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

        private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
    }

}
