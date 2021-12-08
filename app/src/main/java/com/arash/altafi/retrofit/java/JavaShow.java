package com.arash.altafi.retrofit.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arash.altafi.retrofit.R;
import com.arash.altafi.retrofit.java.adapter.AdapterItemJava;
import com.arash.altafi.retrofit.java.api.ApiClient;
import com.arash.altafi.retrofit.java.api.ApiService;
import com.arash.altafi.retrofit.java.data.ResponseItem;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JavaShow extends AppCompatActivity {

    private RecyclerView rcItem;
    private ApiService apiService;
    private AdapterItemJava adapterItem;
    private ExtendedFloatingActionButton fabSave;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_show);

        init();
    }

    private void init() {
        findView();
        apiService = ApiClient.getRetrofit().create(ApiService.class);

        getItem();

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), JavaInsert2.class));
            }
        });
    }

    private void getItem() {
        apiService.getItem().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<ResponseItem>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<ResponseItem> responseItems) {
                adapterItem = new AdapterItemJava(responseItems);
                rcItem.setAdapter(adapterItem);
                rcItem.setLayoutManager(new LinearLayoutManager(JavaShow.this, RecyclerView.VERTICAL, false));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(JavaShow.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findView() {
        fabSave = findViewById(R.id.fab_Save);
        rcItem = findViewById(R.id.rc_item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getItem();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }

}