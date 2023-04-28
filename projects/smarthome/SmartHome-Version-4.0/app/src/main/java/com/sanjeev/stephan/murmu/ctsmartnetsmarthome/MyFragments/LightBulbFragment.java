package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Jump;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice.AdapterSampleDevice;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice.ModelSampleDevice;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.LightBulbDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightBulbFragment extends Fragment {

    //Constant
    private static final String TAG = "LightBulbFragment";
    //Classes
    private Cursor cursor;
    private LightBulbDatabase deviceDb;
    private SQLiteDatabase sqLiteDatabase;
    private AdapterSampleDevice adapter;
    private Jump jump = new Jump(getFragmentManager());
    private Show s = new Show(TAG); //TODO : should pass class name as the parameter for tracing bug
    //Data Model and ArrayList
    private ArrayList<ModelSampleDevice> allDeviceList = new ArrayList<>();
    private ModelSampleDevice model = new ModelSampleDevice();
    //Global Widget Views
    private TextView titleView, noDeviceTextView; //global TextViews
    private View view, dividerView;
    private RelativeLayout parentLayout_sampleDeviceTitle;
    //Variables
    private int NO_DEVICE_POSITION = 0;
    private int deviceStatus = R.string.currently_off, //TODO : update the device status
            deviceStatusTime = R.string.am_12;         //TODO : update the time when device's state changes.
    private int totalDevice = R.dimen.no_device_value,
            fragmentContainer = R.layout.sample_fragment_device,   //TODO : change the container layout
            fragmentTitle = R.string.title_light_bulb,             //TODO : change the fragment title
            deviceImage = R.drawable.device_icon_light_bulb_off,   //TODO : change the default device icon
            deviceON_img = R.drawable.device_icon_light_bulb_on,   //TODO : change the device ON Image
            deviceOFF_img = R.drawable.device_icon_light_bulb_off; //TODO : change the device OFF Image

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(fragmentContainer, container, false);

        setRetainInstance(true); //TODO : use this code to avoid fragment from refreshing!
        setReferenceIDs();
        setupRecyclerView();
        setDisplayData();
        setTitle(fragmentTitle);  //TODO : can change the fragment title | Only use String Resource
        //  AllDeviceArrayList.setLightBulb_ArrayList(allDeviceList);  //TODO : remove comment when not using database!. Just to check if recycler-view works

        return view;
    }

    /**
     * Converting the XML widgets into Java Widget code by referencing through thier IDs.
     */
    private void setReferenceIDs() {
        titleView = view.findViewById(R.id.id_sample_device_title);
        parentLayout_sampleDeviceTitle = view.findViewById(R.id.id_sample_device_title_layout);
        noDeviceTextView = view.findViewById(R.id.id_sample_device_no_device_text);
        dividerView = view.findViewById(R.id.id_sample_device_divider);
    }

    private void setTitle(int title) {
        titleView.setText(getString(title).concat("(").concat(totalDevice + ")"));
    }

    /**
     * Fetch Cursor location of all the data from {@link LightBulbDatabase } database.
     * And Assign those data to the String Variable for accessing later.
     */
    private void setDisplayData() {
        deviceDb = new LightBulbDatabase(view.getContext());
        sqLiteDatabase = deviceDb.getReadableDatabase();       //Read SQLiteDatabase from 'deviceDB'
        cursor = deviceDb.getAllData(sqLiteDatabase); //ping-point the location of the data from 'deviceDb'
        totalDevice = deviceDb.getCountRows();

        s.setLog("Displaying Data From Database");
        s.setLog("------------------------[ LightBulb-Fragment() ]---------------------------------");
        s.setLog("[Index]  Device-Type | Device-Name |  IP  | Location");

        //TODO :  if cursor is not null and cursor is greater than zero then execute below code
        if (cursor != null && cursor.getCount() > NO_DEVICE_POSITION) {
            //TODO :  move first in the database row and start counting from there!
            cursor.moveToFirst();
            //TODO : first get data from database and assign it to final String variables.
            do {

                final int itemIndex = cursor.getInt(cursor.getColumnIndex(LightBulbDatabase.DB_COLUMN_ID));
                final int relayNum = cursor.getInt(cursor.getColumnIndex(LightBulbDatabase.DB_RELAY_NUM));
                final String deviceTypeName = cursor.getString(cursor.getColumnIndex(LightBulbDatabase.DB_DEVICE_TYPE_NAME));
                final String deviceName = cursor.getString(cursor.getColumnIndex(LightBulbDatabase.DB_DEVICE_NICKNAME));
                final String deviceIp = cursor.getString(cursor.getColumnIndex(LightBulbDatabase.DB_DEVICE_IP));
                final String deviceLocation = cursor.getString(cursor.getColumnIndex(LightBulbDatabase.DB_DEVICE_LOCATION));

                s.setLog("[" + itemIndex + "]      " + deviceTypeName + " | " + deviceName + " | " + deviceIp + " | " + deviceLocation);
                setDataModel(itemIndex, relayNum, deviceIp, deviceName, deviceTypeName, deviceLocation);
            }

            // then check it there is next row available which will return either True|False
            //if it is True then repeat the Above do{statement} else exit the while loop,once for all.

            while (cursor.moveToNext());

            deviceDb.close(); //TODO : safely close the database or u wont be able to use again because it already been used here.
        }  //end of if{statement}
        else { //TODO : put some code below that will get executed when the database is empty!
            //hides the fragment title along with total devices and divider when there database is empty

            jump.replaceVoidFragment(getFragmentManager(), R.integer.light_bulb_fragment);
        }
    }

    /**
     * Structures the model by populating the data from the SQLiteDatabase {@link ModelSampleDevice }
     *
     * @param deviceIndex    Location of the Device Item in the database row
     * @param relayNum       Gets the Relay Number
     * @param deviceIp       Gets the IP Address such as '192.168.225.10'
     * @param deviceName     Gets the device name such as 'laptop charger'
     * @param deviceTypeName Gets the type to identify the device such as 'light_bulb'
     * @param deviceLocation Gets where the device is located.
     */
    private void setDataModel(int deviceIndex, int relayNum, String deviceIp, String deviceName, String deviceTypeName, String deviceLocation) {
        /*
         Second,Structuring up the AllDeviceModel data model
         */
        ModelSampleDevice model = new ModelSampleDevice();
        model.setIndex(deviceIndex);
        model.setDeviceIp(deviceIp);
        model.setDeviceName(deviceName);
        model.setDeviceLocation(deviceLocation);
        model.setDeviceType(deviceTypeName);
        model.setDeviceImage(deviceImage);                //TODO : change the device icon.use 'bulb' icon for light bulb only and so on..
        model.setRelayNum(relayNum);
        model.setDeviceStatus(getString(deviceStatus));   //TODO : update the status according to the device state. ON|OFF
        model.setStatusDataTime(getString(deviceStatusTime)); //TODO : update the time when the device's state changes
        allDeviceList.add(model);                 //add the model data structures to the arrayList.
    }

    /**
     * sets recycler view. For more Detail See {@link AdapterSampleDevice}
     */
    private void setupRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.id_recycler_container_device);
        recyclerView.setHasFixedSize(true);                                                //does recycler view has fixed size. YES!
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());  //tells us to line list vertically(linear)
        adapter = new AdapterSampleDevice(allDeviceList);             //bridges the gap between array list and recycler_view
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //TODO : pass the device type to adapter which then passes it to the viewholder,again passes it to bottomSheet dialog
        //TODO : this deviceType is necessary to perform action such as Edit|Delete and so on..
        adapter.setDeviceType(R.string.title_lighting);
        //TODO : pass the device ON|OFF image.for turning on|off effects
        adapter.setDeviceOn_img(deviceON_img);
        adapter.setDeviceOff_img(deviceOFF_img);
        //TODO : pass the getFragmentManager() which is later used to fragmentTransaction later in the app.
        adapter.setFragmentMgr(getFragmentManager());
        adapter.setFragmentContext(getContext());
        //totalDevice = adapter.getItemCount(); //TODO : remove the comment when you want to get total devices from the arrayList | No database!

        adapter.setDeviceItemClickListener(new AdapterSampleDevice.onDeviceItemClickListener() {
            @Override
            public void onSwitchStateChangeListener(int position, boolean isChecked) {
                if (allDeviceList.get(position) != null) {
                    model = allDeviceList.get(position);

                    //TODO : sends this.link below to WebView
                    String link = "http://".concat(model.getDeviceIp()).concat("/RELAY_").concat(model.getRelayNum() + "");
                    //             http//192.168.225.10/RELAY_1=1

                    //TODO : checks the switch's state and toggle link to turn device ON|OFF
                    if (isChecked) {
                        setWeb(link.concat(getString(R.string.equal_on)));
                    } else {
                       setWeb(link.concat(getString(R.string.equal_off)));
                    }

                }
            }

        });

    }

    /**
     * Sets Webview to request device server!
     *
     * @param link that will be <a href="http://192.168.225.12/RELAY_1=ON|OFF"><CODE>http://192.168.225.12/RELAY_1=ON</CODE></a>
     */
    private void setWeb(String link) {
        WebView w = view.findViewById(R.id.id_device_web_view);
        w.getSettings().setJavaScriptEnabled(false);
        w.getSettings().setLoadWithOverviewMode(true);
        w.getSettings().setUseWideViewPort(false);
        w.getSettings().setSupportZoom(false);
        w.loadUrl(link);
        s.setLog(getString(R.string.link).concat(link));
        w.setWebViewClient(new WebViewClient());
    }


    /**
     * @deprecated currently not in operation.
     */
    //otherwise it will keep adding the same arraylist to the existing list when the fragment is visible
    @Override
    public void onPause() {
        super.onPause();/*
        allDeviceList.clear();*/ //TODO : clears the array list when fragment is no longer visible.
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        s.setMethod("onResume()");
        s.setLog("LightBulb Database Adapter Refreshed");
    }
}
