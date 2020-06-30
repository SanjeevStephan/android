package com.sanjeev.stephan.murmu.sqliteapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(
    private val context: Activity,
    val id: Array<String>,
    val name: Array<String>,
    val email: Array<String>
): ArrayAdapter<String>(context,R.layout.custom_list,name) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_list, null, false)

        val idView = view.findViewById<TextView>(R.id.textViewId)
        val nameView = view.findViewById<TextView>(R.id.textViewName)
        val emailView = view.findViewById<TextView>(R.id.textViewEmail)

        idView.text = "ID : ${id[position]}"
        nameView.text = "NAME : ${name[position]}"
        emailView.text = "EMAIL : ${email[position]}"

        return view
    }
}