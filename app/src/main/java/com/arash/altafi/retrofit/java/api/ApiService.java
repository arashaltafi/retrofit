package com.arash.altafi.retrofit.java.api;

import com.arash.altafi.retrofit.java.data.ResponseBanner;
import com.arash.altafi.retrofit.java.data.ResponseItem;
import com.arash.altafi.retrofit.java.data.ResponseMessage;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("banner.php")
    Single <List<ResponseBanner>> getBanner();

    @GET("users/{user}/repos")
    Call <ResponseBody> getUser(@Path("user") String user);

    @GET("developer/retrofit/recive.php")
    Single<List<ResponseItem>> getItem();

    @Headers("Authorization:welcome")
    @GET("developer/retrofit/authorize.php")
    Single <List<ResponseItem>> getItemHeader();

    @POST("developer/retrofit/register.php")
    @FormUrlEncoded
    Single <ResponseMessage> sendUser2(@Field("username") String username , @Field("password") String password);

    @POST("developer/retrofit/set.php")
    Single <ResponseMessage> sendUser(@Body JsonObject object);

    // @Query , @Path , @Field , @POST , @GET , @Headers , @Body
    // complete in shop 2 project in github
}
