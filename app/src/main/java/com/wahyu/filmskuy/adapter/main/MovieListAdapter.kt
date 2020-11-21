package com.wahyu.filmskuy.adapter.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.MovieCatalogueModel
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.utils.invisible
import com.wahyu.filmskuy.utils.visible
import com.wahyu.filmskuy.viewmodels.local.MovieFavoriteViewModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var listFilms = ArrayList<MovieEntity>()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: MovieEntity) {
            with(itemView) {

                val movieFavoriteViewModel =
                    MovieFavoriteViewModel(MovieCatalogueRepository(context))

                if (film.posterPath != null) {
                    val imageSize = context.getString(R.string.size_url_image_list)
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${film.posterPath}"
                    Glide.with(context).load(urlImage).placeholder(R.drawable.loading)
                        .into(imageFilm)
                } else {
                    Glide.with(context).load(R.drawable.img_notfound)
                        .placeholder(R.drawable.loading)
                        .into(imageFilm)
                }

                titleFilm.text = film.title

                if (film.releaseDate != "") {
                    yearFilm.text = film.releaseDate?.substring(0, 4)
                }

                ratingFilm.text = film.voteAverage.toString()

                if (film.favorite) {
                    insertToFavorite.invisible()
                    deleteFromFavorite.visible()
                } else {
                    deleteFromFavorite.invisible()
                    insertToFavorite.visible()
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(
                        DetailActivity.EXTRA_FILMS, MovieCatalogueModel(
                            film.id,
                            film.posterPath,
                            film.title,
                            film.overview,
                            film.voteAverage,
                            film.releaseDate
                        )
                    )
                    itemView.context.startActivity(intent)
                }

                insertToFavorite.setOnClickListener {
                    movieFavoriteViewModel.updateMovieToFavorite(
                        MovieEntity(
                            popular = film.popular,
                            favorite = true,
                            id = film.id,
                            overview = film.overview,
                            posterPath = film.posterPath,
                            releaseDate = film.releaseDate,
                            title = film.title,
                            voteAverage = film.voteAverage
                        )
                    )

                    Toast.makeText(context, "This movie has been added", Toast.LENGTH_SHORT).show()
                }

                deleteFromFavorite.setOnClickListener {
                    movieFavoriteViewModel.updateMovieToFavorite(
                        MovieEntity(
                            popular = film.popular,
                            favorite = false,
                            id = film.id,
                            overview = film.overview,
                            posterPath = film.posterPath,
                            releaseDate = film.releaseDate,
                            title = film.title,
                            voteAverage = film.voteAverage
                        )
                    )
                    Toast.makeText(context, "Movie has been deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setFilm(films: List<MovieEntity>?) {
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