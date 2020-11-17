package com.wahyu.filmskuy.adapter.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.models.MovieCatalogueModel
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.utils.invisible
import com.wahyu.filmskuy.utils.visible
import com.wahyu.filmskuy.viewmodels.local.TvShowFavoriteViewModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 04, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowListAdapter : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {
    private var listFilms = ArrayList<TvShowEntity>()

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: TvShowEntity) {
            with(itemView) {

                val tvFavoriteViewModel = TvShowFavoriteViewModel(context)

                if (film.posterPath != null) {
                    val imageSize = context.getString(R.string.size_url_image_list)
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${film.posterPath}"
                    Glide.with(context).load(urlImage).placeholder(R.drawable.loading)
                        .into(imageFilm)
                } else {
                    Glide.with(context).load(R.drawable.img_notfound)
                        .placeholder(R.drawable.loading).into(imageFilm)
                }

                titleFilm.text = film.name

                if (film.firstAirDate != "") {
                    yearFilm.text = film.firstAirDate?.substring(0, 4)
                }

                ratingFilm.text = film.voteAverage.toString()

                val currentFilm = MovieCatalogueModel(
                    film.id,
                    film.posterPath,
                    film.name,
                    film.overview,
                    film.voteAverage,
                    film.firstAirDate
                )

                if (film.favorite!!) {
                    insertToFavorite.invisible()
                    deleteFromFavorite.visible()
                } else {
                    deleteFromFavorite.invisible()
                    insertToFavorite.visible()
                }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FILMS, currentFilm)
                    itemView.context.startActivity(intent)
                }

                insertToFavorite.setOnClickListener {
                    tvFavoriteViewModel.updateTvShowToFavorite(
                        TvShowEntity(
                            popular = film.popular,
                            favorite = true,
                            firstAirDate = film.firstAirDate,
                            id = film.id,
                            name = film.name,
                            overview = film.overview,
                            posterPath = film.posterPath,
                            voteAverage = film.voteAverage
                        )
                    )
                    Toast.makeText(context, "This tv show has been added", Toast.LENGTH_SHORT)
                        .show()
                }

                deleteFromFavorite.setOnClickListener {
                    tvFavoriteViewModel.updateTvShowToFavorite(
                        TvShowEntity(
                            popular = film.popular,
                            favorite = false,
                            firstAirDate = film.firstAirDate,
                            id = film.id,
                            name = film.name,
                            overview = film.overview,
                            posterPath = film.posterPath,
                            voteAverage = film.voteAverage
                        )
                    )
                    Toast.makeText(context, "This tv show has been deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setFilm(films: List<TvShowEntity>?) {
        if (films == null) return
        this.listFilms.clear()
        this.listFilms.addAll(films)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_film, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val movie = listFilms[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listFilms.size
}