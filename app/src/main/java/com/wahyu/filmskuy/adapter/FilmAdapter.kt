package com.wahyu.filmskuy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmModel
import com.wahyu.filmskuy.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item_film.view.*
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.MovieViewHolder>() {
    private var listFilms = ArrayList<FilmModel>()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: FilmModel) {
            with(itemView) {
                Picasso.get().load(film.image).into(imageFilm)
                titleFilm.text = film.title
                yearFilm.text = film.year
                ratingFilm.text = film.rating

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIES, film.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun setFilm(films: List<FilmModel>?) {
        if (films == null) return
        this.listFilms.clear()
        this.listFilms.addAll(films)
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