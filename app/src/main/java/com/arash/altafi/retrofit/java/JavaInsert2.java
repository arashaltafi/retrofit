package com.arash.altafi.retrofit.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arash.altafi.retrofit.R;
import com.arash.altafi.retrofit.java.api.ApiClient;
import com.arash.altafi.retrofit.java.api.ApiService;
import com.arash.altafi.retrofit.java.data.ResponseMessage;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JavaInsert2 extends AppCompatActivity {

    private TextInputEditText edtUser;
    private TextInputEditText edtPass;
    private MaterialButton btnSave;
    private ApiService apiService;
    private CoordinatorLayout coordinator;
    private ProgressBar progressBar;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_insert2);

        init();
    }

    private void init() {
        findView();

        apiService = ApiClient.getRetrofit().create(ApiService.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUser.length() < 1 && edtPass.length() < 1)
                {
                    Toast.makeText(JavaInsert2.this, "لطفا مقادیر را وارد نمایید", Toast.LENGTH_SHORT).show();
                }
                else if (edtUser.length() < 1)
                {
                    edtUser.setError("لطفا نام کاربری را وارد نمایید");
                }
                else if (edtPass.length() < 1)
                {
                    edtPass.setError("لطفا نام کاربری را وارد نمایید");
                }
                else
                {
                    sendUser(edtUser.getText().toString().trim(),edtPass.getText().toString().trim());
                }
            }
        });

    }

    private void sendUser(String username , String password) {
        progressBar.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        apiService.sendUser2(username , password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<ResponseMessage>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NotNull ResponseMessage responseMessage) {
                progressBar.setVisibility(View.GONE);
                btnSave.setVisibility(View.VISIBLE);
                Snackbar.make(coordinator, responseMessage.getMessage() , Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NotNull Throwable e) {
                Snackbar.make(coordinator, e.getMessage() , Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void findView() {
        coordinator = findViewById(R.id.coordinator_java2);
        btnSave = findViewById(R.id.btn_Insert2_java);
        edtUser = findViewById(R.id.edt_username_java);
        edtPass = findViewById(R.id.edt_password_java);
        progressBar = findViewById(R.id.progress_insert2_java);
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