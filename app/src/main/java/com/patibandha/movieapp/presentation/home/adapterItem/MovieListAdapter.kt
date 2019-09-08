package com.patibandha.movieapp.presentation.home.adapterItem

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patibandha.movieapp.R
import com.patibandha.movieapp.data.models.Movie
import com.patibandha.movieapp.presentation.home.listener.OnMovieItemClickListener
import com.patibandha.movieapp.presentation.utility.loadImage
import kotlinx.android.synthetic.main.cell_movie_item.view.*

class MovieListAdapter(var list: List<Movie>, val listener: OnMovieItemClickListener) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var filteredData: MutableList<Movie> = arrayListOf()

    init {
        filteredData.addAll(list)
    }

    fun updateDataSet(list: List<Movie>) {
        this.list = list
        filteredData.addAll(list)
    }

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.cell_movie_item, container, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = filteredData.get(position)
        holder.ivImage?.loadImage(movie.imageUrl.orEmpty(), R.drawable.placeholder)
        holder.tvTitle?.text = movie.title
        holder.tvGenre?.text = movie.tags
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage = view.ivImage
        val tvTitle = view.tvTitle
        val tvGenre = view.tvGenre

        init {
            itemView.setOnClickListener {
                listener.onMovieItemClick(list.get(adapterPosition))
            }
        }
    }

    fun filter(searchText: String?) {
        filteredData.clear()
        if (searchText?.isEmpty() == true) {
            filteredData.addAll(list)
        } else {
            filteredData.addAll(list.filter {
                it.title?.contains(searchText.orEmpty(), true) == true ||
                        it?.description?.contains(searchText.orEmpty(), true) == true ||
                        it?.author?.contains(searchText.orEmpty(), true) == true
            })
        }
        notifyDataSetChanged()
    }
}