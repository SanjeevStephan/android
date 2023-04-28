package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.GlobalVariables;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.R;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi.database.DeviceDatabase;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.tools.ShowMsg;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.tools.SendLinkRequest;


public class WiFiActivity extends AppCompatActivity {

    ArrayList<DeviceModel> arrayList = new ArrayList<>();  //contains the ArrayList based to the DeviceModel data structure.
 //   int[] totalDevice = new int[arrayList.size()];
    DeviceAdapter adapter ;
    Cursor cursor; //this points the location of data inside the database | Used as the navigation tool for data inside the datatabase using pointer called(cursor)
    DeviceDatabase deviceDB;
    SQLiteDatabase sqLiteDatabase;
    /* ---------------------[ My Custom Classes Below ]-----------------*/

    ShowMsg show = new ShowMsg(this);
    int device_type; //declare the device_type as (int) for holding the position of the device_list from the spinner.
    String WhereAmI = "WiFiActivity";
    BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi); // tells us which res\layout\file is in used.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.app_name);
        setFab(); // setting up the Floating Action Bar(FAB);

        //initialize the 'DeviceDatabase(class)' and creating new instance(clone) of the class.
        // and this =>(WiFiActivity.this ) is passed as a Context to tell the class from where it is being called from.

            deviceDB = new DeviceDatabase(WiFiActivity.this); //= new DeviceDatabase(this);
         sqLiteDatabase = deviceDB.getReadableDatabase();

        //  createList(); // NOT_IN_USE!
        displayList();
        setRecyclerView();

    }

    private void setFab() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMsg.setSnackbar(view, "Add New Device");
               // createBottomSheet();
                go2AddDevice();

            }
        });

    }
    void go2AddDevice()
    { ActivityJumper.setJumper(this,"AddDeviceActivity");}

    private void setRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.wifi_activity_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DeviceAdapter(arrayList); //DeviceAdapter acts as the bridge between the the View and the ArrayList.
        recyclerView.setAdapter(adapter);
        adapter.setDeviceItemClickListener(new DeviceAdapter.OnDeviceItemClickListener() {
            @Override
            public void onItemClickListener(int position)
            {

                ShowMsg.setMsgLog("getRecyclerView()","Item Clicked At Positon :" + position);
            }

            @Override
            public void onStateChangeListener(int position, boolean state) {

                DeviceModel device = arrayList.get(position);
                final String deviceName = device.getDeviceName();
                final String ip = device.getDeviceIP();
                final int relay = device.getDeviceRelay();
                final String deviceLocation = device.getDeviceLocation();
                if(state==true)
                {
                    setWebView(getLink(ip,relay,"ON"));
                    //arrayList.add(new DeviceModel(R.drawable.light_bulb_on,deviceName,ip,relay,deviceLocation));
                    device.setDeviceImage(R.drawable.light_bulb_on);
               //     adapter.notifyDataSetChanged();
                }
                else
                {

                    setWebView(getLink(ip, relay, "OFF"));
                    device.setDeviceImage(R.drawable.light_bulb_off);
                  //  adapter.notifyDataSetChanged();
                  //  arrayList.add(new DeviceModel(R.drawable.light_bulb_off,deviceName,ip,relay,deviceLocation));

                }

              //  ShowMsg.setMsgLog("setRecyclerView","Position :" + position + " | State : " + state);
              //  ControlDevices(position,getLink(position), state);


            }
        });
    }

    private String getLink(String ip,int relay,String state)
    {
        String send_this_link = "http://" + ip + "/RELAY_" + relay + "=" + state;
        //                       http://192.168.225.10/RELAY_1=ON | http://192.168.225.10/RELAY_1=OFF
        return send_this_link;
    }

    void displayList()
    {

        cursor = deviceDB.getAllData(sqLiteDatabase); //get the cursor(location) of all the data stored in the database.
        /*
          if cursor is not null and cursor counter value is greater than zero(0) then execute the below code.
         */
        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst(); // move first in the column of the database row.and the run below code.
            do {
                /*
                    get the String Value using the 'ColumnIndex' through the cursor and
                    assign it the (String) and (int) Variables
                 */
                int index_position = cursor.getInt(cursor.getColumnIndex(DeviceDatabase.DEVICE_COLUMN_ID));
                device_type        = cursor.getInt(cursor.getColumnIndex(DeviceDatabase.DB_DEVICE_TYPE));
                String device_name = cursor.getString(cursor.getColumnIndex(DeviceDatabase.DB_DEVICE_NAME)); //position of device_name in the database
                String ip_address  = cursor.getString(cursor.getColumnIndex(DeviceDatabase.DB_DEVICE_IP)); //position of ip_address in the database.
                int device_image   = cursor.getInt(cursor.getColumnIndex(DeviceDatabase.DB_DEVICE_IMAGE));
                int relay_num      = cursor.getInt(cursor.getColumnIndex(DeviceDatabase.DB_RELAY_SWITCH_NUMBER));
                String location    = cursor.getString(cursor.getColumnIndex(DeviceDatabase.DB_LOCATION));

                //after that send the Variables to the 'DeviceModel(class)' for structuring the data with the arrayList
                //which later can be used with the ArrayAdapter by passing the ArrayList<DeviceModel> Data Structured Model.
                DeviceModel model = new DeviceModel(device_image,device_name,ip_address,relay_num,location); // pass the data to ItemModel
                arrayList.add(model);// add the model dataset to the arrayList.

            }
            while(cursor.moveToNext());  //move to next column in the row it true .

            deviceDB.close(); //close the database,so the other class can safely use it.
        }
        else
        {
            //if no database is empty execute this block.
            ShowMsg.setToast(this,"Please Add Some WiFi Devices.");
        }


    }
    /*

   private String getLink(int position,String state)
    {
        final DeviceModel device = arrayList.get(position);
        String ip = device.getDeviceIP();
        String relay = "/RELAY_" + device.getDeviceRelay();
        String link = "http://" + ip + relay;
       // show.setMsgLog(WhereAmI + ">> getLink()",link);
        String requestLink = null;
        switch(state)
        {
            case "ON"  :
                requestLink = link + "=ON" ;
                break;
            case "OFF" :
                requestLink = link + "=OFF";
                break;
        }
        return requestLink;
    }

    private void ControlDevices(int position,String link, boolean state)
    {
        String requestON = link + "=ON";
        String requestOFF = link + "=OFF";

        final DeviceModel device = arrayList.get(position);

        if (state)
        {
            GlobalVariables.setLink(requestON);

        //    if(device_type == 1) {
            //    device.setDeviceImage(R.drawable.light_bulb_on);
             //   adapter.notifyDataSetChanged();
         //   }
            setWebView(requestON);
        } else
            {
              GlobalVariables.setLink(requestOFF);
              setWebView(requestOFF);
            //    if(device_type == 1) {
              //      device.setDeviceImage(R.drawable.light_bulb_off);
                //    adapter.notifyDataSetChanged();
            //    }

            }

    }*/

    private void createList()
    {
        /*
          This Method is currently not operational
          but i was generous to keep it rather than delete it
          because it can later be used for testing purposes and so on..
         */
        final int img = R.drawable.light_bulb_off;
        arrayList.add(new DeviceModel(img, "Computer Light One", "192.168.225.12", 3, "Computer Room"));
        arrayList.add(new DeviceModel(img, "Computer Light Two", "192.168.225.12", 1, "Computer Room"));
        arrayList.add(new DeviceModel(img, "Laptop Charger", "192.168.225.12", 2, "Computer Room"));
        arrayList.add(new DeviceModel(img, "Monitor", "192.168.225.12", 4, "Computer Room"));
        arrayList.add(new DeviceModel(img, "Desktop Switch", "192.168.225.10", 3, "Computer Room"));
        arrayList.add(new DeviceModel(img, "Mummy's Room Light", "192.168.225.10", 2, "Mummy's Room"));
        arrayList.add(new DeviceModel(img, "Kitchen Light", "192.168.225.10", 1, "Kitchen"));
        arrayList.add(new DeviceModel(img, "Fan", "192.168.225.13", 1, "Computer Room"));
        arrayList.add(new DeviceModel(img, "Bathroom Light", "192.168.225.10", 4, "Bathroom"));
        arrayList.add(new DeviceModel(img, "Lamborghini Race Sound", "192.168.225.14", 1, "Computer Room"));

    }
    void showPage()
    {

        //sendLink.sendRequestLink("httPfdkfljdfkdjfldkfjlkdsfjlds link received successfully");
        SendLinkRequest link_fragment = new SendLinkRequest();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.id_container_fragment, link_fragment);
        ft.commit();
    }

    private void setWebView(String url) {

        WebView w = (WebView) findViewById(R.id.id_device_model_web_view);
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setLoadWithOverviewMode(true);
        w.getSettings().setUseWideViewPort(true);
        w.getSettings().setSupportZoom(false);
        w.getSettings().setBuiltInZoomControls(false);
        w.loadUrl(url);
        show.setMsgLog("setWebView()", url);
        w.setWebViewClient(new WebViewClient());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wi_fi,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.id_nav_add_device:

                break;
            default : break;
        }
        return true;
    }
/*
    void createBottomSheet()
    {
        if(bottomSheetDialog == null)
        {
            View view = LayoutInflater.from(this).inflate(R.layout.content_add_device, null);
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
        }



    }
*/

}
