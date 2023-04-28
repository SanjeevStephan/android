package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory;

public class ModelNewDeviceCategory
{
    String deviceTitle,totalDevices;
    int deviceImage;

    public ModelNewDeviceCategory()
    { }

    public ModelNewDeviceCategory(String deviceTitle, String totalDevices, int deviceImage)
    {
        this.deviceTitle = deviceTitle;
        this.totalDevices = totalDevices;
        this.deviceImage = deviceImage;
    }

    public String getDeviceTitle()
    {
        return deviceTitle;
    }

    public String getTotalDevices()
    {
        return totalDevices;
    }

    public int getDeviceImage()
    {
        return deviceImage;
    }
}
