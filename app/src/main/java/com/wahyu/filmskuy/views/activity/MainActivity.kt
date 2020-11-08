package com.wahyu.filmskuy.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.home.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpagerMain.adapter = MyPagerAdapter(this, supportFragmentManager)
        tabsMain.setupWithViewPager(viewpagerMain)

        favorite.setOnClickListener {
            val toFavorite = Intent(this, FavoriteActivity::class.java)
            startActivity(toFavorite)
        }
    }
}