package com.az.moviesapp.core.baseClasses.recyclerView

import com.az.moviesapp.core.baseClasses.BaseModelView


interface RecyclerViewItemClickListener<T: BaseModelView> {

    fun onItemClick(item: T)
}