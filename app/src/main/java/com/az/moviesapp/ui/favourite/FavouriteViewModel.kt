package com.az.moviesapp.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.az.moviesapp.core.baseClasses.BaseViewModel
import com.az.moviesapp.core.data.network.model.Movie
import com.az.moviesapp.core.data.repository.MoviesRepository
import com.az.moviesapp.core.util.Event
import kotlinx.coroutines.launch

class FavouriteViewModel(val moviesRepository: MoviesRepository): BaseViewModel() {

    val favoriteMoviesLiveData = MutableLiveData<Event<List<Movie>>>()

    fun getFavoriteMovies(){
        viewModelScope.launch {
            favoriteMoviesLiveData.value = Event(moviesRepository.getFavoriteMovies())
        }
    }
}