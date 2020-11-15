package com.wahyu.filmskuy.adapter.favorite

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.models.MovieCatalogueModel
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.utils.gone
import com.wahyu.filmskuy.utils.visible
import com.wahyu.filmskuy.viewmodels.local.MovieFavoriteViewModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteAdapter : RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteViewHolder>() {

    private var listFilms = ArrayList<MovieEntity>()

    class MovieFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                val movieFavoriteViewModel = MovieFavoriteViewModel(context)
                if (movie.posterPath != null) {
                    Log.d("MovieDataFavoriteImage", "${movie.posterPath}")
                    val imageSize = context.getString(R.string.size_url_image_list)
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${movie.posterPath}"
                    Glide.with(context).load(urlImage).placeholder(R.drawable.loading)
                        .into(imageFilm)
                } else {
                    Glide.with(context).load(R.drawable.img_notfound).placeholder(R.drawable.loading)
                        .into(imageFilm)
                }

                titleFilm.text = movie.title

                if (movie.releaseDate != "") {
                    yearFilm.text = movie.releaseDate?.substring(0, 4)
                    Log.d("MovieDataFavoriteData", "${movie.releaseDate}")
                }

                ratingFilm.text = movie.voteAverage.toString()

                itemView.setOnClickListener {
                    val movieIntent = Intent(context, DetailActivity::class.java)

                    movieIntent.putExtra(
                        DetailActivity.EXTRA_FILMS,
                        MovieCatalogueModel(
                            movie.id,
                            movie.posterPath,
                            movie.title,
                            movie.overview,
                            movie.voteAverage,
                            movie.releaseDate
                        )
                    )

                    context.startActivity(movieIntent)
                }

                insertToFavorite.gone()
                deleteFromFavorite.visible()

                deleteFromFavorite.setOnClickListener {
                    movieFavoriteViewModel.deleteMovieWithId(movie.id)
                    Toast.makeText(context, "Movie has been deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setFilms(films: List<MovieEntity>?) {
        if (films == null) return
        this.listFilms.clear()
        this.listFilms.addAll(films)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val movie = listFilms[position]
        holder.bind(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_film, parent, false)
        return MovieFavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFilms.size
    }

}