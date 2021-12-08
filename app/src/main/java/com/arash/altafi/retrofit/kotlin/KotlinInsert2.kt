package com.arash.altafi.retrofit.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.retrofit.R
import com.arash.altafi.retrofit.kotlin.api.ApiClient
import com.arash.altafi.retrofit.kotlin.api.ApiService
import com.arash.altafi.retrofit.kotlin.data.ResponseMessageKotlin
import com.google.android.material.snackbar.Snackbar
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_kotlin_insert.*
import kotlinx.android.synthetic.main.activity_kotlin_insert2.*

class KotlinInsert2 : AppCompatActivity() {

    private var compositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_insert2)

        init()
    }

    private fun init() {
        btn_Insert2_kotlin.setOnClickListener {
            if (edt_username_kotlin.length() < 1 && edt_password_kotlin.length() < 1) {
                Toast.makeText(this , "لطفا مقادیر را وارد نمایید", Toast.LENGTH_SHORT).show()
            }
            else if(edt_username_kotlin.length() < 1) {
                edt_username_kotlin.error = "لطفا نام کاربری را وارد نمایید"
            }
            else if (edt_password_kotlin.length() < 1) {
                edt_password_kotlin.error = "لطفا نام کاربری را وارد نمایید"
            }
            else {
                sendUser(edt_username_kotlin.text.toString().trim() , edt_password_kotlin.text.toString().trim())
            }
        }
    }

    private fun sendUser(username : String , password : String) {

        var apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        progress_insert2_kotlin.visibility = View.VISIBLE
        btn_Insert2_kotlin.visibility = View.GONE

        apiService.sendUser2(username , password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<ResponseMessageKotlin>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onSuccess(t: ResponseMessageKotlin) {
                Snackbar.make(coordinator_kotlin2, t.message.toString() , Snackbar.LENGTH_LONG).show()
                progress_insert2_kotlin.visibility = View.GONE
                btn_Insert2_kotlin.visibility = View.VISIBLE
            }

            override fun onError(e: Throwable) {
                Snackbar.make(coordinator_kotlin2, e.message.toString() , Snackbar.LENGTH_LONG).show()
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable != null) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }

}