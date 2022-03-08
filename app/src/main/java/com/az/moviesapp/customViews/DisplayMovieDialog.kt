package com.az.moviesapp.customViews

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.az.moviesapp.R
import com.az.moviesapp.core.data.network.model.Movie
import com.az.moviesapp.databinding.LayoutDisplayMovieBinding
import com.bumptech.glide.Glide


class DisplayMovieDialog(var movie: Movie): DialogFragment() {

    lateinit var _binding : LayoutDisplayMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.layout_display_movie, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.80).toInt()
        dialog?.window?.setLayout(width, height)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LayoutDisplayMovieBinding.bind(view)

        _binding.ivCancel.setOnClickListener { dismiss() }

        _binding.tvMovieTitle.text = movie.title
        _binding.message.text = movie.imDbRating
        Glide.with(view).load(movie.image).into(_binding.ivImage)

    }

}