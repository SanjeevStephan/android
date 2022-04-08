package com.sanjeev.stephan.murmu.kotlinbeginnercodes

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * @see [Android_Access_URL_WhatsApp]
 * @see [Android_Access_URL_WhatsApp_CustomAdapter]
 * @see [R.layout.fragment_android__access__url__whats_app]
 * @see [R.layout.layout_custom_access_url_whatsapp]
 */
class Android_Access_URL_WhatsApp_CustomAdapter(
    val context: Activity,
    val titles: Array<String>,
    val descs: Array<String>
) :
    ArrayAdapter<String>(context, R.layout.layout_custom_access_url_whatsapp,titles) {

    private lateinit var titleView : TextView
    private lateinit var descView  : TextView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_custom_access_url_whatsapp, null, false)

        titleView = view.findViewById(R.id.id_custom_access_url_title)
        descView = view.findViewById(R.id.id_custom_access_url_desc)

        titleView.text = titles[position]
        descView.text = descs[position]

        return view
    }
}