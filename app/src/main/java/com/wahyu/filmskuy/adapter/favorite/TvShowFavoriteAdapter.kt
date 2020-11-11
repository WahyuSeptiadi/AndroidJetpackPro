package com.wahyu.filmskuy.adapter.favorite

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.models.DetailFilmCatalogue
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.viewmodels.local.TvShowFavoriteViewModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*

/**
 * Created by wahyu_septiadi on 11, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowFavoriteAdapter :
    PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.TvShowFavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvShowEntity> =
            object : DiffUtil.ItemCallback<TvShowEntity>() {
                override fun areContentsTheSame(
                    oldItem: TvShowEntity,
                    newItem: TvShowEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areItemsTheSame(
                    oldItem: TvShowEntity,
                    newItem: TvShowEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    inner class TvShowFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowEntity) {
            with(itemView) {
                val tvShowFavoriteViewModel = TvShowFavoriteViewModel(context)
                if (tvShow.posterPath != null) {
                    Log.d("TvDataFavoriteImage", "${tvShow.posterPath}")
                    val imageSize = context.getString(R.string.size_url_image_list)
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${tvShow.posterPath}"
                    Picasso.get().load(urlImage).placeholder(R.drawable.loading)
                        .into(imageFilm)
                } else {
                    Picasso.get().load(R.drawable.img_notfound).placeholder(R.drawable.loading)
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
                        DetailFilmCatalogue(
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

    override fun onBindViewHolder(holder: TvShowFavoriteViewHolder, position: Int) {
        holder.bind(getItem(position) as TvShowEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_film, parent, false)
        return TvShowFavoriteViewHolder(view)
    }

}