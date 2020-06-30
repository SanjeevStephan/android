package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.R;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceViewHolder>
{

    ArrayList<DeviceModel> arrayList;
    DeviceModel device;
    public DeviceAdapter(ArrayList<DeviceModel> arraylist)
    {
        this.arrayList = arraylist;
    }

    public void setDeviceItemClickListener(OnDeviceItemClickListener deviceItemClickListener)
    {
        this.deviceItemClickListener = deviceItemClickListener;
    }
    public OnDeviceItemClickListener deviceItemClickListener;
    public interface OnDeviceItemClickListener
    {
        void onItemClickListener(int position);
        void onStateChangeListener(int position,boolean state);

    }


    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.wifi_device_item_layout, viewGroup, false);
        DeviceViewHolder viewHolder = new DeviceViewHolder(view,deviceItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder viewHolder, int position)
    {
        String link_status ;
        device = arrayList.get(position);
        link_status = "http://" + device.getDeviceIP() + "/"+ device.getDeviceRelay();
        viewHolder.deviceImage.setImageResource(device.getDeviceImage());
        viewHolder.deviceName.setText(device.getDeviceName());
        viewHolder.deviceLocation.setText(device.getDeviceLocation());
        //viewHolder.deviceStatus.setText(link_status);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
