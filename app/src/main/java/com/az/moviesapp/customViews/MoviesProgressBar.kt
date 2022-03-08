package com.az.moviesapp.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.az.moviesapp.R

class MoviesProgressBar(context : Context, attrs : AttributeSet?) : FrameLayout(context, attrs) {
    private val flProgressRoot : FrameLayout
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_movies_progress, this)
        flProgressRoot = findViewById(
            R.id.fl_progress_root)
    }
    fun showProgress(show : Boolean) {
        flProgressRoot.visibility = if (show) VISIBLE else GONE
    }
}