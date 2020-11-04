package com.wahyu.filmskuy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.MovieViewHolder>() {
    private var listFilms = ArrayList<FilmCatalogue>()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: FilmCatalogue) {
            with(itemView) {
                if (film.image != null) {
                    val imageSize = "w780"
                    val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${film.image}"
                    Picasso.get().load(urlImage).into(imageFilm)
                } else {
                    Picasso.get().load(R.drawable.img_notfound).into(imageFilm)
                }

                titleFilm.text = film.title

                if (film.release != "") {
                    yearFilm.text = film.release?.substring(0, 4) ?: "no date"
                }

                ratingFilm.text = film.vote.toString()

                val currentFilm = FilmCatalogue(
                    film.id,
                    film.image,
                    film.title,
                    film.overview,
                    film.vote,
                    film.release
                )

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_FILMS, currentFilm)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun setFilm(films: List<FilmCatalogue>?) {
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