//package com.wahyu.filmskuy.adapter
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.paging.PagedListAdapter
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.wahyu.filmskuy.R
//import com.wahyu.filmskuy.data.local.entity.MovieEntity
//import com.wahyu.filmskuy.models.MovieCatalogueModel
//import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
//import com.wahyu.filmskuy.utils.invisible
//import com.wahyu.filmskuy.utils.visible
//import com.wahyu.filmskuy.viewmodels.local.MovieFavoriteViewModel
//import com.wahyu.filmskuy.views.activity.DetailActivity
//import kotlinx.android.synthetic.main.list_item_film.view.*
//
///**
// * Created by wahyu_septiadi on 17, November 2020.
// * Visit My GitHub --> https://github.com/WahyuSeptiadi
// */
//
//class MoviePagedListAdapter internal constructor() :
//    PagedListAdapter<MovieEntity, MoviePagedListAdapter.MovieViewHolder>(DIFF_CALLBACK) {
//
//    companion object {
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
//            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
//                return oldItem.overview == newItem.overview && oldItem.posterPath == newItem.posterPath
//            }
//
//            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(movieEntity: MovieEntity) {
//            with(itemView) {
//                val movieFavoriteViewModel = MovieFavoriteViewModel(context)
//
//                if (movieEntity.posterPath != null) {
//                    val imageSize = context.getString(R.string.size_url_image_list)
//                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${movieEntity.posterPath}"
//                    Glide.with(context).load(urlImage).placeholder(R.drawable.loading)
//                        .into(imageFilm)
//                } else {
//                    Glide.with(context).load(R.drawable.img_notfound)
//                        .placeholder(R.drawable.loading)
//                        .into(imageFilm)
//                }
//
//                titleFilm.text = movieEntity.title
//
//                if (movieEntity.releaseDate != "") {
//                    yearFilm.text = movieEntity.releaseDate?.substring(0, 4)
//                }
//
//                ratingFilm.text = movieEntity.voteAverage.toString()
//
//                if (movieEntity.favorite!!) {
//                    insertToFavorite.invisible()
//                    deleteFromFavorite.visible()
//                } else {
//                    deleteFromFavorite.invisible()
//                    insertToFavorite.visible()
//                }
//
//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailActivity::class.java)
//                    intent.putExtra(
//                        DetailActivity.EXTRA_FILMS, MovieCatalogueModel(
//                            movieEntity.id,
//                            movieEntity.posterPath,
//                            movieEntity.title,
//                            movieEntity.overview,
//                            movieEntity.voteAverage,
//                            movieEntity.releaseDate
//                        )
//                    )
//                    itemView.context.startActivity(intent)
//                }
//
//                insertToFavorite.setOnClickListener {
//                    movieFavoriteViewModel.updateMovieToFavorite(
//                        MovieEntity(
//                            popular = movieEntity.popular,
//                            favorite = true,
//                            id = movieEntity.id,
//                            overview = movieEntity.overview,
//                            posterPath = movieEntity.posterPath,
//                            releaseDate = movieEntity.releaseDate,
//                            title = movieEntity.title,
//                            voteAverage = movieEntity.voteAverage
//                        )
//                    )
//
//                    Toast.makeText(context, "This movie has been added", Toast.LENGTH_SHORT).show()
//                }
//
//                deleteFromFavorite.setOnClickListener {
//                    movieFavoriteViewModel.updateMovieToFavorite(
//                        MovieEntity(
//                            popular = movieEntity.popular,
//                            favorite = false,
//                            id = movieEntity.id,
//                            overview = movieEntity.overview,
//                            posterPath = movieEntity.posterPath,
//                            releaseDate = movieEntity.releaseDate,
//                            title = movieEntity.title,
//                            voteAverage = movieEntity.voteAverage
//                        )
//                    )
//                    Toast.makeText(context, "Movie has been deleted", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.list_item_film, parent, false)
//        return MovieViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        val movies = getItem(position) as MovieEntity
//        holder.bind(movies)
//    }
//
//}