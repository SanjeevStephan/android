package com.sanjeev.stephan.murmu.mentorme

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class TraderTemplate : Fragment() {

    lateinit var traderImage: ImageView
    lateinit var traderNameView: TextView
    lateinit var listView: ListView
    var image : Int = R.drawable.robertkiyosaki
    var name : String = "Trader Name"
    lateinit var arrayList: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        image = arguments?.getInt("traderImage")!!.toInt()
        name  = arguments?.getString("traderName").toString()
        arrayList = arguments?.getStringArray("arrayList") as Array<String>
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trader_templates, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        traderImage = view.findViewById(R.id.id_temp_trader_img)
        traderNameView = view.findViewById(R.id.id_temp_trader_name)
        listView = view.findViewById(R.id.id_trader_list_view)

        traderImage.setImageResource(image)
        traderNameView.text = name


        val images = arrayOf(R.drawable.stock_market,R.drawable.website_logo,R.drawable.youtube_logo,
                R.drawable.stock_market,R.drawable.website_logo,R.drawable.youtube_logo,R.drawable.youtube_logo)
        val defaultTitles = resources.getStringArray(R.array.trader_default_list_items)

        // val adapter = ArrayAdapter<String>(view.context as Activity,android.R.layout.simple_list_item_1,arrayList)
        val adapter = CustomAdapterTraderListItem(view.context as Activity,images,defaultTitles,arrayList)
        listView.adapter = adapter

        listView.setOnItemClickListener{
            _, _, position, _ ->

            itemChoosen(name,position)
        }
    }


    private fun itemChoosen(traderName : String,position : Int){

        var url = arrayList[1].toString()
        var navigationId = 0

        when(name) {
            resources.getString(R.string.timothy_sykes) -> {
                when(position) {
                    0 -> navigateToWebPage("file:///android_asset/traders/tim_sykes/mystory.html")
                    1 -> navigateToWebPage(arrayList[1].toString())
                }

            }
            resources.getString(R.string.steven_dux) -> {
                when(position){
                   // 0 -> navigateToWebPage("file:///android_asset/traders/steven_dux/mystory.html")
                    0 -> findNavController().navigate(R.id.action_traderTemplate_to_android_ExpandableWebView)
                    1 -> navigateToWebPage(arrayList[1].toString())
                }
            }
            resources.getString(R.string.ricky_gutierrez) -> {
                when(position){
                    0 -> navigateToWebPage("file:///android_asset/traders/ricky_gutierrez/mystory.html")
                    1 -> navigateToWebPage(arrayList[1].toString())
                }
            }
        }
    }

    private fun navigateToWebPage(url: String = "file:///android_asset/robert_kiyosaki/how_to_create_confidence.html"){

        setLog("URL : $url")
        val bundle = bundleOf("url" to url)
        findNavController().navigate(R.id.action_traderTemplate_to_displayWebPage,bundle)
    }
}