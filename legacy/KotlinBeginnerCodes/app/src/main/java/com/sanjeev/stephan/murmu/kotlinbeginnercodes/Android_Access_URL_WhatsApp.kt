package com.sanjeev.stephan.murmu.kotlinbeginnercodes


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * A simple [Fragment] subclass.
 * @see [Android_Access_URL_WhatsApp]
 * @see [Android_Access_URL_WhatsApp_CustomAdapter]
 * @see [R.layout.fragment_android__access__url__whats_app]
 * @see [R.layout.layout_custom_access_url_whatsapp]
 * Also Check out the below URL to more documentation.
 * @see https://www.tutorialkart.com/kotlin-android/android-open-url-in-browser-activity/
 */
class Android_Access_URL_WhatsApp : Fragment() {

    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android__access__url__whats_app, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titlesArray = arrayOf(
            "Visit Google.com",
            "Open Github.com",
            "Open WhatsApp API Chat",
            "Open Youtube",
            "Open Call Dialer",
            "Open Email"
        )

        val arrayList = arrayOf(
            "https://www.google.com/", //url to google
            "https://github.com/SanjeevStephanMurmu", //url to github.com
            "https://api.whatsapp.com/send?phone=+919661269211", // open whatsapp chat
            "https://www.youtube.com/", // open youtube link
            "tel:9661269211", //open call dialer
            "mailto:samstephan069@gmail.com"
        )

        listView = view.findViewById(R.id.id_android_access_url_list_view)

        /*  val adapter = ArrayAdapter<String>(
              view.context as Activity,
              android.R.layout.simple_list_item_1,
              arrayList)
  */
        val adapter = Android_Access_URL_WhatsApp_CustomAdapter(view.context as Activity,titlesArray,arrayList)

        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->

            openURL(arrayList[position].toString())

        }
    }

    /**
     * @param url
     */
    private fun openURL(url: String) {
        val accessLink = Intent(android.content.Intent.ACTION_VIEW)
        accessLink.data = Uri.parse(url)
        startActivity(accessLink)
    }

}
