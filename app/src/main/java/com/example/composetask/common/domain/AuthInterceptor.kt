package com.example.composetask.common.domain

import android.content.Context
import com.example.composetask.data.local.SharedPrefsHelper
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor (private val context: Context) : Interceptor {
    override fun intercept(chain: Chain): Response {
        val originalRequest = chain.request()
        val accessToken = SharedPrefsHelper.getAccessToken(context)

        return if (accessToken != null) {
            val request = originalRequest.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(request)
        } else {
            chain.proceed(originalRequest)
        }
    }
}