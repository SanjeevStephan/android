package com.sanjeev.stephan.murmu.kotlinrecyclerview

/**
 * @author Sanjeev Stephan Murmu
 * @since 08-Feb-2020
 *
 */
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinrecyclerview.MyRecyclerAdapter.OnMyItemClickListener

class MyRecyclerViewHolder(itemView: View, listener: OnMyItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.idMyImageView)
    val titleView: TextView = itemView.findViewById(R.id.idMyTitleView)
    val descView: TextView = itemView.findViewById(R.id.idMyDescView)
    val switchView: Switch = itemView.findViewById(R.id.idMySwitchView)

    init {
        itemView.setOnClickListener(View.OnClickListener {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onRecyclerItemClickListener(position)
                setMyLog("ItemView Clicked @position : $position")
            }
        }) //itemView Block Ended


        titleView.setOnClickListener(View.OnClickListener {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) listener.onRecyclerItemClickListener(position)

            setMyLog("TitleView Clicked @position : $position")
        }) //titleView Block Ended

        imageView.setOnClickListener(View.OnClickListener {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) listener.onRecyclerImageClickListener(position)

            setMyLog("ImageView Clicked @position : $position")

        })

        switchView.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener()
        {
            //buttonView: CompoundButton?, isChecked: Boolean ->
                _: CompoundButton?, isChecked: Boolean ->

            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) listener.onRecyclerSwitchToggleListener(position, isChecked)


            //TODO : perform separate task depending on the row item position
            when(position)
            {
                0 -> {
                    if (isChecked) {
                        setMyLog("Light Bulb is Turned ON")
                        descView.text = itemView.context.getString(R.string.bulb_on)
                        imageView.setImageResource(R.drawable.bulb_on)
                    } else {
                        setMyLog("Light Bulb Turned OFF")
                        descView.text = itemView.context.getString(R.string.bulb_off)
                        imageView.setImageResource(R.drawable.bulb_off)
                    }
                }
                1 -> {
                    if (isChecked) {
                        setMyLog("Switch is Turned ON")
                        descView.text = itemView.context.getString(R.string.switch_on)
                        imageView.setImageResource(R.drawable.switch_on)
                    } else {
                        setMyLog("Switch is Turned ON")
                        descView.text = itemView.context.getString(R.string.switch_off)
                        imageView.setImageResource(R.drawable.switch_off)
                    }
                }
                2 -> {
                    if (isChecked) {
                        setMyLog("Fan is Turned ON")
                        descView.text = itemView.context.getString(R.string.fan_on)
                        imageView.setImageResource(R.drawable.fan_on)
                    } else {
                        setMyLog("Fan is Turned OFF")
                        descView.text = itemView.context.getString(R.string.fan_off)
                        imageView.setImageResource(R.drawable.fan_off)
                    }
                }
                else -> {
                    if (isChecked) {
                        setMyLog("Door Lock is CLOSED")
                        descView.text = itemView.context.getString(R.string.lock_on)
                        imageView.setImageResource(R.drawable.lock_closed)
                    } else {
                        setMyLog("Door Lock is OPENED")
                        descView.text = itemView.context.getString(R.string.lock_off)
                        imageView.setImageResource(R.drawable.lock_open)
                    }
                }
            }
        })


    }
}