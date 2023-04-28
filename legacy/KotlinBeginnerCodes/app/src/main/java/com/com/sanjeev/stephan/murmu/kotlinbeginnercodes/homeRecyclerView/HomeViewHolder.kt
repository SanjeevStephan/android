package com.sanjeev.stephan.murmu.kotlinbeginnercodes.homeRecyclerView

import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.R
import kotlinx.android.synthetic.main.layout_recycler_view_row_item.view.*

class HomeViewHolder(itemView: View,listener: HomeRecyclerAdapter.OnMyItemClickListener): RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.idMyImageView)
    val title: TextView = itemView.findViewById(R.id.idMyTitleView)
    val desc: TextView = itemView.findViewById(R.id.idMyDescView)
    val switch: Switch = itemView.findViewById(R.id.idMySwitchView)

    init {
        itemView.setOnClickListener() {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onMyItemClickListener(position)
            }
        }

        image.setOnClickListener() {
            val position = adapterPosition

            if(position != RecyclerView.NO_POSITION)
            {
                listener.onMySwitchClickListener(position)
            }
        }
    }

}