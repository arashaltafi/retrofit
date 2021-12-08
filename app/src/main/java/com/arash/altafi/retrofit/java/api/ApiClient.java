package com.arash.altafi.retrofit.java.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = null;
    public static Retrofit retrofit_user = null;

    public static Retrofit getRetrofit()
    {
        if (retrofit == null)
        {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder();
                    builder.addHeader("Authorization","welcome");
                    return chain.proceed(builder.build());
                }
            }).build();

            retrofit = new Retrofit.Builder().baseUrl("https://novindevelopers.ir/").addConverterFactory(GsonConverterFactory.create())
                    .client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }
        return retrofit;
    }

    public static Retrofit getUser()
    {
        if (retrofit_user == null)
        {
            retrofit_user = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit_user;
    }

}
