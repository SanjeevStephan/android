package com.sanjeev.stephan.murmu.kotlinrecyclerview

/**
 * @author Sanjeev Stephan Murmu
 * @since 08-Feb-2020
 *
 */
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(
    private val context: Activity,
    private val myArrayList: ArrayList<MyDataModel>
) : RecyclerView.Adapter<MyRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_recycler_view_row_item, parent, false)
        return MyRecyclerViewHolder(view,listener)
    }

    override fun getItemCount(): Int {
        //returns the total item in the arrayList row.
        return myArrayList.size
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        val myDataModel = myArrayList.get(position)
        holder.titleView.text = myDataModel.title
        holder.descView.text = myDataModel.desc
        holder.imageView.setImageResource(myDataModel.image)
    }

    interface OnMyItemClickListener{
        fun onRecyclerItemClickListener(position: Int)
        fun onRecyclerImageClickListener(position: Int)
        fun onRecyclerTitleClickListener(position: Int)
        fun onRecyclerSwitchToggleListener(position: Int,isChecked: Boolean)
    }

    lateinit var listener : OnMyItemClickListener

    fun setOnMyItemClickListener(listener: OnMyItemClickListener){
        this.listener = listener
    }

}