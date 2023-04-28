package com.sanjeev.stephan.murmu.ctnetwork.smarthome;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.tools.ShowMsg;

public class GlobalVariables
{
    static String WhereAmI = "GlobalVariable";

    public static String link;
    /*
       Class :- AddDeviceActivity's Global variables
     */
    private static String deviceName,deviceIP,deviceLocation;
    private static int relayDevice,deviceImage,deviceType;

    public GlobalVariables() {
    }

    public GlobalVariables(String locateMe,int deviceImage, int relayDevice, int deviceType, String deviceName, String deviceIP, String deviceLocation)
    {
        GlobalVariables.deviceImage = deviceImage;
        GlobalVariables.relayDevice  = relayDevice;
        GlobalVariables.deviceType   = deviceType;
        GlobalVariables.deviceName   = deviceName;
        GlobalVariables.deviceIP     = deviceIP;
        GlobalVariables.deviceLocation = deviceLocation;

        ShowMsg.setMsgLog(locateMe,"---------------------------------------------");
        ShowMsg.setMsgLog(locateMe,"Device-Image : " + deviceImage);
        ShowMsg.setMsgLog(locateMe, "Device-Type : " +   deviceType);
        ShowMsg.setMsgLog(locateMe, "Device-Name : "+   deviceName);
        ShowMsg.setMsgLog(locateMe, "Device-IP : "  +   deviceIP);
        ShowMsg.setMsgLog(locateMe, "Relay : " + relayDevice);
        ShowMsg.setMsgLog(locateMe, "Device-Location : " + deviceLocation);
    }

    public static String getLink() {
        return link;
    }

    public static void setLink(String link) {
        GlobalVariables.link = link;
        ShowMsg.setMsgLog(WhereAmI,link);
    }

    public static int getDeviceImage() {
        return deviceImage;
    }

    public static void setDeviceImage(int deviceImage) {
        GlobalVariables.deviceImage = deviceImage;
    }

    public static int getDeviceType() {
        return deviceType;
    }

    public static void setDeviceType(int deviceType) {
        GlobalVariables.deviceType = deviceType;
    }

    public static String getDeviceName() {
        return deviceName;
    }

    public static void setDeviceName(String deviceName) {
        GlobalVariables.deviceName = deviceName;
    }

    public static String getDeviceIP() {
        return deviceIP;
    }

    public static void setDeviceIP(String deviceIP) {
        GlobalVariables.deviceIP = deviceIP;
    }

    public static String getDeviceLocation() {
        return deviceLocation;
    }

    public static void setDeviceLocation(String deviceLocation) {
        GlobalVariables.deviceLocation = deviceLocation;
    }

    public static int getRelayDevice() {
        return relayDevice;
    }

    public static void setRelayDevice(int relayDevice) {
        GlobalVariables.relayDevice = relayDevice;
    }
}
