package com.az.moviesapp.core.baseClasses.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.az.moviesapp.core.baseClasses.BaseModelView


abstract class BaseRecyclerViewHolder<T: BaseModelView>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun  bind(item: T, onItemClick: RecyclerViewItemClickListener<T>? = null)
}