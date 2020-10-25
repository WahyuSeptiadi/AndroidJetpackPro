package com.wahyu.filmskuy.views.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.wahyu.filmskuy.R

/**
 * Created by wahyu_septiadi on 22, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class SplashActivity : Activity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}