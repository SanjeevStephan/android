package com.sanjeev.stephan.murmu.smarthome.sample

import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.smarthome.R

class DeviceViewHolder(itemView: View, listener: DeviceAdapter.OnMyItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    var deviceName: TextView = itemView.findViewById(R.id.id_device_item_name)
    var deviceStatus: TextView = itemView.findViewById(R.id.id_device_item_status)
    var deviceTime: TextView = itemView.findViewById(R.id.id_device_item_status_data_time)
    var deviceImage: ImageView = itemView.findViewById(R.id.id_device_item_image)
    var deviceSwitch: Switch = itemView.findViewById(R.id.id_device_switch)

    init {

        itemView.setOnClickListener(View.OnClickListener {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) listener.onRecyclerItemClickListener(position)
        })

        deviceName.setOnClickListener(View.OnClickListener {
            //_: View? ->

            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onRecyclerItemClickListener(position)
            }

        }) //deviceName onClick Block ends


        deviceImage.setOnClickListener(View.OnClickListener {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) listener.onRecyclerImageClickListener(position)
        }) //device Image Block Ends

        deviceSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener()
        { _: CompoundButton?, isChecked: Boolean ->

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {

                when(isChecked) {
                    true -> changeImage(R.drawable.bulb_on,"Turned ON")
                    false -> changeImage(R.drawable.bulb_off,"Turned OFF")
                }
            } })

    }
       private fun changeImage(drawableImage: Int,statusMsg : String)
        { deviceImage.setImageResource(drawableImage)
          deviceStatus.text = statusMsg
          deviceTime.text = "NOW"
        }
}