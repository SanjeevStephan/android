package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.ArrayAdapter;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.GlobalVariables;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.R;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi.database.AddDevice;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.tools.ShowMsg;
import static com.sanjeev.stephan.murmu.ctnetwork.smarthome.GlobalArray.deviceON_ImgArray;

public class AddDeviceActivity extends AppCompatActivity{

    /*
      Below Widget and Variables are declared private
      and hence they cannot be accessed outside the 'AddDeviceActivity' class.
     */
    private Spinner input_deviceList,input_relayList,input_roomList;
    private TextInputEditText input_deviceName,input_deviceIp,input_OtherLocation;
    private ImageView deviceImage;
    private Button btnSave;
    private String deviceName,deviceIP,deviceLocation;
    private int relayDevice,imageName,deviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.title_activity_add_new_device);


        setReferenceIDs();
        setFab();
        setDeviceList();
        setRelayList();
        setRoomList();
    }
    private void setReferenceIDs()
    {
        /*
          Attach the XML Layout Code with the Java Code by
          Referencing it with IDs of the Widget Component in the res\layout\file.xml
         */
        deviceImage         = (ImageView) findViewById(R.id.id_add_device_image);
        input_deviceName    = (TextInputEditText) findViewById(R.id.id_input_add_device_name);
        input_deviceIp      = (TextInputEditText) findViewById(R.id.id_input_add_device_ip);
        input_OtherLocation = (TextInputEditText) findViewById(R.id.id_input_add_location);
        btnSave             = (Button) findViewById(R.id.id_add_new_device_button);
    }
    private void setFab()
    {

        FloatingActionButton fab = findViewById(R.id.id_add_back_fab_button); //Referencing the Widget IDs
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                ActivityJumper.setJumper(AddDeviceActivity.this,"WiFiActivity");
            }
        });

    }
    private void setDeviceList()
    {
        /*
          Widget          : Spinner
          ID              : id_select_new_device_spinner
          String Array    : device_lists.
         */
        input_deviceList = (Spinner) findViewById(R.id.id_select_new_device_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.device_lists, android.R.layout.simple_list_item_1);
        input_deviceList.setAdapter(adapter);
        input_deviceList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                deviceType = i; //assign the position to deviceList(Spinner) to deviceType(int);

                imageName = deviceON_ImgArray[i]; // get the arraylist item from 'deviceON_ImgArray'(String[]) based on the position of the deviceType(int)
                deviceImage.setImageResource(deviceON_ImgArray[i]); //setting up the deviceImage(ImageView) with the image from the the above arrayList 'deviceON_ImgArray'

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private void setRelayList()
    {
        /*
          Widget       : Spinner
          ID           : id_add_new_relay
          String Array : relay_list
         */
        input_relayList = (Spinner) findViewById(R.id.id_add_new_relay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.relay_list, android.R.layout.simple_list_item_1);
        input_relayList.setAdapter(adapter);
        input_relayList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // here the default position to the input_relayList(spinner) is '0'
                relayDevice = i; //assign the position of RelayList(Spinner) to relayDevice(int)
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void setRoomList()
    {
        /*
          Widget       : Spinner.
          ID           : id_select_new_device_spinner.
          String Array : room_list.
         */
        input_roomList = (Spinner) findViewById(R.id.id_add_new_room);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.room_list, android.R.layout.simple_list_item_1);
        input_roomList.setAdapter(adapter);
        input_roomList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deviceLocation = adapterView.getSelectedItem().toString();

                if(deviceLocation == "Other Location")
                {
                //    other_location.setVisibility(View.VISIBLE);

                }
                else
                {
                  //  other_location.setVisibility(View.INVISIBLE);
                }
                input_OtherLocation.setText(deviceLocation);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void getInputData(View view)
    {

        /*
           Get Data from the EditText and assign it to the String Variables
         */

        deviceName     = input_deviceName.getText().toString().trim();
        deviceIP       = input_deviceIp.getText().toString().trim();
        deviceLocation = input_OtherLocation.getText().toString().trim();

        /* static Global Variables
        *  All the Strings are passed to the GlobalVariables(class) and
        *  hence all the Strings can be accessed from anywhere inside the project
        */

        GlobalVariables global = new GlobalVariables("AddDeviceActivity",
                imageName,
                relayDevice,
                deviceType,
                deviceName,
                deviceIP,
                deviceLocation );

        if(input_deviceName.getText() != null && input_deviceIp.getText() != null)
        {
            AddDevice addDevice = new AddDevice(this);

        }
        else
        {
            ShowMsg.setToast(this,"Please Fill All the Fields");
        }

    }

}
