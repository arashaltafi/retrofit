package com.arash.altafi.retrofit.kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.retrofit.R
import com.arash.altafi.retrofit.kotlin.api.ApiClient
import com.arash.altafi.retrofit.kotlin.api.ApiService
import com.arash.altafi.retrofit.kotlin.data.ResponseMessageKotlin
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_kotlin_insert.*

class KotlinInsert : AppCompatActivity() {

    private var compositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_insert)

        init()
    }

    private fun init() {
        btn_Insert_kotlin.setOnClickListener {
            sendUser()
        }
    }

    private fun sendUser() {

        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        progress_insert_kotlin.visibility = View.VISIBLE
        btn_Insert_kotlin.visibility = View.GONE
        val objects = JsonObject()
        objects.addProperty("namefamily", edt_Name_kotlin.text.toString().trim())
        objects.addProperty("email", edt_Email_kotlin.text.toString().trim())
        objects.addProperty("mobile", edt_Mobile_kotlin.text.toString().trim())

        apiService.sendUser(objects).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(object : SingleObserver<ResponseMessageKotlin>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onSuccess(t: ResponseMessageKotlin) {
                Snackbar.make(coordinator_kotlin, t.message.toString() , Snackbar.LENGTH_LONG).show()
                progress_insert_kotlin.visibility = View.GONE
                btn_Insert_kotlin.visibility = View.VISIBLE
                finish()
            }

            override fun onError(e: Throwable) {
                Snackbar.make(coordinator_kotlin, e.message.toString() , Snackbar.LENGTH_LONG).show()
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