package com.sanjeev.stephan.murmu.kotlinrecyclerviewapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView : ImageView = itemView.findViewById(R.id.imageView)
    val titleView : TextView = itemView.findViewById(R.id.title)
    val descView : TextView = itemView.findViewById(R.id.desc)
}