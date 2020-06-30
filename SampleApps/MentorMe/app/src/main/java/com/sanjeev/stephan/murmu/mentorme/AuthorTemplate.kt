package com.sanjeev.stephan.murmu.mentorme

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class AuthorTemplate : Fragment() {

    lateinit var bookListView : ListView
    lateinit var authorImageView: ImageView
    lateinit var authorNameView : TextView

    private var bookList: Array<String>? = arrayOf("hello")
    var authorName : String? = "Author name Here"
    var image: Int = R.drawable.robertkiyosaki

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookList = arguments?.getStringArray("bookList") as Array<String>
        authorName = arguments?.getString("authorName").toString()
        image = arguments?.getInt("image")!!.toInt()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_author_templates,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorNameView = view.findViewById(R.id.id_temp_author_name)
        authorImageView = view.findViewById(R.id.id_temp_author_img)
        bookListView = view.findViewById(R.id.id_book_list_view)

        authorNameView.text = authorName
        authorImageView.setImageResource(image)


        val adapter = ArrayAdapter<String>(view.context as Activity,android.R.layout.simple_list_item_1, bookList as Array<out String>)
        bookListView.adapter = adapter

    }

}