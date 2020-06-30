package com.sanjeev.stephan.murmu.mentorme

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapterTraderListItem(
    private val context: Activity,
    private val images: Array<Int>,
    private val titles: Array<String>,
    private val desc: Array<String>
) : ArrayAdapter<String>(context, R.layout.layout_custom_list_item, titles) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_custom_list_item, null, false)

        val titleView = view.findViewById<TextView>(R.id.id_trader_list_item_title)
        val descView  = view.findViewById<TextView>(R.id.id_trader_list_item_desc)
        val imageView = view.findViewById<ImageView>(R.id.id_trader_list_item_image)

        titleView.text = titles[position]
        descView.text = desc[position]
        imageView.setImageResource(images[position])

        return view
    }

}