package com.wahyu.filmskuy.adapter.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.views.fragment.favorite.MovieFavoriteFragment
import com.wahyu.filmskuy.views.fragment.favorite.TvShowFavoriteFragment

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MyPagerFavAdapter (private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf(
        MovieFavoriteFragment(),
        TvShowFavoriteFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.movie)
            else -> context.getString(R.string.tv_show)
        }
    }
}