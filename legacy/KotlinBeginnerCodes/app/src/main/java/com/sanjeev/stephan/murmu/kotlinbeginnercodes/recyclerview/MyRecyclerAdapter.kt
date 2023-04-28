package com.sanjeev.stephan.murmu.kotlinbeginnercodes.recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.R

class MyRecyclerAdapter(private val context: Activity,private val arrayList: ArrayList<MyDataModel>) : RecyclerView.Adapter<MyRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_recycler_view_row_item, parent, false)
        return MyRecyclerViewHolder(view,listener)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        val myDataModel = arrayList.get(position)
        //TODO : Bind the Data with the View
        holder.titleView.text = myDataModel.title
        holder.descView.text = myDataModel.desc
        holder.imageView.setImageResource(myDataModel.image)
    }


    private lateinit var listener: OnMyRecyclerItemClickListener

    fun setOnMyRecyclerClickListener(listener: OnMyRecyclerItemClickListener) {
        this.listener = listener
    }

    interface OnMyRecyclerItemClickListener {
        fun onRecyclerItemClickListener(position: Int)
        fun onRecyclerTitleClickListener(position: Int)
        fun onRecyclerImageClickListener(position: Int)
        fun onRecyclerSwitchToggleListener(position: Int,isChecked : Boolean)
    }
}