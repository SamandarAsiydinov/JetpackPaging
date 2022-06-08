package com.example.jetpackpaging.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {

    private const val BASE_URL = "https://reqres.in/api/"

    private fun getLogging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)
    private fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getLogging())
        .build()

    private fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService = getRetroInstance().create(ApiService::class.java)
}