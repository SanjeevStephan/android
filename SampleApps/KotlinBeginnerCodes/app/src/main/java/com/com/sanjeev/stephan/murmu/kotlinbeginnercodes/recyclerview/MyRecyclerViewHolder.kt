package com.sanjeev.stephan.murmu.kotlinbeginnercodes.recyclerview

import android.app.Activity
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.R
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.setMyToast
import kotlinx.android.synthetic.main.layout_recycler_view_row_item.view.*

class MyRecyclerViewHolder(itemView: View, listener: MyRecyclerAdapter.OnMyRecyclerItemClickListener) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.idMyTitleView)
    val descView: TextView = itemView.findViewById(R.id.idMyDescView)
    val imageView: ImageView = itemView.findViewById(R.id.idMyImageView)
    val switchView: Switch = itemView.findViewById(R.id.idMySwitchView)

    init {
        itemView.setOnClickListener(View.OnClickListener {

            val position  = adapterPosition
            if(position != RecyclerView.NO_POSITION) listener.onRecyclerItemClickListener(position)
        }) //itemView Block ends

        titleView.setOnClickListener(View.OnClickListener {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onRecyclerTitleClickListener(position)
            }
            titleView.setText("Title [$position] Clicked")
        }) //titleView Block ends
/*
        imageView.setOnClickListener(View.OnClickListener {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) listener.onRecyclerImageClickListener(position)
        }) //imageView Block ends
*/
        switchView.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener()
        {
            _: CompoundButton?, isChecked: Boolean ->

            val position = adapterPosition

            if(position != RecyclerView.NO_POSITION)
            {
                when(position)
                {
                    0 -> { //TODO : Light Bulb
                        if(isChecked) changeImage(R.drawable.bulb_on,R.string.bulb_on,"Light Bulb Is Turned ON","Light Bulb")
                        else changeImage(R.drawable.bulb_off,R.string.bulb_off,"Light Bulb Is Turned OFF","Light Bulb")

                    }
                    1 -> { //TODO : Switch
                        if(isChecked) changeImage(R.drawable.switch_on,R.string.switch_on,"Switch Is Turned ON","Switch")
                        else changeImage(R.drawable.switch_off,R.string.switch_off,"Switch Is Turned OFF","Switch")

                    }
                    2 -> { //TODO : Door Lock
                        if(isChecked) changeImage(R.drawable.lock_closed,R.string.lock_on,"Door Lock is Closed","Door Lock")
                        else changeImage(R.drawable.lock_open,R.string.lock_off,"Door Lock is Opened","Door Lock")

                    }
                }
            }
        })

    }

    /**
     * @param imageDrawable (Int) Must be Drawable Image Resource i.e(R.drawable.image)
     * @param descMsg (Int) Must be Value String Resource i.e(R.string.message)
     * @param message (String) Simple String Text Message
     */
        private fun changeImage(imageDrawable : Int,descMsg : Int,message : String,titleMsg : String = "Simple Title")
        {
            imageView.setImageResource(imageDrawable)
            descView.text = itemView.context.getString(descMsg)
            titleView.text = titleMsg
            setMyToast(itemView.context as Activity,message)
        }

}