package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 * @author Sanjeev Stephan Murmu
 * @since 12-Jan-20
 * HomeFragment : All the fragments in the app are called from this Fragment.
 */
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentHomeBinding
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.homeRecyclerView.HomeDataModel
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.homeRecyclerView.HomeRecyclerAdapter
import java.lang.Exception

class Home : Fragment() {

    //data binding allows us to replace 'findViewById<>' ,every time we need to find new view
    //it will index all the view and along with the id and save it for later uses
    lateinit var binding: FragmentHomeBinding
    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>

    private val topLevel = TopLevelArray() //creating an instances of 'TopLevel()' class constructor
    private val titleArray = topLevel.getList() // calling the 'getList()' function from the object variable 'topLevel'
    //val booleanAry = topLevel.getBooleanList()
    private val imageArray = topLevel.getImgList()
    private val descArray = topLevel.getDescList()
    private val linkArray = topLevel.getLinkList()
    private val booleanArray = topLevel.getBooleanList()

    /**
     * Create the View of the Fragment.
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the fragment view using the data-binding object class 'DataBindingUtil'
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false)
        return binding.root //return the binding root view
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val arraylist: ArrayList<HomeDataModel> = ArrayList()

        val homeArray = getMyArrayList(titleArray, descArray, imageArray, booleanArray)

        buildRecyclerView(homeArray)
/*
        val titles = topLevel.getArrayList(TopLevelArray.What_Do_You_Want.TITLE)
        val descs = topLevel.getArrayList(TopLevelArray.What_Do_You_Want.DESCRIPTION)
        val images = topLevel.getArrayList(TopLevelArray.What_Do_You_Want.IMAGE)
        val booleans = topLevel.getArrayList(TopLevelArray.What_Do_You_Want.BOOLEAN)*/

        // val homeArray = getMyArrayList(titles,descs,images,booleans)

        /*  arraylist.add(HomeDataModel("Helllo","world",R.drawable.ic_adb_green))
          arraylist.add(HomeDataModel("Helllo","world",R.drawable.ic_adb_green))
          arraylist.add(HomeDataModel("Helllo","world",R.drawable.ic_adb_green))
          arraylist.add(HomeDataModel("Helllo","world",R.drawable.ic_adb_green))
  */

        /* listView = binding.idHomeFragmentListView // here listView's IDs called from the binding variable.
         //       adapter = ArrayAdapter<String>(view.context,android.R.layout.simple_list_item_1,myArray)
         adapter = AdapterCustomMainList(context as Activity,imgAry,myArray)
         listView.adapter = adapter //setting the adapter for the listView.

         //set click event trigger for the listView.
         listView.setOnItemClickListener {
             //parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
             _: AdapterView<*>?, view: View?, position: Int, _: Long ->

             chooseItem(position)
             setLog("Item at position clicked $position")
             //Toast Called from 'MyToast.kt' kotlin file
             setMyToast(view?.context as Activity,"Item Clicked : ${myArray[position]} at position $position")
         }
 */
        setHasOptionsMenu(true)

    }

    private fun buildRecyclerView(arrayList: ArrayList<HomeDataModel>) {
        val recylcerView: RecyclerView = binding.idHomeFragmentListView
        val adapter = HomeRecyclerAdapter(context as Activity, arrayList)
        val linearLayout: RecyclerView.LayoutManager = LinearLayoutManager(context as Activity)
        recylcerView.layoutManager = linearLayout
        recylcerView.adapter = adapter

        adapter.setOnMyItemClickListener(object : HomeRecyclerAdapter.OnMyItemClickListener {
            override fun onMyItemClickListener(position: Int) {

                //HomePopupMenu(recylcerView, position)

            }

            override fun onMyImageClickListener(position: Int) {
                setLog("Image Clicked @position : $position")
            }

            override fun onMySwitchClickListener(position: Int) {
                setLog("${linkArray[position]} : $position")
                setToast(view?.context as Activity, "${linkArray[position]}")
            }
        })
    }


    /**
     * Takes String Message Text and Displays it the LogCat Console.
     * @param message string text
     */
    private fun setLog(message: String) {
        Log.i("TAG", message)
    }


    private fun getMyArrayList(
        titleArray: Array<String>,
        descArray: Array<String>,
        imageArray: Array<Int>,
        isChecked: Array<Boolean>
    ): ArrayList<HomeDataModel> {

        val arraylist: ArrayList<HomeDataModel> = ArrayList()

        for (x in titleArray.indices) {
            arraylist.add(HomeDataModel(titleArray[x].toString(), descArray[x].toString(), R.drawable.ic_adb_green, isChecked[x]))
        }
        return arraylist
    }

}