package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice;

public class ModelSampleDevice
{
    private String deviceName,deviceIp,deviceType,deviceLocation,deviceStatus,statusDataTime;
    private int relayNum,deviceImage;
    private int index;

    public ModelSampleDevice() {
    }

    public ModelSampleDevice(String deviceName, String deviceIp, String deviceType, String deviceLocation, String deviceStatus, String statusDataTime, int relayNum, int deviceImage) {
        this.deviceName = deviceName;
        this.deviceIp = deviceIp;
        this.deviceType = deviceType;
        this.deviceLocation = deviceLocation;
        this.deviceStatus = deviceStatus;
        this.statusDataTime = statusDataTime;
        this.relayNum = relayNum;
        this.deviceImage = deviceImage;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public String getStatusDataTime() {
        return statusDataTime;
    }

    public int getRelayNum() {
        return relayNum;
    }

    public int getDeviceImage() { return deviceImage; }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setStatusDataTime(String statusDataTime) {
        this.statusDataTime = statusDataTime;
    }

    public void setRelayNum(int relayNum) {
        this.relayNum = relayNum;
    }

    public void setDeviceImage(int deviceImage) {
        this.deviceImage = deviceImage;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
