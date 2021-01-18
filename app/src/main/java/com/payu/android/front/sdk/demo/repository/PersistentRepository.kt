package com.payu.android.front.sdk.demo.repository

import android.content.Context

private const val SETTINGS_NAME = "appSettings"
private const val SETTINGS_POSID_KEY = "posid"
private const val SETTINGS_CLIENT_SECRET_KEY = "client_secret"
private const val SETTINGS_SAVE_CREDENTIALS_KEY = "save_credentials"

/**
 * Sandbox credentials:
 * https://developers.payu.com/pl/overview.html#Testing
 */
private const val POS_ID_DEFAULT_VALUE = "300746"
private const val CLIENT_SECRET_DEFAULT_VALUE = "2ee86a66e5d97e3fadc400c9f19b065d"//"12f071174cb7eb79d4aac5bc2f07563f"

class PersistentRepository(private val context: Context) {

    private fun getSettings() = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE)
    var saveCredentials: Boolean
        get() = getSettings().getBoolean(SETTINGS_SAVE_CREDENTIALS_KEY, false)
        set(value) {
            getSettings().edit().putBoolean(SETTINGS_SAVE_CREDENTIALS_KEY, value).apply()
        }

    var posid: String
        get() = getSettings().getString(SETTINGS_POSID_KEY, POS_ID_DEFAULT_VALUE)
            ?.ifEmpty { POS_ID_DEFAULT_VALUE }
            ?: POS_ID_DEFAULT_VALUE
        set(value) {
            getSettings().edit().putString(SETTINGS_POSID_KEY, value).apply()
        }

    var clientSecret
        get() = getSettings().getString(SETTINGS_CLIENT_SECRET_KEY, CLIENT_SECRET_DEFAULT_VALUE)
            ?.ifEmpty { CLIENT_SECRET_DEFAULT_VALUE }
            ?: CLIENT_SECRET_DEFAULT_VALUE
        set(value) {
            getSettings().edit().putString(SETTINGS_CLIENT_SECRET_KEY, value).apply()
        }
}
