package com.sanjeev.stephan.murmu.ctsmartnet.smarthome;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.AddNewRelayDevice;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.DialogLocationList;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.DialogInfo;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.DialogRelayList;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.InputDialog;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceAddActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Display User-Interface for adding device item into the SQLiteDatabase.
 *
 * @author Sanjeev Stephan Murmu
 * @since 19 Oct 2019.
 */
public class AddActivity extends AppCompatActivity implements InterfaceAddActivity, View.OnClickListener {

    private static final String TAG = "AddActivity";
    //classes
    Show show = new Show(TAG);
    //global widget views
    ImageView deviceImageView;
    TextView parentNameTextView;
    TextView devNameTextView, ipTextView, devTypeTextView,parentRelayTextView, relayTextView, locationTextView;
    RelativeLayout parentLayout_deviceLocation, parentLayout_relayNumber, parentLayout_ipAddress, parentLayout_deviceName;
    Button saveButton;
    //variables
    String deviceType;
    int deviceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setReferenceIDs();

        if (getIntent() != null) {
            deviceType = getIntent().getExtras().getString(getString(R.string.key_add_device));
            deviceImage = getIntent().getExtras().getInt(getString(R.string.key_add_device_image));

            if (deviceType != null) { //TODO : changes the AddActivity action-bar TITLE such as 'Add New WiFI Lighting' and so on.
                setTitle(getString(R.string.title_add_new_wifi_device).concat(" ").concat(deviceType));
                devTypeTextView.setText(deviceType);

            }
            if (deviceImage != 0) {  //TODO : changes the device-image according to the device-type.
                deviceImageView.setImageResource(deviceImage);
            }
        }

        setTextView();
        setClickListener();

        Toolbar toolbar = findViewById(R.id.id_toolbar_add_activity);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //TODO : arrow points back to HOME-MainActivity.

    }

    void setReferenceIDs() {

        deviceImageView = findViewById(R.id.id_add_device_image);
        devNameTextView = findViewById(R.id.id_add_device_input_device_name);
        devTypeTextView = findViewById(R.id.id_add_device_input_device_type);
        ipTextView = findViewById(R.id.id_add_device_input_device_ip);
        relayTextView = findViewById(R.id.id_add_device_input_device_relay);
        locationTextView = findViewById(R.id.id_add_device_input_device_location);

        parentNameTextView  = findViewById(R.id.id_inner_text_device_name);
        parentRelayTextView = findViewById(R.id.id_inner_text_device_relay);

        parentLayout_deviceName = findViewById(R.id.id_add_device_device_name_layout);
        parentLayout_relayNumber = findViewById(R.id.id_add_device_device_relay_layout);
        parentLayout_ipAddress = findViewById(R.id.id_add_device_device_ip_layout);
        parentLayout_deviceLocation = findViewById(R.id.id_add_device_device_location_layout);

        saveButton = findViewById(R.id.id_add_new_device_button);
    }

    void setTextView()
    {
      if(deviceType.equals("CCTV"))
      {
          parentNameTextView.setText(getString(R.string.hint_url_attributes));
          parentRelayTextView.setText(getString(R.string.hint_device_port_number));
      }
    }

    /**
     * widgets that are listening for onClick Events.
     */
    void setClickListener() {
        saveButton.setOnClickListener(this);
        parentLayout_deviceName.setOnClickListener(this);
        parentLayout_ipAddress.setOnClickListener(this);
        parentLayout_deviceLocation.setOnClickListener(this);
        parentLayout_relayNumber.setOnClickListener(this);
    }

    /**
     * Method located in {@link InterfaceAddActivity} java class
     * Also used in dialog-fragment {@link DialogRelayList} java class
     * @param position relay number in integer
     */
    @Override
    public void onRelayItemSelectedListener(int position) {
        relayTextView.setText(position + "");
    }

    /**
     * Method Located in {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceAddActivity}
     * Also used in dialog-frgment {@link DialogLocationList} java class
     * @param location location in string value
     * @param position location in terms of its position in integer value
     */
    @Override
    public void onLocationPositionChangeListener(String location, int position) { //TODO : here number '9' is the position number for 'Other Location'
        if (position != 9) {
            locationTextView.setText(location); }
        else { // TODO : when user's clicks 'Other Location' then show an input dialog box for custom-location.
            InputDialog inputLocation = new InputDialog();
            Bundle locationData = new Bundle();
            locationData.putInt(getString(R.string.key_dialog_title), R.string.enter_location);
            locationData.putInt(getString(R.string.key_hint), R.string.edit_title_text_view_device_location);
            locationData.putString(getString(R.string.key_input), ""); //TODO : use empty string if you want empty editText | pass text here if you want some text in input dialog.
            inputLocation.setArguments(locationData);

            if (getFragmentManager() != null)
                inputLocation.show(getSupportFragmentManager(), getString(R.string.key_input));
        }
    }

    /**
     * Method located in {@link InterfaceAddActivity} java class
     * Used in dialog-fragemt {@link InputDialog} java class
     * @param message input-text entered by the user!
     * @param hint tells what text data have been entered by user.
     */
    @Override
    public void onReceivedInputListener(String message, int hint) {
        switch (hint) {
            //TODO : verify that the hint received is 'input_hint_device_nickname'
            case R.string.hint_device_name_wo_colon:
            case R.string.hint_url_attributes_wo_colon:
                devNameTextView.setText(message); //TODO : then only set the textView
                break;
            case R.string.hint_device_port_wo_colon: //TODO : same as above but different case !
                relayTextView.setText(message);
                break;
            case R.string.hint_device_ip_wo_colon: //TODO : same as above but different case !
                ipTextView.setText(message);
                break;
            case R.string.edit_title_text_view_device_location: //TODO : same as above but different case !
                locationTextView.setText(message);
                break;


        }
    }

    /**
     * [onClick()] overrided when class implements View.OnClickListener
     * Class instances used {@link InputDialog} java class
     * Class instances used {@link DialogRelayList} java class
     * Class instances used {@link DialogLocationList} java class
     * @param v
     */
    @Override
    public void onClick(View v) {
        InputDialog inputIp = new InputDialog();
        InputDialog inputName = new InputDialog();

        if (!deviceType.equals("CCTV")) { // TODO : display different input options if the device is 'CCTV' otherwise use normal 'relay' device settings.
            switch (v.getId()) {
                case R.id.id_add_device_device_name_layout:
                    Bundle nameData = new Bundle();
                    nameData.putInt(getString(R.string.key_dialog_title), R.string.enter_device_name);
                    nameData.putInt(getString(R.string.key_hint), R.string.hint_device_name_wo_colon);
                    nameData.putString(getString(R.string.key_input), devNameTextView.getText().toString().trim());
                    inputName.setArguments(nameData);
                    inputName.show(getSupportFragmentManager(), getString(R.string.key_input));
                    break;
                case R.id.id_add_device_device_relay_layout:
                    DialogRelayList relayList = new DialogRelayList();
                    relayList.show(getSupportFragmentManager(), getString(R.string.key_relay_or_port));
                    break;
                case R.id.id_add_device_device_ip_layout:
                    Bundle ipData = new Bundle();
                    ipData.putInt(getString(R.string.key_dialog_title), R.string.enter_ip_address);
                    ipData.putInt(getString(R.string.key_hint), R.string.hint_device_ip_wo_colon);
                    ipData.putString(getString(R.string.key_input), ipTextView.getText().toString().trim());
                    inputIp.setArguments(ipData);
                    inputIp.show(getSupportFragmentManager(), getString(R.string.key_input));
                    break;
                case R.id.id_add_device_device_location_layout:
                    DialogLocationList locationDialog = new DialogLocationList();
                    locationDialog.show(getSupportFragmentManager(), getString(R.string.key_location));
                    break;
                case R.id.id_add_new_device_button:
                    show.setLog("Saved Button Clicked!");
                    verifyTextFieldsBeforSaving(); //TODO : verify all fields before saving to database!
                    break;
            }
        } else { //TODO : execute below code if the device happens to be a 'CCTV' otherwise execute above code.
            switch (v.getId()) {
                case R.id.id_add_device_device_name_layout:
                    Bundle nameData = new Bundle();
                    nameData.putInt(getString(R.string.key_dialog_title), R.string.enter_device_name);
                    nameData.putInt(getString(R.string.key_hint), R.string.hint_url_attributes_wo_colon);
                    nameData.putString(getString(R.string.key_input), devNameTextView.getText().toString().trim());
                    inputName.setArguments(nameData); //TODO : pass this Bundle data to Fragment InputDialog.
                    inputName.show(getSupportFragmentManager(), getString(R.string.key_input));
                    break;
                case R.id.id_add_device_device_relay_layout:
                    Bundle portData = new Bundle();
                    InputDialog inputPort = new InputDialog();
                    portData.putInt(getString(R.string.key_dialog_title), R.string.enter_port_number);
                    portData.putInt(getString(R.string.key_hint), R.string.hint_device_port_wo_colon);
                    portData.putString(getString(R.string.key_input), "");
                    inputPort.setArguments(portData); //TODO : pass this Bundle data to Fragment InputDialog.
                    if (getFragmentManager() != null)
                        inputPort.show(getSupportFragmentManager(), getString(R.string.key_input));
                    break;
                case R.id.id_add_device_device_ip_layout:
                    Bundle ipData = new Bundle();
                    ipData.putInt(getString(R.string.key_dialog_title), R.string.enter_ip_address);
                    ipData.putInt(getString(R.string.key_hint), R.string.hint_device_ip_wo_colon);
                    ipData.putString(getString(R.string.key_input), ipTextView.getText().toString().trim());
                    inputIp.setArguments(ipData); //TODO : pass this Bundle data to Fragment InputDialog.
                    inputIp.show(getSupportFragmentManager(), getString(R.string.key_input));
                    break;
                case R.id.id_add_device_device_location_layout:
                    DialogLocationList locationDialog = new DialogLocationList();
                    locationDialog.show(getSupportFragmentManager(), getString(R.string.key_location));
                    break;
                case R.id.id_add_new_device_button:
                    show.setLog("Saved Button Clicked!");
                    verifyTextFieldsBeforSaving(); //TODO : verify all fields before saving to database!
                    break;
            }
        }
    }

    public void showDialogBox(String text) {
        show.setLog(text);
        DialogInfo dialogInfo = new DialogInfo();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.key_dialog_msg), text);
        dialogInfo.setArguments(bundle);
        dialogInfo.show(getSupportFragmentManager(), getString(R.string.key_dialog_msg));
    }

    /**
     * Checks the Fields for Empty Text
     */
    private void verifyTextFieldsBeforSaving() {


        final String deviceName = devNameTextView.getText().toString().trim();
        final String ipAddress = ipTextView.getText().toString().trim();
        final String deviceType = devTypeTextView.getText().toString().trim();
        final String relayOrPortNum = relayTextView.getText().toString().trim();
        final String deviceLocation = locationTextView.getText().toString().trim();

        if (!TextUtils.isEmpty(deviceName)
                && !TextUtils.isEmpty(ipAddress)
                && !TextUtils.isEmpty(deviceType)
                && !TextUtils.isEmpty(relayOrPortNum))
        { //TODO : execute below code only when all the input fields are not blank " "
            if (!deviceName.equals("Empty") && !ipAddress.equals("Empty") && !relayOrPortNum.equals("Empty")) {
                //TODO : execute below code only when none of the above textfields are not labeled 'EMPTY'.
                show.setLog("--------------VerifyTextFields---------------");
                show.setLog("Name : " + deviceName);
                show.setLog("IP : " + ipAddress);
                show.setLog("Type : " + deviceType);
                show.setLog("Relay : " + relayOrPortNum);
                show.setLog("Location : " + deviceLocation);

                if (saveDevice(deviceName, ipAddress, deviceType, relayOrPortNum, deviceLocation)) {
                    showDialogBox("Device Saved Successfully");
                    setTimer();
                } else {
                    showDialogBox("Failed to Save Device");
                }
            } else {
                showDialogBox("Please Fill the 'Empty' fields!");
            }
        } else {
            showDialogBox("Check NickName|IP Address again!");
        }
    }

    /**
     * Save the Device Information to Database
     * also see {@link AddNewRelayDevice} java class
     * @param nickname       Device's Name
     * @param ip             Device's IP Address such as 192.168.225.10
     * @param type           Device's Type such as Light Bulb,Fan
     * @param relayOrPortNum It can be any number other than Zero(0) upto 16 channel
     * @param location       Location of the Device Item.
     * @return True if save,False is not saved!
     */
    boolean saveDevice(String nickname, String ip, String type, String relayOrPortNum, String location) {
        int relayOrPort = Integer.parseInt(relayOrPortNum);
        AddNewRelayDevice addDevice = new AddNewRelayDevice(this);
        return addDevice.saveDevice(relayOrPort, type, nickname, ip, location);
    }

    /**
     * After Device saved to database,jump to MainActivity within few milliseconds!
     */
    private void setTimer() {
        long delay = 100;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        };

        timer.schedule(task, delay);
    }

}
