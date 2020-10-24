package com.wahyu.filmskuy.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wahyu.filmskuy.views.fragment.MovieFragment
import com.wahyu.filmskuy.views.fragment.TvShowFragment

/**
 * Created by wahyu_septiadi on 22, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        MovieFragment(),
        TvShowFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Movie"
            else -> "Tv Show"
        }
    }
}