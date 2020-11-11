package com.wahyu.filmskuy.adapter.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.data.remote.response.MovieResult
import com.wahyu.filmskuy.models.FilmCatalogueModel
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.viewmodels.local.MovieFavoriteViewModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var listFilms = ArrayList<MovieResult>()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: MovieResult) {
            with(itemView) {

                val movieFavoriteViewModel = MovieFavoriteViewModel(context)

                if (film.posterPath != null) {
                    val imageSize = context.getString(R.string.size_url_image_list)
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${film.posterPath}"
                    Glide.with(context).load(urlImage).placeholder(R.drawable.loading).into(imageFilm)
                } else {
                    Glide.with(context).load(R.drawable.img_notfound).placeholder(R.drawable.loading)
                        .into(imageFilm)
                }

                titleFilm.text = film.title

                if (film.releaseDate != "") {
                    yearFilm.text = film.releaseDate?.substring(0, 4)
                }

                ratingFilm.text = film.voteAverage.toString()

                val currentFilm = FilmCatalogueModel(
                    film.id,
                    film.posterPath,
                    film.title,
                    film.overview,
                    film.voteAverage,
                    film.releaseDate
                )

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FILMS, currentFilm)
                    itemView.context.startActivity(intent)
                }

                if (film.isFavorite) {
                    toggleButton.isChecked
                }

                toggleButton.setOnClickListener {
                    film.isFavorite = true
                    movieFavoriteViewModel.insertMovie(
                        MovieEntity(
                            film.isFavorite,
                            film.adult,
                            film.backdropPath,
                            film.id,
                            film.originalLanguage,
                            film.originalTitle,
                            film.overview,
                            film.popularity,
                            film.posterPath,
                            film.releaseDate,
                            film.title,
                            film.video,
                            film.voteAverage,
                            film.voteCount
                        )
                    )
                    Toast.makeText(context, "This movie has been added", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setFilm(films: List<MovieResult>?) {
        if (films == null) return
        this.listFilms.clear()
        this.listFilms.addAll(films)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_film, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listFilms[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listFilms.size
}