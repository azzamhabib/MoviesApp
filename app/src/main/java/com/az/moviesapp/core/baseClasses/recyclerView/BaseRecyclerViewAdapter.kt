package com.az.moviesapp.core.baseClasses.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.az.moviesapp.core.baseClasses.BaseModelView


abstract class BaseRecyclerViewAdapter<T: BaseModelView, VH: BaseRecyclerViewHolder<T>>: RecyclerView.Adapter<VH>(){

    private var items = arrayListOf<T>()
    private var itemClickListener: RecyclerViewItemClickListener<T>? = null

    private fun getItem(position: Int): T = items[position]

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    fun addItemClickListener(itemClickListener: RecyclerViewItemClickListener<T>?) {
        this.itemClickListener = itemClickListener
    }

    fun setList(items: ArrayList<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun resetList(items: ArrayList<T>) {
        this.items.clear()
        setList(items)
    }

    fun removeItem(item: T){
        this.items.remove(item)
        notifyDataSetChanged()
    }

    fun notifyItemChange(oldItem: T ,newItem: T) {
        var index = this.items.indexOf(oldItem)
        this.items.removeAt(index)
        this.items.add(index,newItem)
        notifyDataSetChanged()
    }

}