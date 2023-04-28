package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFirebase;

public class FirebaseDeviceModel
{
    private String deviceName,deviceIp,deviceType,deviceLocation,deviceStatus,statusDataTime;
    private int relayNum,deviceImage,deviceState;
    private int index;

    public FirebaseDeviceModel() {
    }

    public FirebaseDeviceModel(String deviceName, String deviceIp, int relayNum) {
        this.deviceName = deviceName;
        this.deviceIp = deviceIp;
        this.relayNum = relayNum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public int getRelayNum() {
        return relayNum;
    }

    public void setRelayNum(int relayNum) {
        this.relayNum = relayNum;
    }

    public int getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(int deviceState) {
        this.deviceState = deviceState;
    }
}
