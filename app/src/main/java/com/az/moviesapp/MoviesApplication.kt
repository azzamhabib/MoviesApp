package com.az.moviesapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.az.moviesapp.di.networkModule
import com.az.moviesapp.di.repositoryModule
import com.az.moviesapp.di.viewModelModule
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import java.io.InputStream
import java.util.concurrent.TimeUnit

class MoviesApplication: Application() {

    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)
            val modulesList = arrayListOf<Module>()
            modulesList.addAll(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    networkModule
                )
            )
            modules(modulesList)
        }

        initGlide()
    }

    fun initGlide(){
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
        val factory = OkHttpUrlLoader.Factory(client)
        Glide.get(applicationContext).registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }
}