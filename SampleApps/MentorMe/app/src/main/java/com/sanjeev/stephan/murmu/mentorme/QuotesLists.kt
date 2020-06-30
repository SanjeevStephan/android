package com.sanjeev.stephan.murmu.mentorme

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sanjeev.stephan.murmu.mentorme.quotes.RobertKiyosaki

class QuotesLists : Fragment() {

  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_quotes_list, null, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val robert = RobertKiyosaki()
    val quotes = robert.getData(RobertKiyosaki.What_Do_You_Want.QUOTES)

    val listView = view.findViewById<ListView>(R.id.id_quotes_list_view)
    val adapter = ArrayAdapter<String>(view.context as Activity,android.R.layout.simple_list_item_1,quotes)
    listView.adapter = adapter
    listView.setOnItemClickListener{
      _, _, _, _ ->
      findNavController().navigate(R.id.displayWebPage)
    }

  }

}