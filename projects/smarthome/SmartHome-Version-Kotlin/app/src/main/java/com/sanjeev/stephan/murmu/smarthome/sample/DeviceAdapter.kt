package com.sanjeev.stephan.murmu.smarthome.sample

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.smarthome.R

class DeviceAdapter(private val context: Activity, private val arrayList: ArrayList<DeviceModel>) :
    RecyclerView.Adapter<DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_layout_fragment_devices, parent, false)
        return DeviceViewHolder(view,listenerMeHere)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val deviceModel: DeviceModel = arrayList.get(position)

        holder.deviceName.text = deviceModel.name
        holder.deviceImage.setImageResource(deviceModel.image)
    }

    lateinit var listenerMeHere : OnMyItemClickListener

    fun setOnMyItemClickListener(listener: OnMyItemClickListener) {
        this.listenerMeHere = listener
    }

    interface OnMyItemClickListener{
        fun onRecyclerItemClickListener(position: Int)
        fun onRecyclerImageClickListener(position: Int)
        fun onRecyclerSwitchToggleListener(position: Int)
    }
}