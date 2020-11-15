package com.wahyu.filmskuy.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.entity.TvShowEntity

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase : RoomDatabase() {
    abstract fun filmCatalogueDao(): MovieCatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: MovieCatalogueDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): MovieCatalogueDatabase {
            if (INSTANCE == null) {
                synchronized(MovieCatalogueDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MovieCatalogueDatabase::class.java,
                            "FilmCatalogue.db"
                        ).build()
                    }
                }
            }

            return INSTANCE as MovieCatalogueDatabase
        }
    }
}