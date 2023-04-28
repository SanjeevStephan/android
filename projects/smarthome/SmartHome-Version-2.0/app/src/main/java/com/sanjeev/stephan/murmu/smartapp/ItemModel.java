package com.sanjeev.stephan.murmu.smartapp;

public class ItemModel
{
    String deviceName,ipAddress,relay_num,location;
    public ItemModel(String deviceName,String ipAddress,String relay_num,String location)
    {
        setDeviceName(deviceName);
        setIpAddress(ipAddress);
        setRelay_num(relay_num);
        setLocation(location);
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        String ip = "IP :" + ipAddress;
        this.ipAddress = ip;
    }

    public String getRelay_num() {
        return relay_num;
    }

    public void setRelay_num(String relay_num) {
        this.relay_num = "Relay : " + relay_num;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = "Location : " + location;
    }
}
