package com.az.moviesapp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.az.moviesapp.R
import com.az.moviesapp.core.baseClasses.BaseFragment
import com.az.moviesapp.core.data.db.MoviesDao
import com.az.moviesapp.core.data.network.model.Movie
import com.az.moviesapp.customViews.DisplayMovieDialog
import com.az.moviesapp.databinding.FragmentFavouriteBinding
import com.az.moviesapp.databinding.FragmentMoviesBinding
import com.az.moviesapp.ui.movies.MoviesAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment: BaseFragment() , MoviesAdapter.OnMovieClickedListener, MoviesAdapter.OnAddToBlockedClickedListener{


    private val favouriteViewModel by viewModel<FavouriteViewModel>()
    val moviesDao: MoviesDao by inject()
    private lateinit var _favouriteBinding: FragmentFavouriteBinding

    val moviesAdapter = MoviesAdapter()


    override fun whichLayout(): Int = R.layout.fragment_favourite

    override fun setTitle(): Int = R.string.empty_string


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _favouriteBinding = FragmentFavouriteBinding.bind(view)
        initAdapter()

        favouriteViewModel.getFavoriteMovies()

        observeViewModel()


    }

    private fun initAdapter() {
        val verticalLayoutManager = GridLayoutManager(
            activity, 1,
            GridLayoutManager.VERTICAL, false
        )

        moviesAdapter.onMovieClickedListener = this
        moviesAdapter.onAddToBlockedClickedListener = this

        _favouriteBinding.rvMovies.layoutManager = verticalLayoutManager
        _favouriteBinding.rvMovies.adapter = moviesAdapter
    }

    private fun observeViewModel(){
        favouriteViewModel.favoriteMoviesLiveData.observe(viewLifecycleOwner, Observer {
            if(it.isNotConsumed()) {
                val result = it.consume()
                result?.let { it1 -> moviesAdapter.addItems(it1) }
            }
        })
    }

    override fun handleOnMaintenanceRequestClicked(movie: Movie) {
        DisplayMovieDialog(movie).show(childFragmentManager,"DisplayMovieDialog")
    }

    override fun handleAddToBlockedClicked(movie: Movie) {
        val newMovie = movie.copy(isBlocked = true)
        lifecycleScope.launch {
            moviesDao.insertMovie(newMovie)
        }
        moviesAdapter.deleteMovieById(newMovie)
    }
}