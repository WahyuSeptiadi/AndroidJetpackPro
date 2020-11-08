package com.wahyu.filmskuy.adapter.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.remote.response.MovieResult
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.viewmodels.local.MovieFavoriteViewModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film_favorite.view.*

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteAdapter :
    PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MovieFavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    inner class MovieFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                val viewModel = MovieFavoriteViewModel(context)
                if (movie.poster_path != null) {
                    val imageSize = "/w780"
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${movie.poster_path}"
                    Picasso.get().load(urlImage).placeholder(R.drawable.loading)
                        .into(imageFilmFavorite)
                } else {
                    Picasso.get().load(R.drawable.img_notfound).placeholder(R.drawable.loading)
                        .into(imageFilmFavorite)
                }

                titleFilmFavorite.text = movie.title

                if (movie.release_date != "") {
                    yearFilmFavorite.text = movie.release_date?.substring(0, 4)
                }

                ratingFilmFavorite.text = movie.vote_average.toString()

                itemView.setOnClickListener {
                    val movieIntent = Intent(context, DetailActivity::class.java)

                    movieIntent.putExtra(
                        DetailActivity.EXTRA_FILMS,
                        MovieResult(
                            movie.isFavorite,
                            movie.adult,
                            movie.backdrop_path,
                            ArrayList(),
                            movie.id,
                            movie.original_language,
                            movie.original_title,
                            movie.overview,
                            movie.popularity,
                            movie.poster_path,
                            movie.release_date,
                            movie.title,
                            movie.video,
                            movie.vote_average,
                            movie.vote_count
                        )
                    )

                    context.startActivity(movieIntent)
                }

                toggleButtonFavorite.setOnClickListener {
                    viewModel.delete(movie)
                    Toast.makeText(context, "Movie has been deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_film_favorite, parent, false)
        return MovieFavoriteViewHolder(view)
//        val view = MovieFavoriteViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.list_item_film_favorite, parent, false)
//        )
//
//        view.itemView.setOnClickListener {
//            val movieIntent = Intent(activity, DetailActivity::class.java)
//            val movie = getItem(view.adapterPosition)
//
//            if (movie != null) {
//                movieIntent.putExtra(
//                    DetailActivity.EXTRA_FILMS,
//                    MovieResult(
//                        movie.adult,
//                        movie.backdrop_path,
//                        ArrayList(),
//                        movie.id,
//                        movie.original_language,
//                        movie.original_title,
//                        movie.overview,
//                        movie.popularity,
//                        movie.poster_path,
//                        movie.release_date,
//                        movie.title,
//                        movie.video,
//                        movie.vote_average,
//                        movie.vote_count
//                    )
//                )
//            }
//
//            activity.startActivity(movieIntent)
//        }
//
//        return view
    }
}