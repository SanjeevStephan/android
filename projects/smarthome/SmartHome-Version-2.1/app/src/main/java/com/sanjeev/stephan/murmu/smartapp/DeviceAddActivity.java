package com.sanjeev.stephan.murmu.smartapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Spinner;
public class DeviceAddActivity extends AppCompatActivity {

    EditText input_device_name,input_ip_address,input_location;
    AllDeviceDatabase deviceDB;
    Spinner spinner;
    Switch input_relay_state;
    int relayState = 0;
    String deviceName,ipAddress,location;
    int relayNum;
    Button btn_save;
    Debugger debug = new Debugger();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_device);

        input_device_name = (EditText) findViewById(R.id.id_update_device_name);
        input_ip_address  = (EditText) findViewById(R.id.id_update_device_ip);
        input_location    = (EditText) findViewById(R.id.id_update_location);
        input_relay_state = (Switch) findViewById(R.id.id_relay_state_switch);
        spinner           = (Spinner) findViewById(R.id.id_update_relay_spinner);
        btn_save          = (Button) findViewById(R.id.id_btn_add_new_device);

        deviceDB = new AllDeviceDatabase(this); //creating an instance of AllDeviceDatabase.

         setSpinner();
    }
    void setSpinner()
    {
       // final String relays[] = {"Select Relay Switch","RELAY 1","RELAY 2","RELAY 3","RELAY 4","RELAY 5","RELAY 6"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, relays);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.relays_list, android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                relayNum = i ; //pretendig it to be a String..

                if(i != 0)
                {
                        setToast(relayNum + "");
                      //  btn_save.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onAddNewDevice_btnClick(View view)
    {

        switch(view.getId())
        {
            case R.id.id_btn_add_new_device:

                deviceName = input_device_name.getText().toString().toLowerCase();
                ipAddress  = input_ip_address.getText().toString().trim();
                location = input_location.getText().toString().trim();
                if(input_relay_state.isChecked())
                { relayState = 1; }
                else
                { relayState = 0; }


               // int num_relay = Integer.parseInt(relayNum);
                if(relayNum != 0)
                {
                    insertDevice(deviceName,ipAddress,relayNum,relayState,location);

                    setToast("----------------------[ Added ]-----------------------------");
                    setToast("Inserting Item Into Database");
                    setToast("Device Name : " + deviceName);
                    setToast("IP : " + ipAddress);
                    setToast("Relay Num :" + relayNum);
                    setToast("Relay State : " + relayState);
                    setToast("Location : " + location );
                }
                else
                {
                    setToast("Please Fill All the Empty Fields");
                }
                break;
            case R.id.id_btn_go_to_main_activity:
                openMainActivity();
                break;
        }
    }

   public void insertDevice(String deviceName,String ipAddress,int relayNum,int relayState,String location)
    {

        if(deviceDB.insertDevice(deviceName,ipAddress,relayNum,relayState,location))
        {
            setToast("Device Data Inserted Successfully");
        }
        else
        {
            setToast("Failed to Insert Data!.");
        }
        openMainActivity();
    }


    void setToast(String text)
    {
        debug.setToast(this,text);

    }
    void openMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
