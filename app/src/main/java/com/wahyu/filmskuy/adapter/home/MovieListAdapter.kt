package com.wahyu.filmskuy.adapter.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.data.remote.response.MovieResult
import com.wahyu.filmskuy.models.DetailFilmCatalogue
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
                val viewModel = MovieFavoriteViewModel(context)
                if (film.poster_path != null) {
                    val imageSize = "/w780"
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${film.poster_path}"
                    Picasso.get().load(urlImage).placeholder(R.drawable.loading).into(imageFilm)
                } else {
                    Picasso.get().load(R.drawable.img_notfound).placeholder(R.drawable.loading)
                        .into(imageFilm)
                }

                titleFilm.text = film.title

                if (film.release_date != "") {
                    yearFilm.text = film.release_date?.substring(0, 4)
                }

                ratingFilm.text = film.vote_average.toString()

                val currentFilm = DetailFilmCatalogue(
                    film.id,
                    film.poster_path,
                    film.title,
                    film.overview,
                    film.vote_average,
                    film.release_date
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
                    viewModel.insert(
                        MovieEntity(
                            film.isFavorite,
                            film.adult,
                            film.backdrop_path,
                            film.id,
                            film.original_language,
                            film.original_title,
                            film.overview,
                            film.popularity,
                            film.poster_path,
                            film.release_date,
                            film.title,
                            film.video,
                            film.vote_average,
                            film.vote_count
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