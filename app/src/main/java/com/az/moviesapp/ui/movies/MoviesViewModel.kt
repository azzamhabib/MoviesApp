package com.az.moviesapp.ui.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.az.moviesapp.core.baseClasses.BaseViewModel
import com.az.moviesapp.core.data.network.model.MoviesResponse
import com.az.moviesapp.core.data.repository.MoviesRepository
import com.az.moviesapp.core.util.Event
import kotlinx.coroutines.launch
import com.az.moviesapp.core.data.network.error_handling.Result
import com.az.moviesapp.core.data.network.model.Movie
import kotlinx.coroutines.delay
import timber.log.Timber


class MoviesViewModel(val moviesRepository: MoviesRepository): BaseViewModel() {

    val moviesLiveData = MutableLiveData<Event<List<Movie>>>()



    fun getMovies(){
        viewModelScope.launch {
            loading.value = true

                when (val response = moviesRepository.getMovies()) {
                    is Result.Success -> {
                        val list = processResponse(response.value?.items)
                        moviesLiveData.value = Event(list)
                    }
                    is Result.Error -> {
                        networkErrorEvent.value = Event(true)
                    }
                }

            loading.value = false
        }
    }


    fun searchForMovie(expression: String){
        viewModelScope.launch {
            loading.value = true

            when (val response = moviesRepository.searchForMovie(expression)) {
                is Result.Success -> {
                    val list = processResponse(response.value?.results)
                    moviesLiveData.value = Event(list)
                }
                is Result.Error -> {
                    networkErrorEvent.value = Event(true)
                }
            }

            loading.value = false
        }
    }


    private fun processResponse(items: List<Movie>?): List<Movie>?{
        val result = mutableListOf<Movie>()
        items?.let{ items ->

                val blockedMovies = moviesRepository.getBlockedMovies()
                val favoriteMovies = moviesRepository.getFavoriteMovies()

                items.forEach { item ->
                    var newItem = item
                    val isFavorite = favoriteMovies.find { it.id == newItem.id }
                    isFavorite?.let {
                        newItem =  newItem.copy(isFavorite = true)
                    }
                    val isBlocked = blockedMovies.find { it.id == newItem.id }
                    isBlocked?.let {
                        newItem = newItem.copy(isBlocked = true)
                    }

                    if(!newItem.isBlocked)
                        result.add(newItem)

                }

                return result

            }


        return result
    }

}