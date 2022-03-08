package com.az.moviesapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.az.moviesapp.R
import com.az.moviesapp.core.baseClasses.BaseFragment
import com.az.moviesapp.core.data.db.MoviesDao
import com.az.moviesapp.core.data.network.model.Movie
import com.az.moviesapp.customViews.DisplayMovieDialog
import com.az.moviesapp.databinding.FragmentMoviesBinding
import com.az.moviesapp.databinding.FragmentSearchBinding
import com.az.moviesapp.ui.movies.MoviesAdapter
import com.az.moviesapp.ui.movies.MoviesViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: BaseFragment(), MoviesAdapter.OnMovieClickedListener, MoviesAdapter.OnAddToFavoriteClickedListener , MoviesAdapter.OnAddToBlockedClickedListener {

    private val moviesViewModel by viewModel<MoviesViewModel>()

    private lateinit var _moviesBinding: FragmentSearchBinding

    val moviesDao: MoviesDao by inject()

    val moviesAdapter = MoviesAdapter()

    override fun whichLayout(): Int = R.layout.fragment_search

    override fun setTitle(): Int = R.string.empty_string

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _moviesBinding = FragmentSearchBinding.bind(view)

        initAdapter()

        observeViewModel()

        _moviesBinding.ivSearch.setOnClickListener {
            moviesViewModel.searchForMovie(_moviesBinding.etSearch.text.toString())
        }

    }


    private fun initAdapter() {
        val verticalLayoutManager = GridLayoutManager(
            activity, 1,
            GridLayoutManager.VERTICAL, false
        )

        moviesAdapter.onMovieClickedListener = this
        moviesAdapter.onAddToFavoriteClickedListener = this
        moviesAdapter.onAddToBlockedClickedListener = this

        _moviesBinding.rvMovies.layoutManager = verticalLayoutManager
        _moviesBinding.rvMovies.adapter = moviesAdapter
    }


    private fun observeViewModel(){
        moviesViewModel.loading.observe(viewLifecycleOwner, Observer {
            _moviesBinding.moviesProgressBar.showProgress(it)
        })

        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            if(it.isNotConsumed()){
                val response = it.consume()
                response?.let { list -> moviesAdapter.addItems(list) }
            }
        })
    }

    override fun handleOnMaintenanceRequestClicked(movie: Movie) {
        DisplayMovieDialog(movie).show(childFragmentManager,"DisplayMovieDialog")
    }

    override fun handleAddToFavoriteClicked(movie: Movie) {
        val newMovie = movie.copy(isFavorite = true)
        lifecycleScope.launch {
            moviesDao.insertMovie(newMovie)
        }
        moviesAdapter.updateMovieById(newMovie)
    }

    override fun handleAddToBlockedClicked(movie: Movie) {
        val newMovie = movie.copy(isBlocked = true)
        lifecycleScope.launch {
            moviesDao.insertMovie(newMovie)
        }
        moviesAdapter.deleteMovieById(newMovie)
    }
}