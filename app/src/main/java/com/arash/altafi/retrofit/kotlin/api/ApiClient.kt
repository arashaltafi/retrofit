package com.arash.altafi.retrofit.kotlin.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        var retrofitKotlin: Retrofit? = null
        var retrofitUser: Retrofit? = null

        fun getRetrofit(): Retrofit {
            if (retrofitKotlin == null) {
                val client = OkHttpClient.Builder().addInterceptor { chain ->
                    val request = chain.request()
                    val builder = request.newBuilder()
                    builder.addHeader("Authorization", "welcome")
                    chain.proceed(builder.build())
                }.build()
                retrofitKotlin = Retrofit.Builder().baseUrl("https://novindevelopers.ir/")
                    .addConverterFactory(GsonConverterFactory.create()).client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            }
            return retrofitKotlin!!
        }

        fun getUser(): Retrofit {
            if (retrofitUser == null) {
                retrofitUser = Retrofit.Builder().baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofitUser!!
        }
    }

}