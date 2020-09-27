package com.admin.mvvmwithoutdagger.data.datasource.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.admin.mvvmwithoutdagger.utils.MyLocaleManager

class AppSharedPref(context: Context, prefFileName: String) {

    companion object {

        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private val PREF_KEY_LANGUAGE_KEY = "PREF_KEY_LANGUAGE_KEY"
        private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)


    var language: String?
        //        get() = mPrefs.getString(PREF_KEY_LANGUAGE_KEY, LocaleManager.deviceDefaultLanguage)
        get() = mPrefs.getString(PREF_KEY_LANGUAGE_KEY, MyLocaleManager.deviceDefaultLanguage())
        set(userDetails) = mPrefs.edit().putString(PREF_KEY_LANGUAGE_KEY, userDetails).apply()


    var accessToken: String?
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()


    var currentUserEmail: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
        set(email) = mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()

    fun deleteCurrentUserEmail() {
        mPrefs.edit().remove(PREF_KEY_CURRENT_USER_EMAIL).apply()
    }



}
