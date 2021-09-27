package com.example.numbersgameapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

//fisrt class extend recuclerView.adapter <> while the second extend RecyclerView.ViewHolder<>

class RecyclerAdapter (var messages:ArrayList<String>): RecyclerView.Adapter <RecyclerAdapter.ItemViewHolder> () {
    class ItemViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val msg=messages[position]
        holder.itemView.apply {
            tv.text= msg
        }

    }

    override fun getItemCount()= messages.size

}