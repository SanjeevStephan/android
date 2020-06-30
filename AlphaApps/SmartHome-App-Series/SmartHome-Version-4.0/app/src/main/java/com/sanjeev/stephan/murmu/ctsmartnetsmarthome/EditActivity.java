package com.sanjeev.stephan.murmu.ctsmartnet.smarthome;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.CCTVDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.FanDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.LightBulbDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.SocketDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceAddActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.DialogLocationList;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.DialogRelayList;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.InputDialog;

/**
 * Provides the User-Interface for editing existing wifi devices
 * @author Sanjeev Stephan Murmu
 * @since 20th Oct 2019
 *
 */
public class EditActivity extends AppCompatActivity implements View.OnClickListener, InterfaceAddActivity {

    private static final String TAG = "EditActivity";
    //Classes
    Show s = new Show(TAG);
    private Bundle bundle;
    //Variables
    private int devIndex = -1;
    private int devRelayOrPort = -1;
    private String devName = " ";
    private String devIp = " ";
    private String devLocation = " ";
    private String devType = " ";
    private final String typeLighting = "Lighting";
    private final String typeFan = "Fan";
    private final String typePlug = "SmartPlug";
    private final String typeCCTV = "CCTV";
    private int container = R.id.id_fragment_container_edit_activity;
    // Widgets
    private TextView titleNameView,titleTypeView,titleRelayView,titleLocationView;
    private TextView nameView,typeView,ipView,relayView,locationView;
    private RelativeLayout parentLayout_nameView,parentLayout_typeView,parentLayout_ipView,parentLayout_relayView,parentLayout_locationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s.setMethod("onCreate()");
        setContentView(R.layout.activity_edit);
        s.setContext(getApplicationContext());

        //gets intents from another activity and assign it to a string by passing the intent key
        devIndex       = getIntent().getExtras().getInt(getString(R.string.key_device_index));
        devRelayOrPort = getIntent().getExtras().getInt(getString(R.string.key_relay_or_port));
        devName        = getIntent().getExtras().getString(getString(R.string.key_device_name));
        devIp          = getIntent().getExtras().getString(getString(R.string.key_ip));
        devType        = getIntent().getExtras().getString(getString(R.string.key_device_type));
        devLocation    = getIntent().getExtras().getString(getString(R.string.key_location));

        setReferenceIDs();
        setTextViews();
        setClickListener();

        Toolbar toolbar = findViewById(R.id.id_edit_activity_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //back arrow points to main activity


    }
    private void setReferenceIDs()
    {
        nameView = findViewById(R.id.id_edit_activity_device_name);
        typeView = findViewById(R.id.id_edit_activity_device_type);
        ipView = findViewById(R.id.id_edit_activity_device_ip);
        relayView = findViewById(R.id.id_edit_activity_device_relay);
        locationView = findViewById(R.id.id_edit_activity_device_location);

        titleNameView  = findViewById(R.id.id_edit_activity_device_name_parent_title);
        titleRelayView = findViewById(R.id.id_edit_activity_device_relay_parent_title);
        titleTypeView  = findViewById(R.id.id_edit_activity_device_type_parent_title);
        titleLocationView = findViewById(R.id.id_edit_activity_device_location_parent_title);

        parentLayout_nameView = findViewById(R.id.id_edit_activity_device_name_layout);
        parentLayout_typeView = findViewById(R.id.id_edit_activity_device_type_layout);
        parentLayout_ipView = findViewById(R.id.id_edit_activity_device_ip_layout);
        parentLayout_relayView = findViewById(R.id.id_edit_activity_device_relay_layout);
        parentLayout_locationView = findViewById(R.id.id_edit_activity_device_location_layout);
    }
    private void setTextViews()
    {
        if(!devType.equals(typeCCTV))
        { //TODO : if the deviceType is not 'CCTV' then change the textViews to meet wifi relay device settings!
            setTitle("Edit " + devName);
            titleNameView.setText(getString(R.string.edit_title_text_view_device_name));
            titleRelayView.setText(getString(R.string.edit_title_text_view_device_relay));
            titleTypeView.setText(getString(R.string.edit_title_text_view_device_type));
            titleLocationView.setText(getString(R.string.edit_title_text_view_device_location));
        }
        else
        { //TODO : if the deviceType is 'CCTV' then change the textViews to meet cctv settings!
            setTitle("Edit " + devType);
            titleNameView.setText(getString(R.string.edit_title_text_view_ip_attribute));
            titleRelayView.setText(getString(R.string.edit_title_text_view_port_number));
            titleTypeView.setText(getString(R.string.edit_title_text_view_camera_type));
            titleLocationView.setText(getString(R.string.edit_title_text_view_camera_location));
        }

        //sets the textview by string assigned using intent extras()
        nameView.setText(devName);
        typeView.setText(devType);
        ipView.setText(devIp);
        relayView.setText(devRelayOrPort +""); //concat number with string | textview doesn't display integers
        locationView.setText(devLocation);
    }
    //below component widgets will be listening for onclick events
    private void setClickListener()
    {
        parentLayout_nameView.setOnClickListener(this);
        parentLayout_typeView.setOnClickListener(this);
        parentLayout_ipView.setOnClickListener(this);
        parentLayout_relayView.setOnClickListener(this);
        parentLayout_locationView.setOnClickListener(this);
    }

    /**
     * Action to be taken when specific widgets gets clicked
     * @param v provides widgets IDs.
     */
    @Override
    public void onClick(View v)
    {
        s.setMethod("onClick()");

        if(!devType.equals(typeCCTV))
        {   //TODO : if devType is not 'CCTV' then execute below code.
            switch(v.getId())
            {
                case R.id.id_edit_activity_device_name_layout :
                    s.setLog("[" + devIndex + "] DeviceName : " + devName);
                    Bundle nameData = new Bundle(); //use Bundle to send data to fragment
                    InputDialog inputName = new InputDialog(); //initialize an input dialog
                    nameData.putInt(getString(R.string.key_dialog_title), R.string.enter_device_name);
                    nameData.putInt(getString(R.string.key_hint),R.string.hint_device_name_wo_colon); //dont forget to provide the hint for input dialog
                    nameData.putString(getString(R.string.key_input),devName);
                    inputName.setArguments(nameData);//sending bundle as arguments
                    if(getFragmentManager() !=null)
                        inputName.show(getSupportFragmentManager(),getString(R.string.key_input)); //show input dialog
                    break;
                case R.id.id_edit_activity_device_type_layout :
                    s.setLog("User is not allowed to update the [Device Type]");
                    break;
                case R.id.id_edit_activity_device_ip_layout :
                    s.setLog("[" + devIndex + "] Device Type : " + devIp);
                    Bundle ipData = new Bundle();
                    InputDialog inputIp = new InputDialog();
                    ipData.putInt(getString(R.string.key_dialog_title),R.string.enter_ip_address);
                    ipData.putInt(getString(R.string.key_hint),R.string.hint_device_ip_wo_colon);
                    ipData.putString(getString(R.string.key_input),devIp);
                    inputIp.setArguments(ipData);
                    if(getFragmentManager() !=null)
                        inputIp.show(getSupportFragmentManager(),getString(R.string.key_input));
                    break;
                case R.id.id_edit_activity_device_relay_layout :
                    s.setLog("[" + devIndex + "] Device Relay : " + devRelayOrPort);
                    DialogRelayList relayList = new DialogRelayList(); //initialize dialog with list of relay numbers
                    if(getFragmentManager() !=null)
                        relayList.show(getSupportFragmentManager(),getString(R.string.key_relay_or_port));
                    break;
                case R.id.id_edit_activity_device_location_layout :
                    s.setLog("[" + devIndex + "] Device Location : " + devLocation);
                    DialogLocationList locationDialog = new DialogLocationList();
                    if(getFragmentManager() !=null)
                        locationDialog.show(getSupportFragmentManager(),getString(R.string.key_location));
                    break;
            }
        }
        else
        {   //TODO : if the devType is 'CCTV' then execute below statement!
            switch(v.getId())
            {
                case R.id.id_edit_activity_device_name_layout :
                    s.setLog("[" + devIndex + "] DeviceName : " + devName);
                    Bundle nameData = new Bundle();
                    InputDialog inputName = new InputDialog();
                    nameData.putInt(getString(R.string.key_dialog_title), R.string.enter_additional_attributes);
                    nameData.putInt(getString(R.string.key_hint),R.string.hint_device_ip_attribute_wo_colon);
                    nameData.putString(getString(R.string.key_input),devName);
                    inputName.setArguments(nameData);
                    if(getFragmentManager() !=null)
                        inputName.show(getSupportFragmentManager(),getString(R.string.key_input));
                    break;
                case R.id.id_edit_activity_device_type_layout :
                    s.setLog("User is not allowed to update the [Device Type]");
                    break;
                case R.id.id_edit_activity_device_ip_layout :
                    s.setLog("[" + devIndex + "] Device Type : " + devIp);
                    Bundle ipData = new Bundle();
                    InputDialog inputIp = new InputDialog();
                    ipData.putInt(getString(R.string.key_dialog_title),R.string.enter_ip_address);
                    ipData.putInt(getString(R.string.key_hint),R.string.hint_device_ip_wo_colon);
                    ipData.putString(getString(R.string.key_input),devIp);
                    inputIp.setArguments(ipData);
                    if(getFragmentManager() !=null)
                        inputIp.show(getSupportFragmentManager(),getString(R.string.key_input));
                    break;
                case R.id.id_edit_activity_device_relay_layout :
                    s.setLog("[" + devIndex + "] Device Relay : " + devRelayOrPort);
                    Bundle portData = new Bundle();
                    InputDialog inputPort = new InputDialog();
                    portData.putInt(getString(R.string.key_dialog_title),R.string.enter_port_number);
                    portData.putInt(getString(R.string.key_hint),R.string.hint_device_port_wo_colon);
                    portData.putString(getString(R.string.key_input),devRelayOrPort+"");
                    inputPort.setArguments(portData);
                    if(getFragmentManager() !=null)
                        inputPort.show(getSupportFragmentManager(),getString(R.string.key_input));
                    break;
                case R.id.id_edit_activity_device_location_layout :
                    s.setLog("[" + devIndex + "] Device Location : " + devLocation);
                    DialogLocationList locationDialog = new DialogLocationList();
                    if(getFragmentManager() !=null)
                        locationDialog.show(getSupportFragmentManager(),getString(R.string.key_location));
                    break;
            }
        }

    }

    /**
     * Method located in {@link InterfaceAddActivity} java class
     * Also used in dialog-fragment {@link DialogRelayList} java class
     * @param position relay number in integer
     */
    @Override
    public void onRelayItemSelectedListener(int position) {
        this.devRelayOrPort = position; //TODO : update the devRelayOrPort [ DEVICE_RELAY ]
        s.setMethod("onRelayItemSelectedListener()");
        s.setLog("RELAY : " + position);
        relayView.setText(position+""); //display the relay number on the screen using textViews
        updateDevice(); //also update the database at the same time!
        toast("Relay");
    }

    /**
     * Method Located in {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceAddActivity}
     * Also used in dialog-frgment {@link DialogLocationList}
     * @param Location location in string value
     * @param position location in terms of its position in integer value
     */

    @Override
    public void onLocationPositionChangeListener(String Location,int position) {
        this.devLocation = Location; //TODO : update the devLocation [ DEVICE_LOCATION ]
        s.setLog("Location : " + Location +"is @position ::" + position);
        s.setMethod("onLocationPositionChangeListener()");

        if(position != 9)  //Here at position '9' holds the value of 'Other Location'
        {   //TODO : if the location selected is not 'Other Location' then execute below.
            s.setLog("LOCATION : " + devLocation);
            locationView.setText(devLocation);
            updateDevice();
            toast("Location");
        }
        else
        {   //TODO : if the location selected is 'Other Location' then
            //TODO : display an input dialog for custom location.
            InputDialog inputLocation = new InputDialog();
            Bundle locationData = new Bundle();
            locationData.putInt(getString(R.string.key_dialog_title), R.string.enter_location);
            locationData.putInt(getString(R.string.key_hint),R.string.edit_title_text_view_device_location);
            locationData.putString(getString(R.string.key_input),""); //TODO : remove the devLocation if you want empty editText
            inputLocation.setArguments(locationData);

            if(getFragmentManager() !=null)
            { inputLocation.show(getSupportFragmentManager(),getString(R.string.key_input)); }
        }
    }


    @Override
    public void onReceivedInputListener(String message, int hint) {
        s.setMethod("onReceivedInputListener()");
        switch(hint)
        {
            //TODO : verify that the hint received is 'input_hint_device_nickname'
            case R.string.hint_device_name_wo_colon:
            case R.string.hint_device_ip_attribute_wo_colon:
                //TODO : executes the below code when both the above case are matched!.
                this.devName = message; //TODO : update the devName [ DEVICE_NAME ]
                s.setLog("DEVICE_NAME : " + devName);
                 nameView.setText(devName); //TODO : then only set the textView
                setTitle("Edit " + devName);
                updateDevice();
                toast("Device Name");
                break;
            case R.string.hint_device_ip_wo_colon: //TODO : same as above!
                this.devIp = message;  //TODO : update the devIP [ IP_ADDRESS ]
                s.setLog("DEVICE_IP : " + devIp);
                ipView.setText(devIp);
                updateDevice();
                toast("IP Address");
                break;
            case R.string.hint_device_port_wo_colon:
                this.devRelayOrPort = Integer.parseInt(message.trim()); //TODO : update the relay|port
                //TODO : make sure to convert the string in Integer.
                s.setLog("PORT : " + devRelayOrPort);
                relayView.setText(devRelayOrPort+""); //textview don't allow intergers to be displayed alone.concat string.
                updateDevice();
                toast("Port");
                break;
            case R.string.edit_title_text_view_device_location:
                this.devLocation = message; //TODO : update the devLocation [ DEVICE_LOCATION ]
                s.setMethod("onLocationSelectedListener()");
                s.setLog("LOCATION : " + devLocation);
                locationView.setText(devLocation);
                updateDevice();
                toast("Location");
                break;

        }
    }

    /**
     * Update the database
     *
     */
    private void  updateDevice()
    {
        final String deviceName     = nameView.getText().toString().trim();
        final String ipAddress      = ipView.getText().toString().trim();
        final String deviceType     = typeView.getText().toString().trim();
        final int relayOrPortNum    = Integer.parseInt(relayView.getText().toString().trim());
        final String deviceLocation = locationView.getText().toString().trim();

        s.setLog("--------------Updating Device---------------");
        s.setLog("Index : " + devIndex);

    if(devIndex != -1) //no database @position '-1'
    {
        s.setLog("Name : " + deviceName);
        s.setLog("IP : " + ipAddress);
        s.setLog("Type : " + deviceType);
        s.setLog("Relay : " + relayOrPortNum);
        s.setLog("Location : " + deviceLocation);

        //TODO : use separate SQLiteDatabase for different Device-Types!.
        switch(deviceType)
        {
            case typeLighting:
                LightBulbDatabase lightDb = new LightBulbDatabase(this);
                if(lightDb.updateDevice(devIndex,deviceType,deviceName,ipAddress,relayOrPortNum,deviceLocation))
                {showMsg(typeLighting,deviceName);}
                break;
            case typeFan:
                FanDatabase fanDb = new FanDatabase(this);
                if(fanDb.updateDevice(devIndex,deviceType,deviceName,ipAddress,relayOrPortNum,deviceLocation))
                {showMsg(typeFan,deviceName);}
                break;
            case typePlug:
                SocketDatabase socketDb = new SocketDatabase(this);
                if(socketDb.updateDevice(devIndex,deviceType,deviceName,ipAddress,relayOrPortNum,deviceLocation))
                {showMsg(typePlug,deviceName);}
                break;
            case typeCCTV:
                    devIndex = devIndex + 1; //TODO : add one to the device Index when zero otherwise it'll not update the first cctv camera item!
                    s.setLog("Device Index after adding 1 : " + devIndex);
                    CCTVDatabase cctDb = new CCTVDatabase(this);
                    if(cctDb.updateDevice(devIndex,deviceType,deviceName,ipAddress,relayOrPortNum,deviceLocation))
                    {showMsg(typeCCTV,devLocation);}
                break;

        }
    } }
    public void showMsg(String type,String name)
    {
        s.setLog(type + " Successfully Updated " + name);
    }
    public void toast(String dataKey)
    {
        s.setToast(dataKey + " successfully updated");
    }

}
