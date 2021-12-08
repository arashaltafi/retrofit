package com.arash.altafi.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.retrofit.java.JavaActivity
import com.arash.altafi.retrofit.kotlin.KotlinActivity
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        btnJava.setOnClickListener {
            startActivity(Intent(this , JavaActivity::class.java))
        }

        btnKotlin.setOnClickListener {
            startActivity(Intent(this , KotlinActivity::class.java))
        }
    }

}