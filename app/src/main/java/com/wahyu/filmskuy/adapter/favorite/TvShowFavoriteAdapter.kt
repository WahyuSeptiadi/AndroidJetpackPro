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
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.models.FilmCatalogueModel
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.viewmodels.local.TvShowFavoriteViewModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*

/**
 * Created by wahyu_septiadi on 11, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowFavoriteAdapter : RecyclerView.Adapter<TvShowFavoriteAdapter.TvShowFavoriteViewHolder>() {

    private var listFilms = ArrayList<TvShowEntity>()

    class TvShowFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowEntity) {
            with(itemView) {
                val tvShowFavoriteViewModel = TvShowFavoriteViewModel(context)
                if (tvShow.posterPath != null) {
                    Log.d("TvDataFavoriteImage", "${tvShow.posterPath}")
                    val imageSize = context.getString(R.string.size_url_image_list)
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${tvShow.posterPath}"
                    Glide.with(context).load(urlImage).placeholder(R.drawable.loading)
                        .into(imageFilm)
                } else {
                    Glide.with(context).load(R.drawable.img_notfound).placeholder(R.drawable.loading)
                        .into(imageFilm)
                }

                titleFilm.text = tvShow.name

                if (tvShow.firstAirDate != "") {
                    yearFilm.text = tvShow.firstAirDate?.substring(0, 4)
                    Log.d("TvDataFavoriteDate", "${tvShow.firstAirDate}")
                }

                ratingFilm.text = tvShow.voteAverage.toString()

                itemView.setOnClickListener {
                    val tvShowIntent = Intent(context, DetailActivity::class.java)

                    tvShowIntent.putExtra(
                        DetailActivity.EXTRA_FILMS,
                        FilmCatalogueModel(
                            tvShow.id,
                            tvShow.posterPath,
                            tvShow.name,
                            tvShow.overview,
                            tvShow.voteAverage,
                            tvShow.firstAirDate
                        )
                    )
                    context.startActivity(tvShowIntent)
                }

                toggleButton.setOnClickListener {
                    tvShowFavoriteViewModel.deleteTvShow(tvShow)
                    Toast.makeText(context, "Tv Show has been deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setFilms(films: List<TvShowEntity>?) {
        if (films == null) return
        this.listFilms.clear()
        this.listFilms.addAll(films)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TvShowFavoriteViewHolder, position: Int) {
        val tvShow = listFilms[position]
        holder.bind(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_film, parent, false)
        return TvShowFavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFilms.size
    }

}