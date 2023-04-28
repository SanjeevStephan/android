package com.sanjeev.stephan.murmu.kotlinbeginnercodes

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.layout_custom_main_list.view.*

class AdapterCustomMainList(
    private val context: Activity,
    private val imgAry: Array<Int>,
    private val title: Array<String>
) :
    ArrayAdapter<String>(context, R.layout.layout_custom_main_list, title) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_custom_main_list, null, false)
        // val tickBox = view.id_check_box
        val titleBox = view.id_list_title
        val imgBox = view.id_list_img

        // titleBox.isEnabled = tickMark[position]
        // tickBox.isChecked = tickMark[position]
        titleBox.text = title[position]
        imgBox.setImageResource(imgAry[position])

        return view
    }
}