package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi;

import android.content.Context;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.GlobalVariables;

import java.util.ArrayList;

public class ServerRequest
{
    static String getLink(Context context,int position)
    {
        final ArrayList<DeviceModel> arrayList = new ArrayList<>();
        final DeviceModel device = arrayList.get(position);
        final String ip = device.getDeviceIP();
        final int relay = device.getDeviceRelay();
        final String link = "http://" + ip + "/RELAY_" + relay;  //http://192.168.225.10/RELAY_1
        GlobalVariables.setLink(link); //link scope sets as global
        return link;
    }
    static String getDeviceON_link(Context context,int position)
    {
        String link = getLink(context, position);
        return link + "=ON"; //http://192.168.225.10/RELAY_1=ON
    }
    static String getDeviceOFF_link(Context context,int position)
    {
        String link = getLink(context, position);
        return link + "=OFF"; //http://192.168.225.10/RELAY_1=OFF
    }
    static String request_ON_OFF_Link(Context context,int position,boolean state)
    {
        String send_this_link;
        if(state)
        {  /* if state is true then send below link */
            send_this_link = getDeviceON_link(context, position);
        }
        else
        { /* if state is (false) then send below link*/
            send_this_link = getDeviceOFF_link(context, position);
        }
        return send_this_link;
    }
}
