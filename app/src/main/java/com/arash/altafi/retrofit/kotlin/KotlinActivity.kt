package com.arash.altafi.retrofit.kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.retrofit.R
import com.arash.altafi.retrofit.kotlin.adapter.AdapterBannerKotlin
import com.arash.altafi.retrofit.kotlin.api.ApiClient
import com.arash.altafi.retrofit.kotlin.api.ApiService
import com.arash.altafi.retrofit.kotlin.data.ResponseBannerKotlin
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_kotlin.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class KotlinActivity : AppCompatActivity() {

    private val TAG = "KotlinActivity"
    private var compositeDisposable : CompositeDisposable = CompositeDisposable()
    private var adapterBannerKotlin: AdapterBannerKotlin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        init()
    }

    private fun init() {
        getBanner()
        getUser("arashaltafi")

        btn_show_kotlin.setOnClickListener {
            startActivity(Intent(this, KotlinShow::class.java))
        }

        btn_insert_kotlin.setOnClickListener {
            startActivity(Intent(this, KotlinInsert::class.java))
        }
    }

    private fun getBanner() {

        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        apiService.getBanner().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(object : SingleObserver<List<ResponseBannerKotlin>> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onSuccess(responseBanners: List<ResponseBannerKotlin>) {
                adapterBannerKotlin = AdapterBannerKotlin(responseBanners)
                rc_banner_kotlin.adapter = adapterBannerKotlin
                rc_banner_kotlin.layoutManager = LinearLayoutManager(applicationContext , RecyclerView.VERTICAL , false)
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@KotlinActivity, e.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun getUser(user : String) {

        val apiServiceUser = ApiClient.getUser().create(ApiService::class.java)

        apiServiceUser.getUser(user).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i(TAG, "response: " + response.message())
                Log.i(TAG, "response: " + response.message().length)
                Log.i(TAG, "response: " + response.body().toString())
                Log.i(TAG, "response: " + response.raw().toString())
                Log.i(TAG, "response: " + response.code())
                Log.i(TAG, "response: " + response.isSuccessful)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@KotlinActivity, t.message, Toast.LENGTH_SHORT).show()
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