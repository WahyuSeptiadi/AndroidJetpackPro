package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.data.MovieCatalogueRepository
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.vo.Resources

/**
 * Created by wahyu_septiadi on 15, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieCatalogueViewModel (private val data : MovieCatalogueRepository): ViewModel(){
    fun getAllMovies() : LiveData<Resources<List<MovieEntity>>> = data.getAllMovie()
}