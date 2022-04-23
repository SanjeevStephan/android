package com.sanjeev.stephan.murmu.smarthome.sample


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.smarthome.R
import com.sanjeev.stephan.murmu.smarthome.Show
import com.sanjeev.stephan.murmu.smarthome.sample.DeviceAdapter.OnMyItemClickListener

/**
 * A simple [Fragment] subclass.
 */
class SampleDevice : Fragment() {

    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var sampleTitle : TextView
    var titleText : String = "Simple Title"
    private val arrayList: ArrayList<DeviceModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titleText = arguments?.getString("title").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sample_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sampleTitle = view.findViewById(R.id.idSimpleTitle)
        sampleTitle.text = titleText
        buildRecyclerView(view)

    }

    private fun buildRecyclerView(view : View) {
        arrayList.add(DeviceModel(R.drawable.bulb_off, "Light Bulb 1"))
        arrayList.add(DeviceModel(R.drawable.bulb_off, "Light Bulb 2"))
        arrayList.add(DeviceModel(R.drawable.bulb_off, "Light Bulb 3"))

        recyclerView = view.findViewById(R.id.idRecyclerViewSample)
        recyclerLayoutManager = LinearLayoutManager(view.context as Activity)
        recyclerView.layoutManager = recyclerLayoutManager
        val adapter = DeviceAdapter(view.context as Activity, arrayList)
        recyclerView.adapter = adapter
        val show = Show(view.context as Activity,"Sample Device")
        adapter.setOnMyItemClickListener(object : OnMyItemClickListener {
            override fun onRecyclerItemClickListener(position: Int) {
                show.setToast("Item Clicked @position $position")
                show.setLog("Item Clicked @position $position")
            }

            override fun onRecyclerImageClickListener(position: Int) {
                show.setToast("My Image Clicked @position $position")
            }

            override fun onRecyclerSwitchToggleListener(position: Int) {
                show.setToast("Recycler Switch Toggle clicked $position")
            }

        })

    }

}
