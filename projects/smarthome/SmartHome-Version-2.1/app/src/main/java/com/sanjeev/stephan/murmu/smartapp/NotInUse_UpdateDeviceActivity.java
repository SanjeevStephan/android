package com.sanjeev.stephan.murmu.smartapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NotInUse_UpdateDeviceActivity extends AppCompatActivity {
/*

    private EditText input_deviceName,input_ipAddress,input_deviceLocation;
    private int deviceId,relayNum,relayState = 0;
    private String deviceName,ipAddress,deviceLocation;
    AllDeviceDatabase deviceDB;
    Debugger debug = new Debugger();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_in_use_activity_update_device);

        deviceDB = new AllDeviceDatabase(this);

        input_deviceName     = (EditText) findViewById(R.id.id_update_device_name);
        input_ipAddress      = (EditText) findViewById(R.id.id_update_device_ip);
        input_deviceLocation = (EditText) findViewById(R.id.id_update_location);

        try
        {
            deviceId       = Integer.parseInt(getIntent().getExtras().getString("deviceId").trim());

        } catch (NumberFormatException e) {
            e.printStackTrace();
            debug.setLog("Position is Empty @UpdateDeviceActivity.java");
        }

        deviceName     = getIntent().getExtras().getString("deviceName");
        ipAddress      = getIntent().getExtras().getString("ipAddress");
        relayNum       = getIntent().getExtras().getInt("relayNum");
        deviceLocation = getIntent().getExtras().getString("deviceLocation");


        debug.setLog(deviceName + "'s device ID :" + deviceId);

        if(deviceName!=null && ipAddress!=null && deviceLocation !=null)
        {
            input_deviceName.setText(deviceName);
            input_ipAddress.setText(ipAddress);
            input_deviceLocation.setText(deviceLocation);
        }
        else
        {
            debug.setLog("Not Data Received @UpdateDeviceActivty.java");
        }

    }

    public void onUpdateBtn_click(View view)
    {
        if(deviceDB.updateDevice(deviceId, deviceName, ipAddress, relayNum, relayState, deviceLocation))
        {
            debug.setToast(this,"[" + deviceName + "] Device Updated Successfully");
            openMainActivity();
        }
        else
        {
            debug.setToast(this,"Failed to Update Device");
        }
    }
    public void onCancelBtn_click(View view)
    {
        debug.setToast(this, "Cancelled");
        openMainActivity();
    }
    void openMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    */
}
