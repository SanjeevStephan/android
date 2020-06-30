package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Jump;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice.AdapterSampleDevice;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice.ModelSampleDevice;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.FanDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.SocketDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.ArrayList;


/**
 * A simple {@link PlugSocketFragment} subclass.
 */
public class PlugSocketFragment extends Fragment {


    //constant
    private static final String TAG = "PlugSocketFragment";
    //classes
    private Cursor cursor;
    private SocketDatabase socketDb;
    private SQLiteDatabase sqLiteDatabase;
    private AdapterSampleDevice adapter;
    private Jump jump = new Jump(getFragmentManager());
    Show s = new Show(TAG); //TODO : should pass class name as the parameter for tracing bug
    private ArrayList<ModelSampleDevice> arrayList = new ArrayList<>();
    private ModelSampleDevice model = new ModelSampleDevice();
    //Global Widget
    private TextView titleView, noDeviceTextView;
    private View view, dividerView;
    //Variables
    private int NO_DEVICE_POSITION = 0;
    private int deviceStatus = R.string.currently_off, deviceStatusTime = R.string.am_12;
    private int totalDevice = R.dimen.no_device_value,
            fragmentContainer = R.layout.sample_fragment_device, //TODO : change the container layout
            deviceImage = R.drawable.device_icon_switch_off;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(fragmentContainer, container, false);
        setRetainInstance(true); //avoid fragment from refreshing!
        setReferenceIDs();
        setupRecyclerView();
        setDisplayData();
        setTitle(R.string.title_socket); //TODO : can change the fragment title from R.string.*;
        // AllDeviceArrayList.setSocket_ArrayList(arrayList); //TODO : replace it only with Array List!

        return view;
    }

    private void setReferenceIDs() {
        titleView = view.findViewById(R.id.id_sample_device_title);
        noDeviceTextView = view.findViewById(R.id.id_sample_device_no_device_text);
        dividerView = view.findViewById(R.id.id_sample_device_divider);
    }

    private void setTitle(int title) {
        titleView.setText(getString(title).concat("(").concat(totalDevice + ")"));
    }

    /**
     * Fetch Cursor location of all the data from database.
     * And Assign those data to the String Vairable for accessing later.
     */
    private void setDisplayData() {
        socketDb = new SocketDatabase(view.getContext());
        sqLiteDatabase = socketDb.getReadableDatabase();       //Read SQLiteDatabase from 'deviceDB'
        cursor = socketDb.getAllData(sqLiteDatabase); //ping-point the location of the data from 'deviceDb'
        totalDevice = socketDb.getCountRows();

        s.setLog("Displaying Data From Database");
        s.setLog("------------------------[ setDisplayData() ]---------------------------------");
        s.setLog("[Index]  Device-Type | Device-Name |  IP  | Location");

        /* if cursor is not null and cursor is greater than zero then execute below code */
        if (cursor != null && cursor.getCount() > NO_DEVICE_POSITION) {
            /* move first in the database row and start counting from there!*/
            cursor.moveToFirst();
            //first get data from database and assign it to final String variables.
            do {

                final int itemIndex = cursor.getInt(cursor.getColumnIndex(FanDatabase.DB_COLUMN_ID));
                // final int deviceTypeNo = cursor.getInt(cursor.getColumnIndex(LightBulbDatabase.DB_DEVICE_TYPE_NO));
                // final int deviceImage  = cursor.getInt(cursor.getColumnIndex(LightBulbDatabase.DB_DEVICE_IMG));
                final int relayNum = cursor.getInt(cursor.getColumnIndex(FanDatabase.DB_RELAY_NUM));
                final String deviceTypeName = cursor.getString(cursor.getColumnIndex(FanDatabase.DB_DEVICE_TYPE_NAME));
                final String deviceName = cursor.getString(cursor.getColumnIndex(FanDatabase.DB_DEVICE_NICKNAME));
                final String deviceIp = cursor.getString(cursor.getColumnIndex(FanDatabase.DB_DEVICE_IP));
                final String deviceLocation = cursor.getString(cursor.getColumnIndex(FanDatabase.DB_DEVICE_LOCATION));

                s.setLog("[" + itemIndex + "]      " + deviceTypeName + " | " + deviceName + " | " + deviceIp + " | " + deviceLocation);
                setDataModel(itemIndex, relayNum, deviceIp, deviceName, deviceTypeName, deviceLocation);
            }

            // then check it there is next row available which will return either True|False
            //if it is True then repeat the Above do{statement} else exit the while loop,once for all.

            while (cursor.moveToNext());
            socketDb.close(); //safely close the database or u wont be able to use again because it already been used here.


        }  //end of if{statement} here
        else {  //TODO : put some code below that will get executed when the database is empty!
            //hides the fragment title along with total devices and divider when there database is empty
            jump.replaceVoidFragment(getFragmentManager(), R.integer.socket_fragment);
            //TODO : add the comment when you want to display 'No Light Bulb Available' Text
            //show.setSnackbar(view,"Sorry,No Data Found In Database!");
        }
    }

    /**
     * Structures the model by populating the data from the SQLiteDatabase
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
        model.setDeviceImage(deviceImage); //TODO : change the device icon.use icon for specific device only .
        model.setRelayNum(relayNum);
        model.setDeviceStatus(getString(deviceStatus)); //TODO : update the status according to the device state. ON|OFF
        model.setStatusDataTime(getString(deviceStatusTime));     //TODO : update the time when the device's state changes
        arrayList.add(model); //add the model data structures to the arrayList.
    }

    /**
     * sets recycler view. For more Detail See {@link AdapterSampleDevice}
     */
    private void setupRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.id_recycler_container_device);
        recyclerView.setHasFixedSize(true);                                                //does recycler view has fixed size. YES!
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());  //tells us to line list vertically(linear)
        adapter = new AdapterSampleDevice(arrayList);             //bridges the gap between array list and recycler_view
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //TODO : pass the device type to adapter which then passes it to the viewholder,again passes it to bottomSheet dialog
        //TODO : this deviceType is necessary to perform action such as Edit|Delete and so on..
        adapter.setDeviceType(R.string.title_smart_plug);

        adapter.setFragmentMgr(getFragmentManager());

        //TODO : sends the FragmentManager to Adapter,then to ViewHolder,again to the BottomSheet-Edit|Delete,where we can replace this.fragment when any changes has been made!
        adapter.setFragmentMgr(getFragmentManager());

        // TODO : put the device ON|OFF image.
        adapter.setDeviceOn_img(R.drawable.device_icon_switch_on);
        adapter.setDeviceOff_img(R.drawable.device_icon_switch_off);
        totalDevice = adapter.getItemCount();

        adapter.setDeviceItemClickListener(new AdapterSampleDevice.onDeviceItemClickListener() {
            @Override
            public void onSwitchStateChangeListener(int position, boolean isChecked) {
                model = arrayList.get(position);

              //TODO : sends this.link below to WebView
                String link = "http://".concat(model.getDeviceIp()).concat("/RELAY_").concat(model.getRelayNum() + "");

                //TODO : checks the switch's state and toggle link to turn device ON|OFF
                if (isChecked) {
                    setWeb(link.concat("=ON"));
                    s.setLog(link.concat("=ON"));
                } else {
                    setWeb(link.concat("=OFF"));
                    s.setLog(link.concat("=OFF"));
                }

            }
        });

    }

    /**
     * Sets Webview for request link
     *
     * @param link that will be <code>http://192.168.225.12/RELAY_1=ON|OFF</code>
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

    //TODO : clears the array list when fragment is no longer visible.
    //otherwise it will keep adding the same arraylist when the fragment is visible

    @Override
    public void onPause() {
        super.onPause();/*
        allDeviceList.clear();*/
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        s.setMethod("onResume()");
        s.setLog("PlugSocket-DataBase Adapter Refreshed");
    }
}
