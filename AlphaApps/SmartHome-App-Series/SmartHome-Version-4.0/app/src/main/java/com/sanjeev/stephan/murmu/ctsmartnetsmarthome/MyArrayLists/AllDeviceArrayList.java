package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyArrayLists;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice.ModelSampleDevice;

import java.util.ArrayList;

/**
 * keep me safe for testing purpose later on.!
 * also see.{@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.LightBulbFragment}
 * also see.{@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.FanFragment}
 * also see.{@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.PlugSocketFragment}
 */
public class AllDeviceArrayList
{
    public static void setLightBulb_ArrayList(ArrayList<ModelSampleDevice> sampleDeviceList)
    {
        sampleDeviceList.add(new ModelSampleDevice("Light Bulb", "192.168.225.12", "Bulb", "Computer Room", "Currently Device is OFF", "10:05 AM", 3, R.drawable.device_icon_light_bulb_off));
        sampleDeviceList.add(new ModelSampleDevice("Light Bulb 2", "192.168.225.12", "Bulb", "Computer Room", "Currently Device is ON", "12:05 AM", 1, R.drawable.device_icon_light_bulb_off));
        sampleDeviceList.add(new ModelSampleDevice("Bathroom", "192.168.225.10", "Bulb", "Computer Room", "Currently Device is ON", "12:05 AM", 4, R.drawable.device_icon_light_bulb_off));
        sampleDeviceList.add(new ModelSampleDevice("Mummy's Room", "192.168.225.10", "Bulb", "Computer Room", "Currently Device is ON", "12:05 AM", 2, R.drawable.device_icon_light_bulb_off));
    }
    public static void setFan_ArrayList(ArrayList<ModelSampleDevice> sampleDeviceList)
    {
        sampleDeviceList.add(new ModelSampleDevice("Desktop Fan", "192.168.225.10", "Fan", "Computer Room", "Currently Device is OFF", "10:05 AM", 1, R.drawable.device_icon_fan_table_off));
    }
    public static void setSocket_ArrayList(ArrayList<ModelSampleDevice> sampleDeviceList)
    {
        sampleDeviceList.add(new ModelSampleDevice("Monitor Switch", "192.168.225.12", "Bulb", "Computer Room", "Currently Device is ON", "12:05 AM", 4, R.drawable.device_icon_switch_off));
        sampleDeviceList.add(new ModelSampleDevice("Desktop Switch","192.168.225.10","Bulb","Computer Room","Currently Device is ON","12:05 AM",3,R.drawable.device_icon_switch_off));

    }
}
