package com.fakhrirasyids.quickie.common

import android.content.Context
import android.content.ContextWrapper
import android.os.LocaleList
import java.util.Locale

class ContextUtils(base: Context) : ContextWrapper(base) {
    companion object {
        fun updateLocale(c: Context, localeToSwitchTo: Locale): ContextWrapper {
            val resources = c.resources
            val configuration = resources.configuration
            val localeList = LocaleList(localeToSwitchTo)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
            return ContextUtils(c.createConfigurationContext(configuration))
        }
    }
}