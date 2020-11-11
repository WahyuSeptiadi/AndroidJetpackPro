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
abstract class FilmCatalogueDatabase : RoomDatabase() {
    abstract fun filmCatalogueDao(): FilmCatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: FilmCatalogueDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FilmCatalogueDatabase {
            if (INSTANCE == null) {
                synchronized(FilmCatalogueDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            FilmCatalogueDatabase::class.java,
                            "FilmCatalogue.db"
                        ).build()
                    }
                }
            }

            return INSTANCE as FilmCatalogueDatabase
        }
    }
}