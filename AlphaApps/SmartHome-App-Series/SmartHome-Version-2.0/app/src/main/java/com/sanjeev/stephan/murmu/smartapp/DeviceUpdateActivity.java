package com.sanjeev.stephan.murmu.smartapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.nio.charset.Charset;


public class DeviceUpdateActivity extends AppCompatActivity {

    int id_to_update = 0,relayNum,relayState = 0;
    private String deviceName, ipAddress, deviceLocation;
    AllDeviceDatabase deviceDb = new AllDeviceDatabase(this);
    private EditText input_deviceName, input_ipAddress, input_deviceLocation;
    private Spinner input_relayNum;
    private String Key_ID = "positionId";
    Debugger debug = new Debugger();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_existing_device);

        input_deviceName = (EditText) findViewById(R.id.id_update_device_name);
        input_ipAddress = (EditText) findViewById(R.id.id_update_device_ip);
        input_deviceLocation = (EditText) findViewById(R.id.id_update_location);
        input_relayNum = (Spinner) findViewById(R.id.id_update_relay_spinner);

        setSpinner();
        /*
         Bundle data gets sent from the MainActivity > adapter.setOnItem_clickListener();

         */

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int value = extras.getInt(Key_ID);
            if (value > 0) {
                Cursor cursor = deviceDb.getItemAtPosition(value);
                id_to_update = value;
                cursor.moveToFirst();

                deviceName = cursor.getString(cursor.getColumnIndex(deviceDb.DB_DEVICE_NAME));
                ipAddress = cursor.getString(cursor.getColumnIndex(deviceDb.DB_DEVICE_IP));
                relayNum = cursor.getInt(cursor.getColumnIndex(deviceDb.DB_RELAY_SWITCH_NUMBER));
                relayState = Integer.parseInt(cursor.getString(cursor.getColumnIndex(deviceDb.DB_RELAY_STATE)));
                deviceLocation = cursor.getString(cursor.getColumnIndex(deviceDb.DB_LOCATION));

                if (!cursor.isClosed()) {
                    cursor.close();
                }

                input_deviceName.setText((CharSequence) deviceName);
                input_ipAddress.setText((CharSequence) ipAddress);
                input_deviceLocation.setText((CharSequence) deviceLocation);
                input_relayNum.setSelection(relayNum);

            }
        }
    }

    public void UpdateDevice(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt(Key_ID);

            if(value > 0)
            {
                deviceName = input_deviceName.getText().toString().trim();
                ipAddress = input_ipAddress.getText().toString().trim();
                deviceLocation= input_deviceLocation.getText().toString().trim();


                if(deviceDb.updateDevice(id_to_update,deviceName,ipAddress,relayNum,relayState,deviceLocation))
                {
                    debug.setToast(this,"--------------------------[ Updated ]-------------------");
                    debug.setToast(this,"ID Updated :" + id_to_update);
                    debug.setToast(this,"Device Name :" + deviceName);
                    debug.setToast(this,"IP Address :" + ipAddress);
                    debug.setToast(this,"Relay Number : " + relayNum);
                    debug.setToast(this,"RelayState :" + relayState);
                    debug.setToast(this,"deviceLocation :" + deviceLocation);

                    go2MainActivity();
                }
                else { debug.setToast(this,"Failed to Update Device!"); }
            }
        }
    }
    public void deleteDevice(View view)
    {

    }

    private void setSpinner()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.relays_list, android.R.layout.simple_list_item_1);
        input_relayNum.setAdapter(adapter);
       // relayNum = input_relayNum.getSelectedItem().toString();
        input_relayNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                relayNum = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void go2MainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
