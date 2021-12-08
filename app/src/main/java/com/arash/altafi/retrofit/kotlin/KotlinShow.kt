package com.arash.altafi.retrofit.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.retrofit.R
import com.arash.altafi.retrofit.kotlin.adapter.AdapterItemKotlin
import com.arash.altafi.retrofit.kotlin.api.ApiClient
import com.arash.altafi.retrofit.kotlin.api.ApiService
import com.arash.altafi.retrofit.kotlin.data.ResponseItemKotlin
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_kotlin_show.*

class KotlinShow : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()
    private var adapterItem: AdapterItemKotlin ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_show)

        init()
    }

    private fun init() {
        getItem()
        fab_Save_kotlin.setOnClickListener {
            startActivity(Intent(this , KotlinInsert2::class.java))
        }
    }

    private fun getItem() {
        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        apiService.getItem().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<List<ResponseItemKotlin>>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onSuccess(t: List<ResponseItemKotlin>) {
                adapterItem = AdapterItemKotlin(t)
                rc_item_kotlin.adapter = adapterItem
                rc_item_kotlin.layoutManager = LinearLayoutManager(this@KotlinShow , RecyclerView.VERTICAL , false)
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@KotlinShow, e.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onStart() {
        super.onStart()
        getItem()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable != null) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }

}