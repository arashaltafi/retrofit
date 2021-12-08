package com.arash.altafi.retrofit.kotlin.api

import com.arash.altafi.retrofit.java.data.ResponseBanner
import com.arash.altafi.retrofit.java.data.ResponseItem
import com.arash.altafi.retrofit.java.data.ResponseMessage
import com.arash.altafi.retrofit.kotlin.data.ResponseBannerKotlin
import com.arash.altafi.retrofit.kotlin.data.ResponseItemKotlin
import com.arash.altafi.retrofit.kotlin.data.ResponseMessageKotlin
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("banner.php")
    fun getBanner(): Single<List<ResponseBannerKotlin>>

    @GET("users/{user}/repos")
    fun getUser(@Path("user") user: String): Call<ResponseBody>

    @GET("developer/retrofit/recive.php")
    fun getItem(): Single<List<ResponseItemKotlin>>

    @Headers("Authorization:welcome")
    @GET("developer/retrofit/authorize.php")
    fun getItemHeader(): Single<List<ResponseItemKotlin>>

    @POST("developer/retrofit/register.php")
    @FormUrlEncoded
    fun sendUser2(@Field("username") username: String, @Field("password") password: String): Single<ResponseMessageKotlin>

    @POST("developer/retrofit/set.php")
    fun sendUser(@Body objects: JsonObject): Single<ResponseMessageKotlin>

    // @Query , @Path , @Field , @POST , @GET , @Headers , @Body
    // complete in shop 2 project in github

}