package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory;

import android.content.Context;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

import java.util.ArrayList;

public class MyNewDeviceArrayList
{
    public static void setNewDeviceArrayList(Context c,ArrayList<ModelNewDeviceCategory> arrayList)
    {


        arrayList.add(new ModelNewDeviceCategory(c.getString(R.string.title_lighting),"5 devices",R.drawable.device_icon_light_bulb_on_anime));
        arrayList.add(new ModelNewDeviceCategory(c.getString(R.string.title_fan),"3 devices", R.drawable.device_icon_fan_table_off));
        arrayList.add(new ModelNewDeviceCategory(c.getString(R.string.title_smart_plug),"2 devices", R.drawable.device_icon_smart_plug));
        arrayList.add(new ModelNewDeviceCategory(c.getString(R.string.title_sensor),"4 devices", R.drawable.device_icon_light_bulb_on_anime));
        arrayList.add(new ModelNewDeviceCategory(c.getString(R.string.title_cctv),"5 devices", R.drawable.device_icon_cctv_camera));
        arrayList.add(new ModelNewDeviceCategory(c.getString(R.string.title_tv),"5 devices", R.drawable.device_icon_tv));
    }
}
