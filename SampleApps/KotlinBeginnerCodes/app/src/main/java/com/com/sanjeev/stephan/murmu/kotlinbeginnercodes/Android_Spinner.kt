package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
@author Sanjeev Stephan Murmu
@since 2nd-Feb-2020
**/

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

/**
 * A simple [Android_Spinner] subclass.
 * This Fragment Class Displays a Dropdown Spinner Menu and
 * Performs action when an drop-down item is clicked.
 * @see [R.layout.fragment_android__spinner]
 * @see https://www.tutorialkart.com/kotlin-android/android-spinner-kotlin-example/
 */
class Android_Spinner : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var spinner: Spinner
    lateinit var textView : TextView
    val arrayList = arrayOf("Select Your Dream Car!","Audi","BMW","Lamborghini","Ferrari","McLaren")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android__spinner, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = view.findViewById(R.id.spinner)
        textView = view.findViewById(R.id.id_spinner_text_view)


        spinner.setOnItemSelectedListener(this)
        //create An ArrayAdapter using simple spinner layout and language array
        val adapter = ArrayAdapter(view.context as Activity,android.R.layout.simple_spinner_item,arrayList)
        //Set Layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Set Adapter to Spinner
        spinner.adapter = adapter

    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        textView.text = "Selected : ${arrayList[position].toString()}"
    }
}
