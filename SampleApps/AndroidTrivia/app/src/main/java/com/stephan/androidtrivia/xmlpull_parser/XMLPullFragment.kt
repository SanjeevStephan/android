package com.stephan.androidtrivia.xmlpull_parser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.stephan.androidtrivia.R
import com.stephan.androidtrivia.databinding.FragmentXmlParserBinding
import java.io.IOException

class XMLPullFragment : Fragment() {

    private lateinit var binding: FragmentXmlParserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentXmlParserBinding>(
            inflater,
            R.layout.fragment_xml_parser,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = binding.parseXmlListView
        var employees: List<Employee>? = null

        try {
            val parser = XMLPullParserHandler()
            val istream = resources.assets.open("employees.xml")

            employees = parser.parse(istream)

            val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, employees)
            listView.adapter = adapter

            listView.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                showMsg(employees[position].name)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun showMsg(name: String) {
        Toast.makeText(view!!.context, name, Toast.LENGTH_SHORT).show()
    }
}