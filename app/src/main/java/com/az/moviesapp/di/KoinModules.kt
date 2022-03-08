package com.az.moviesapp.di

import androidx.room.Room
import com.az.moviesapp.core.data.db.MoviesDataBase
import com.az.moviesapp.core.data.repository.MoviesRepository
import com.az.moviesapp.core.data.repository.MoviesRepositoryImpl
import com.az.moviesapp.ui.favourite.FavouriteViewModel
import com.az.moviesapp.ui.movies.MoviesViewModel
import com.enayakw.app.core.data.network.RetrofitFactory
import com.enayakw.app.core.data.shared_prefs.SharedPrefs
import com.enayakw.app.core.data.shared_prefs.SharedPrefsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { Room.databaseBuilder(androidContext(),MoviesDataBase::class.java,"movies_db").allowMainThreadQueries().build().getRunDao() }
    single { MoviesRepositoryImpl(get(),get()) as MoviesRepository }
}



val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { FavouriteViewModel(get()) }
}

val networkModule = module {
    single { RetrofitFactory.create(get()) }
}