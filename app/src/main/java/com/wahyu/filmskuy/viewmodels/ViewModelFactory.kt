package com.wahyu.filmskuy.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wahyu.filmskuy.data.FilmCatalogueRepository
import com.wahyu.filmskuy.di.Injection

/**
 * Created by wahyu_septiadi on 15, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class ViewModelFactory private constructor(private val mAcademyRepository: FilmCatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when {
            modelClass.isAssignableFrom(MovieCatalogueViewModel::class.java) -> {
                MovieCatalogueViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}