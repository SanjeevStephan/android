package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi;

public class DeviceModel
{
    public int deviceImage,deviceRelay;
    public String deviceName,deviceIP,deviceLocation;

    public DeviceModel(int deviceImage, String deviceName, String deviceIP, int deviceRelay, String deviceLocation) {
        this.deviceImage = deviceImage;
        this.deviceName = deviceName;
        this.deviceIP = deviceIP;
        this.deviceRelay = deviceRelay;
        this.deviceLocation = deviceLocation;
    }

    public int getDeviceImage() {
        return deviceImage;
    }

    public void setDeviceImage(int deviceImage) {
        this.deviceImage = deviceImage;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public int getDeviceRelay() {
        return deviceRelay;
    }

    public void setDeviceRelay(int deviceRelay) {
        this.deviceRelay = deviceRelay;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }
}
