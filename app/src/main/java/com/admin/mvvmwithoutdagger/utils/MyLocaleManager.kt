package com.admin.mvvmwithoutdagger.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import com.admin.mvvmwithoutdagger.ApplicationClass
import com.admin.mvvmwithoutdagger.data.datasource.sharedpref.AppSharedPref
import java.text.NumberFormat
import java.util.*

object MyLocaleManager {
    const val LANGUAGE_ENGLISH = "en"
    const val LANGUAGE_FRENCH = "fr"
    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) config.locales[0] else config.locale
    }

    fun deviceDefaultLanguage(): String {
        if (Locale.getDefault().language.equals(LANGUAGE_FRENCH)) {
            return LANGUAGE_FRENCH
        } else {
            return LANGUAGE_ENGLISH
        }
    }


    private var appSharedPref: AppSharedPref? = null

    init {
        if (appSharedPref == null) {
            appSharedPref = ApplicationClass.instance?.appSharedPref
        }
    }


    fun setNewLocale(c: Resources, language: String) {
        persistLanguage(language)
        updateResources(c, language)
    }

    val language: String?
        get() = appSharedPref!!.language

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(language: String) {
        appSharedPref!!.language = language
    }

    private fun updateResources(resources: Resources, language: String?) {
        val activityRes = resources
        val activityConf = activityRes.configuration
        val newLocale = Locale(language!!)
        activityConf.setLocale(newLocale)
        activityConf.setLayoutDirection(newLocale)
        activityRes.updateConfiguration(activityConf, activityRes.displayMetrics)
    }

    fun getNumberFormat(currencyCode:String): NumberFormat? {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        numberFormat.currency = Currency.getInstance(currencyCode)   //  example "USD", "INR"
        numberFormat.minimumFractionDigits = 0
        return numberFormat
    }
}