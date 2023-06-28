package ru.savenkov.homework.data.datasource

import android.content.SharedPreferences
import javax.inject.Inject


class LocalSettingsDataSource @Inject constructor(
    private val sharedPrefs: SharedPreferences
) {
    private val IS_AUTHORIZED = "IS_AUTHORIZED"
    private val CURRENT_LANGUAGE_KEY = "CURRENT_LANGUAGE_KEY"
    private val AUTHORIZED_TOKEN = "AUTHORIZED_TOKEN"
    private val WAS_FIRST_LOGIN_KEY = "WAS_FIRST_LOGIN_KEY"

    fun setCurrentLanguage(language: String) {
        sharedPrefs.edit()
            .putString(CURRENT_LANGUAGE_KEY, language)
            .apply()
    }

    fun getCurrentLanguage() = sharedPrefs.getString(CURRENT_LANGUAGE_KEY, "ru")!!

    fun setAuthorizedTokenAndStatus(token: String) {
        val isAuthorized = true
        sharedPrefs.edit()
            .putBoolean(IS_AUTHORIZED, isAuthorized)
            .putString(AUTHORIZED_TOKEN, token)
            .apply()
    }

    fun setLogoutStatus() {
        val isAuthorized = false
        sharedPrefs.edit()
            .putBoolean(IS_AUTHORIZED, isAuthorized)
            .apply()
    }

    fun setWasFirstLoginKey() {
        sharedPrefs.edit()
            .putBoolean(WAS_FIRST_LOGIN_KEY, true)
            .apply()
    }

    fun wasFirstLogin(): Boolean = sharedPrefs.getBoolean(WAS_FIRST_LOGIN_KEY, false)

    fun isAuthorized() = sharedPrefs.getBoolean(IS_AUTHORIZED, false)

    fun getToken() = sharedPrefs.getString(AUTHORIZED_TOKEN, null)
}