package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.R;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi.database.DeviceDatabase;

public class DeviceViewHolder extends RecyclerView.ViewHolder {

    public ImageView deviceImage;
    public TextView deviceName,deviceLocation,deviceStatus;
    public Switch deviceState;
    public DeviceViewHolder(@NonNull View itemView, final DeviceAdapter.OnDeviceItemClickListener deviceListener)
    {
        super(itemView);

        deviceImage = itemView.findViewById(R.id.id_device_model_image);
        deviceName = itemView.findViewById(R.id.id_device_model_name);
        deviceLocation = itemView.findViewById(R.id.id_device_model_location);
        deviceStatus = itemView.findViewById(R.id.id_device_model_status);
        deviceState = itemView.findViewById(R.id.id_device_model_state);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(deviceListener != null)
                {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) //NO_POSITION means (-1)
                    {
                        deviceListener.onItemClickListener(position);
                    }
                }
            }
        });

        deviceState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(deviceListener != null)
                {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION)
                    {
                        deviceListener.onStateChangeListener(position,b);
                    }
                }
            }
        });

    }


}
