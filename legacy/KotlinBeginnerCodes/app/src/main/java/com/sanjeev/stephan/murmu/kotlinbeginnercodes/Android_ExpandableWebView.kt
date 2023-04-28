package com.sanjeev.stephan.murmu.kotlinbeginnercodes


/**
 * @author Sanjeev Stephan Murmu
 * @since 23th-Jan-20
 * Visit Below Link For More Detailed Information on Expandable List View
 * @see <a href="https://tutorialwing.com/android-expandablelistview-using-kotlin-example/">Expandale List View</a>
 *
 * 1.Create View for Child Item In ExpandableListView
 *   Since we need an xml file that contains ui for a child item in expandableListView.
 *   So, create an xml file in main/res/layout folder with name [R.layout.layout_expandable_list_item.xml]
 *2. Create View For Group Item in ExpandableListView
 *   Now, we need an ui for group item in expandableListView.
 *   So, create an xml file, named [R.layout.layout_expandable_list_group.xml], in main/res/layout folder.
 *3.Create Adapter For ExpandableListView
 *   Now, we will create adapter for expandableListView that will be used to provide data to the view.
 *   So, create a kotlin file , named [Android_ExpandableListView_CustomAdapter.kt]
 */

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.fragment.app.Fragment

import java.util.ArrayList
import java.util.HashMap

/**
 * this class provides data for an item to the expandableListView.
 * We have inherited this class from BaseExpandableListAdapter class.
 * A constructor has also been defined that accepts context, titleList and dataList.
 * Then, we have overridden some of the methods in this class.
 *
 * This class needs below files to work!
 * @see Android_ExpandableWebView  -> Kotlin Class
 * @see Android_ExpandableListView_CustomAdapter -> Kotlin Class
 * @see R.layout.fragment_expandable_list_view -> XML Layout
 * @see R.layout.layout_expandable_list_item -> XML Layout
 * @see R.layout.layout_expandable_list_group -> XML Layout
 */
class Android_ExpandableWebView : Fragment() {

    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String>? = null

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val redmiMobiles = ArrayList<String>()
            redmiMobiles.add("https://in.investing.com/")
           /* redmiMobiles.add("Redmi S2")
            redmiMobiles.add("Redmi Note 5 Pro")
            redmiMobiles.add("Redmi Note 5")
            redmiMobiles.add("Redmi 5 Plus")
            redmiMobiles.add("Redmi Y1")
            redmiMobiles.add("Redmi 3S Plus")*/

            val micromaxMobiles = ArrayList<String>()
            micromaxMobiles.add("https://in.investing.com/")
/*            micromaxMobiles.add("Micromax Bharat 5 Pro")
            micromaxMobiles.add("Micromax Bharat 5")
            micromaxMobiles.add("Micromax Canvas 1")
            micromaxMobiles.add("Micromax Dual 5")*/

            val appleMobiles = ArrayList<String>()
            appleMobiles.add("https://in.investing.com/")
/*            appleMobiles.add("iPhone 8 Plus")
            appleMobiles.add("iPhone X")
            appleMobiles.add("iPhone 7 Plus")
            appleMobiles.add("iPhone 7")
            appleMobiles.add("iPhone 6 Plus")*/

            val samsungMobiles = ArrayList<String>()
            samsungMobiles.add("https://in.investing.com/")
/*            samsungMobiles.add("Samsung Galaxy Note 7")
            samsungMobiles.add("Samsung Galaxy Note 5 Dual")
            samsungMobiles.add("Samsung Galaxy S8")
            samsungMobiles.add("Samsung Galaxy A8")
            samsungMobiles.add("Samsung Galaxy Note 4")*/

            listData["Redmi"] = redmiMobiles
            listData["Micromax"] = micromaxMobiles
            listData["Apple"] = appleMobiles
            listData["Samsung"] = samsungMobiles

            return listData
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expandable_list_view, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandableListView = view.findViewById(R.id.expandableListView)
        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = Android_ExpandableWebView_CustomAdapter(
                view.context as Activity,
                titleList as ArrayList<String>,
                listData
            )
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                Toast.makeText(
                    view.context as Activity,
                    (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            /*
            We need groupCollapsed listener to perform some operations whenever any group is collapsed
             */
            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(
                    view.context as Activity,
                    (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            /*
            We need child click listener in expandableListView to perform some operations when any child item is clicked.
            Here, we are also showing toast message whenever any child item is clicked.
             */
            expandableListView!!.setOnChildClickListener {
                // parent, v, groupPosition, childPosition, id ->
                    _, _, groupPosition, childPosition, _ ->
                Toast.makeText(
                    view.context as Activity,
                    "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + listData[(titleList as ArrayList<String>)[groupPosition]]!!.get(
                        childPosition
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
        }

    }
}
