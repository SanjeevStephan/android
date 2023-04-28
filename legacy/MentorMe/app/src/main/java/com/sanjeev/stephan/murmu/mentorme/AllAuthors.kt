package com.sanjeev.stephan.murmu.mentorme

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.sanjeev.stephan.murmu.mentorme.myarrays.ArrayAllAuthors
import com.sanjeev.stephan.murmu.mentorme.quotes.RobertKiyosaki

class AllAuthors : Fragment()
{
  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_all_authors_list, null, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


    val authors = ArrayAllAuthors()
    val arrayList = authors.getAuthors(ArrayAllAuthors.What_Do_You_Want.AUTHOR_NAME)

    val listView = view.findViewById<ListView>(R.id.id_all_authors_list)
    val adapter = ArrayAdapter<String>(view.context as Activity,android.R.layout.simple_list_item_1, arrayList)
    listView.adapter = adapter
  }


}