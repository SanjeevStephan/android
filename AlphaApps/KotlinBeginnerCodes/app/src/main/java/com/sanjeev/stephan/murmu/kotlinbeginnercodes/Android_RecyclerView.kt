package com.sanjeev.stephan.murmu.kotlinbeginnercodes


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.recyclerview.MyDataModel
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.recyclerview.MyRecyclerAdapter

/**
 * A simple [RecyclerView] subclass.
 * @see MyDataModel
 * @see MyRecyclerAdapter
 * @see MyRecyclerViewHolder
 * @see R.layout.fragment_android__recycler_view
 * @see R.layout.layout_recycler_view_row_item
 */
class Android_RecyclerView : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var recyclerLayoutManager : RecyclerView.LayoutManager
    var arrayList: ArrayList<MyDataModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android__recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildRecyclerview(view)
    }

    private fun buildRecyclerview(view: View){

        recyclerView = view.findViewById(R.id.idMyRecyclerView)
        recyclerLayoutManager = LinearLayoutManager(view.context as Activity)
        recyclerView.layoutManager = recyclerLayoutManager

        arrayList.add(MyDataModel("Light Bulb","Currently OFF",R.drawable.bulb_off))
        arrayList.add(MyDataModel("Switch","Currently OFF",R.drawable.switch_off))
        arrayList.add(MyDataModel("Door Lock","Currently OPENED",R.drawable.lock_open))

        val adapter = MyRecyclerAdapter(view.context as Activity, arrayList)
        recyclerView.adapter = adapter
        adapter.setOnMyRecyclerClickListener(object : MyRecyclerAdapter.OnMyRecyclerItemClickListener
        {
            override fun onRecyclerItemClickListener(position: Int) {
                toast("Item @position : $position Clicked")
            }

            override fun onRecyclerTitleClickListener(position: Int) {
              //  toast("Item @position : $position Clicked")

            }

            override fun onRecyclerImageClickListener(position: Int) {
                val name: MyDataModel = arrayList.get(position)
                toast("Image [$name Clicked")

            }

            override fun onRecyclerSwitchToggleListener(position: Int, isChecked: Boolean) {
               // toast("Item @position : $position Clicked")
            }

        })

    }

    fun toast(msg : String)
    {
        setMyToast(view?.context as Activity,msg)
    }

}
