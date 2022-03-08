package com.az.moviesapp.ui.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.az.moviesapp.R
import com.az.moviesapp.core.baseClasses.BaseModelView
import com.az.moviesapp.core.baseClasses.recyclerView.BaseRecyclerViewAdapter
import com.az.moviesapp.core.baseClasses.recyclerView.BaseRecyclerViewHolder
import com.az.moviesapp.core.baseClasses.recyclerView.RecyclerViewItemClickListener
import com.az.moviesapp.core.data.network.model.Movie
import com.az.moviesapp.databinding.ItemMovieBinding
import com.bumptech.glide.Glide


class MoviesAdapter : BaseRecyclerViewAdapter<BaseModelView, MoviesAdapter.MovieViewHolder>()  {

    var items = ArrayList<Movie>()


    interface OnMovieClickedListener {
        fun handleOnMaintenanceRequestClicked(movie: Movie)
    }

    var onMovieClickedListener: OnMovieClickedListener? = null

    interface OnAddToFavoriteClickedListener {
        fun handleAddToFavoriteClicked(movie: Movie)
    }

    var onAddToFavoriteClickedListener: OnAddToFavoriteClickedListener? = null

    interface OnAddToBlockedClickedListener {
        fun handleAddToBlockedClicked(movie: Movie)
    }

    var onAddToBlockedClickedListener: OnAddToBlockedClickedListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie,null))
    }



    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(list: List<Movie>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }




    inner class MovieViewHolder(private val view: View) :
        BaseRecyclerViewHolder<BaseModelView>(view) {
        override fun bind(interfaceImplementation: BaseModelView, onItemClick: RecyclerViewItemClickListener<BaseModelView>?) = with(view) {
            val movie =  interfaceImplementation as Movie
            val binding = ItemMovieBinding.bind(view)


            Glide.with(view).load(movie.image).into(binding.ivPoster)
            binding.tvTitle.text = movie.title
            binding.tvRating.text = movie.imDbRating

            if(movie.isFavorite)
                binding.ivFavorite.setImageResource(android.R.drawable.btn_star_big_on)
            else
                binding.ivFavorite.setImageResource(android.R.drawable.btn_star)

            if(movie.isBlocked){
                this.visibility = View.GONE
            }

            setOnClickListener {  onMovieClickedListener?.handleOnMaintenanceRequestClicked(movie)}
            binding.ivFavorite.setOnClickListener {
                onAddToFavoriteClickedListener?.handleAddToFavoriteClicked(movie)
            }
            binding.ivBlock.setOnClickListener { onAddToBlockedClickedListener?.handleAddToBlockedClicked(movie) }

        }

    }



    fun updateMovieById(movie: Movie){
        for(i in 0..items.lastIndex){
            if(items.get(i).id == movie.id){
                items[i] = items[i].copy(isFavorite = movie.isFavorite)
                break;
            }
        }
        notifyDataSetChanged()
    }

    fun deleteMovieById(movie: Movie){
        for(i in 0..items.lastIndex){
            if(items.get(i).id == movie.id){
                items.removeAt(i)
                break;
            }
        }
        notifyDataSetChanged()
    }
}