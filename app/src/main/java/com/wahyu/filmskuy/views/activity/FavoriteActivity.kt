package com.wahyu.filmskuy.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.favorite.MyPagerFavoriteAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        viewpagerFavorite.adapter = MyPagerFavoriteAdapter(this, supportFragmentManager)
        tabsFavorite.setupWithViewPager(viewpagerFavorite)

        btnBackFavorite.setOnClickListener {
            onBackPressed()
        }
    }
}