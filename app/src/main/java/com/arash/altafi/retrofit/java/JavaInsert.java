package com.arash.altafi.retrofit.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.arash.altafi.retrofit.R;
import com.arash.altafi.retrofit.java.api.ApiClient;
import com.arash.altafi.retrofit.java.api.ApiService;
import com.arash.altafi.retrofit.java.data.ResponseMessage;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JavaInsert extends AppCompatActivity {

    private TextInputEditText edtName;
    private TextInputEditText edtEmail;
    private TextInputEditText edtMobile;
    private MaterialButton btnSave;
    private ApiService apiService;
    private CoordinatorLayout coordinator;
    private ProgressBar progressBar;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_insert);

        init();
    }

    private void init() {
        findView();
        apiService = ApiClient.getRetrofit().create(ApiService.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUser();
            }
        });

    }

    private void sendUser() {
        progressBar.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        JsonObject object = new JsonObject();
        object.addProperty("namefamily", edtName.getText().toString().trim());
        object.addProperty("email", edtEmail.getText().toString().trim());
        object.addProperty("mobile", edtMobile.getText().toString().trim());
        apiService.sendUser(object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ResponseMessage>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull ResponseMessage responseMessage) {
                Snackbar.make(coordinator, responseMessage.getMessage(), Snackbar.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                btnSave.setVisibility(View.VISIBLE);
                finish();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Snackbar.make(coordinator, e.getMessage(), Snackbar.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                btnSave.setVisibility(View.VISIBLE);
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
        coordinator = findViewById(R.id.coordinator_java);
        btnSave = findViewById(R.id.btn_Insert_java);
        edtName = findViewById(R.id.edt_Name_java);
        edtEmail = findViewById(R.id.edt_Email_java);
        edtMobile = findViewById(R.id.edt_Mobile_java);
        progressBar = findViewById(R.id.progress_insert_java);
    }

}