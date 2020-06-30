package com.sanjeev.stephan.murmu.kotlinrecyclerview

/**
 * @author Sanjeev Stephan Murmu
 * @since 08-Feb-2020
 *
 */

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinrecyclerview.MyRecyclerAdapter

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    val myArrayList: ArrayList<MyDataModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildRecyclerView(view)

    }

    fun buildRecyclerView(view: View) {
        myRecyclerView = view.findViewById(R.id.idMyRecyclerView)
        recyclerLayoutManager = LinearLayoutManager(view.context as Activity)
        myRecyclerView.layoutManager = recyclerLayoutManager

        myArrayList.add(MyDataModel(R.drawable.bulb_off,"Light Bulb","Currently OFF"))
        myArrayList.add(MyDataModel(R.drawable.switch_off,"Switch","Currently OFF"))
        myArrayList.add(MyDataModel(R.drawable.fan_off,"Fan","Currently OFF"))
        myArrayList.add(MyDataModel(R.drawable.lock_open,"Door Lock","Currently OFF"))

        val adapter = MyRecyclerAdapter(view.context as Activity, myArrayList)
        myRecyclerView.adapter = adapter

        adapter.setOnMyItemClickListener(object : MyRecyclerAdapter.OnMyItemClickListener
        {
            //Press [ Ctrl + I] to Implement the Interface Methods
            override fun onRecyclerItemClickListener(position: Int) {
                val myDataModel = myArrayList.get(position)
            }

            override fun onRecyclerImageClickListener(position: Int) {
                toast("Image clicked @position $position")
            }

            override fun onRecyclerTitleClickListener(position: Int) {
                toast("Title clicked @position $position")
            }

            override fun onRecyclerSwitchToggleListener(position: Int, isChecked: Boolean) {
                when (isChecked) {
                    true -> toast("Switch ON @position : $position")
                    false -> toast("Switch OFF @position : $position")
                }
            }
        })
    }

    fun toast(message : String) { setMyToast(view?.context as Activity,message)}

}
