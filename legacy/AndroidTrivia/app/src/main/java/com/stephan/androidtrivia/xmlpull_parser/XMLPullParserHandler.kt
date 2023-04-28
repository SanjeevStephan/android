package com.stephan.androidtrivia.xmlpull_parser

import android.util.Log
import android.widget.Toast
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

/**
 * This Class reads the data from the XML Document and
 * Stores it in the ArrayList<Employee> with Employee as a model structure.
 */

class XMLPullParserHandler {
    private val employees = ArrayList<Employee>()
    private var employee: Employee? = null
    private lateinit var text: String

    fun parse(inputStream: InputStream): List<Employee> {

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true

            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)

            var eventType = parser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {

                val tagname = parser.name

                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagname.equals("employee", ignoreCase = true)) {
                        //create a new instance of employee
                        employee = Employee()
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("employee", ignoreCase = true)) {
                        // add employee object to list
                        employee?.let { employees.add(it) }
                    } else if (tagname.equals("id", ignoreCase = true)) {
                        employee!!.id = Integer.parseInt(text)
                    } else if (tagname.equals("name", ignoreCase = true)) {
                        employee!!.name = text
                    } else if (tagname.equals("salary", ignoreCase = true)) {
                        employee!!.salary = java.lang.Float.parseFloat(text)
                    }

                    else -> {
                        Log.i("TAG","No Data Loaded")
                    }
                }

                eventType = parser.next()
            }


        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return employees

    }
}