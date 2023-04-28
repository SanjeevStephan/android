package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;


public class ViewHolderNewDeviceCategory extends RecyclerView.ViewHolder {

    ImageView deviceImage;
    TextView deviceTitle,totalDevices;
    CardView cardItem;
    public ViewHolderNewDeviceCategory(@NonNull View itemView, final AdapterNewDeviceCategory.onNewDevice_ClickListener listener)
    {
        super(itemView);

        deviceImage = (ImageView)itemView.findViewById(R.id.id_new_device_image_icon);
        deviceTitle = (TextView)itemView.findViewById(R.id.id_new_device_title);
        totalDevices = (TextView) itemView.findViewById(R.id.id_new_device_total_devices);
        cardItem = (CardView)itemView.findViewById(R.id.id_new_device_card_layout_item);

        cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onCardItemClickListener(position);
                    }
                }
            }
        });

    }
}
