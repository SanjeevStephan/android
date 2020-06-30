package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi.database;

import android.content.Context;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.GlobalVariables;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi.ActivityJumper;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.tools.ShowMsg;

public class AddDevice
{
    DeviceDatabase deviceDB;
    public AddDevice(Context context)
    {

        final int deviceType = GlobalVariables.getDeviceType();
        final String deviceName = GlobalVariables.getDeviceName();
        final String ipAddress  = GlobalVariables.getDeviceIP();
        final int relayNum   = GlobalVariables.getRelayDevice();
        final int deviceImage = GlobalVariables.getDeviceImage();
        final String location = GlobalVariables.getDeviceLocation();
        deviceDB = new DeviceDatabase(context);
        if(deviceDB.insertDevice(deviceType,deviceName,ipAddress,deviceImage,relayNum,location))
        {
            ShowMsg.setMsgLog("AddDevice","Device Data Inserted Successfully");
        }
        else
        {
            ShowMsg.setMsgLog("AddDevice","Failed to Insert Data!.");
        }
        ActivityJumper.setJumper(context,"WiFiActivity");
    }
}
