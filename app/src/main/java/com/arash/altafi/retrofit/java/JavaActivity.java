package com.arash.altafi.retrofit.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arash.altafi.retrofit.R;
import com.arash.altafi.retrofit.java.adapter.AdapterBannerJava;
import com.arash.altafi.retrofit.java.api.ApiClient;
import com.arash.altafi.retrofit.java.api.ApiService;
import com.arash.altafi.retrofit.java.data.ResponseBanner;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JavaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MaterialButton btnShow;
    private MaterialButton btnInsert;
    private ApiService apiService;
    private ApiService apiServiceUser;
    private static final String TAG = "JavaActivity";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private AdapterBannerJava adapterBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        init();
    }

    private void init() {
        findView();
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiServiceUser = ApiClient.getUser().create(ApiService.class);

        get_banner();
        getUser("arashaltafi");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),JavaInsert.class));
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),JavaShow.class));
            }
        });
    }

    private void get_banner() {
        apiService.getBanner().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new SingleObserver<List<ResponseBanner>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NotNull List<ResponseBanner> responseBanners) {
                adapterBanner = new AdapterBannerJava(responseBanners);
                recyclerView.setAdapter(adapterBanner);
                recyclerView.setLayoutManager(new LinearLayoutManager(JavaActivity.this , RecyclerView.VERTICAL , false));
            }

            @Override
            public void onError(@NotNull Throwable e) {
                Toast.makeText(JavaActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getUser(String user) {
        apiServiceUser.getUser(user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG ,"response: " + response.message());
                Log.i(TAG ,"response: " + response.message().length());
                Log.i(TAG ,"response: " + response.body().toString());
                Log.i(TAG ,"response: " + response.raw().toString());
                Log.i(TAG ,"response: " + response.code());
                Log.i(TAG ,"response: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(JavaActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }

    private void findView() {
        recyclerView = findViewById(R.id.rc_banner_java);
        btnShow = findViewById(R.id.btn_show_java);
        btnInsert = findViewById(R.id.btn_insert_java);
    }
}