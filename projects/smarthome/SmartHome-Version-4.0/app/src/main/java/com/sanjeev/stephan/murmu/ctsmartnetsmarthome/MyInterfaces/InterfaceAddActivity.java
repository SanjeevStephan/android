package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces;

public interface InterfaceAddActivity
{
    void onRelayItemSelectedListener(int position);
    void onLocationPositionChangeListener(String location,int position);
    void onReceivedInputListener(String message,int hint);
}
