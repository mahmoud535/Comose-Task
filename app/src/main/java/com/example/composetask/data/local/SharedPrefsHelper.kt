package com.example.composetask.data.local

import android.content.Context

object SharedPrefsHelper {
    private const val PREFS_NAME = "app_prefs"
    private const val ACCESS_TOKEN_KEY = "access_token"

    fun saveAccessToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(ACCESS_TOKEN_KEY, token).apply()
    }

    fun getAccessToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(ACCESS_TOKEN_KEY, null)
    }
}