package com.wahyu.filmskuy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.data.response.TvShowResult
import com.wahyu.filmskuy.models.DetailFilmCatalogue
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 04, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowListAdapter : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {
    private var listFilms = ArrayList<TvShowResult>()

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: TvShowResult) {
            with(itemView) {
                if (film.posterPath != null) {
                    val imageSize = context.getString(R.string.size_url_image_list)
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${film.posterPath}"
                    Picasso.get().load(urlImage).placeholder(R.drawable.loading).into(imageFilm)
                } else {
                    Picasso.get().load(R.drawable.img_notfound).placeholder(R.drawable.loading).into(imageFilm)
                }

                titleFilm.text = film.name

                if (film.firstAirDate != "") {
                    yearFilm.text = film.firstAirDate?.substring(0, 4)
                }

                ratingFilm.text = film.voteAverage.toString()

                val currentFilm = DetailFilmCatalogue(
                    film.id,
                    film.posterPath,
                    film.name,
                    film.overview,
                    film.voteAverage,
                    film.firstAirDate
                )

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FILMS, currentFilm)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun setFilm(films: List<TvShowResult>?) {
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