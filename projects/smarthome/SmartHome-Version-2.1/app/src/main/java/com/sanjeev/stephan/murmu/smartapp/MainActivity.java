package com.sanjeev.stephan.murmu.smartapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity
{

    ArrayList<ItemModel> arrayList = new ArrayList<>(); // array of list
    ItemAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager ;
    WebView w;
    AllDeviceDatabase deviceDB;
    Cursor cursor;
    DisplayListToRecyclerView displayList;
    Intent intent;
   // String device_name,ip_address,relay_num,location,url;
     int relayState=0;
     GetItemAtPosition getItemAtPosition;
     Debugger debug = new Debugger();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setToast("Starting MainActivity.class");
        w = (WebView) findViewById(R.id.webview_id);

        // display data from the database in to the RecyclerView

        deviceDB = new AllDeviceDatabase(this);
        SQLiteDatabase sqLiteDatabase = deviceDB.getReadableDatabase();
        //Cursor cursor = deviceDB.getAllData(sqLiteDatabase);

       // displayList = new DisplayListToRecyclerView(sqLiteDatabase);

       // if(displayList.isDisplayListEmpty() != true)  { newActivity(); }
        displayList(sqLiteDatabase);
        buildRecyclerView(); //build the recyclerview.

    }

    void displayList(SQLiteDatabase sqLiteDatabase)
    {

       // setToast("Called displayList()");
       // deviceDB = new AllDeviceDatabase(this);
//        SQLiteDatabase sqLiteDatabase = deviceDB.getReadableDatabase();

        cursor = deviceDB.getAllData(sqLiteDatabase);

            if(cursor != null && cursor.getCount() > 0)
             {
                cursor.moveToFirst();
                do {
                    int index_position = cursor.getInt(0);
                    String device_name = cursor.getString(1); //position of device_name in the database
                    String ip_address = cursor.getString(2); //position of ip_address in the database.
                    String relay_num = cursor.getString(3);
                    int relayState = cursor.getShort(4);
                    String location = cursor.getString(5);

                    ItemModel model = new ItemModel(device_name,ip_address,relay_num,location); // pass the data to ItemModel
                    arrayList.add(model);// add the model dataset to the arrayList.

                }
                while(cursor.moveToNext());

                deviceDB.close();
        }
        else
        {
            //if no database is empty execute this block.
            setToast("Please Add Some WiFi Devices.");
        /*    if(deviceDB.insertDevice("Add New Device", "Enter IP Address", "Enter Relay Num", 0, "Enter Device Location"))
            {
                debug.setToast(this,"Default Item Inserted into Database");
               // adapter.notifyItemInserted(0);
            }
            else
            { debug.setToast(this,"Failed to Insert default Item");}*/

        }


    }

    void buildRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ItemAdapter(arrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItem_ClickListener(new ItemAdapter.OnItem_ClickListener() {
            @Override
            public void onItem_click(int position)
            {
                int incrementOne = 1;
                setToast("Item clicked at position : " +  position);
                int position_num = position + incrementOne ; //Add 1 every time when the recyclerview list item is clicked to avoid counter to go zero.
                //Because item inside database starts from One instead of zero.

                //Calling below class to getInformation about the item at any specific position.
                setToast(getLink(position));

            }


            @Override
            public void onMoreOptionMenu_click(int position,String menuOption)
            {


                switch(menuOption)
                {
                    case "edit" :
                        //setToast("You have clicked Edit Menu Option");
                  /*      getItemAtPosition = new GetItemAtPosition(position, cursor, deviceDB);
                        int deviceId      = getItemAtPosition.getDeviceID();
                        String deviceName = getItemAtPosition.getDeviceName();
                        String ipAddress = getItemAtPosition.getIpAddress();
                        String relayNum    = getItemAtPosition.getRelayNum();
                        String deviceLocation = getItemAtPosition.getDeviceLocation();
                        updateDevice(deviceId,deviceName,ipAddress,relayNum,deviceLocation);*/

                        UpdateExistingDevice(position);

                        break;
                    case "delete" :
                        DeviceDelete deviceDelete = new DeviceDelete(MainActivity.this, position);
                        //adapter.notifyItemRemoved(position);
                        break;
                }
            }
        });




    }

    String getLink(int position)
    {
        getItemAtPosition = new GetItemAtPosition(position, cursor, deviceDB);
        return getItemAtPosition.getLink();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.nav_add_new_device:
                Intent intent = new Intent(this, DeviceAddActivity.class);
                startActivity(intent);
                //addTaskDialog();
                break;
        }
        return true;
    }

    void setToast(String text)
    {
       debug.setToast(this,text);
    }

    void setUrl(String url)
    {
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setLoadWithOverviewMode(true);
        w.getSettings().setUseWideViewPort(true);
        w.getSettings().setSupportZoom(false);
        w.getSettings().setBuiltInZoomControls(false);
        w.loadUrl(url);
        w.setWebViewClient(new WebViewClient());


    }

    void addNewDevice()
    {
        intent = new Intent(this, DeviceAddActivity.class);
        startActivity(intent);
    }

  /*  void updateDevice(int deviceId,String deviceName,String ipAddress,String relayNum,String deviceLocation)
    {
        intent = new Intent(this, UpdateDeviceActivity.class);
        intent.putExtra("deviceId", deviceId + "");
        intent.putExtra("deviceName", deviceName);
        intent.putExtra("ipAddress", ipAddress);
        intent.putExtra("relayNum", relayNum);
        intent.putExtra("deviceLocation", deviceLocation);
        startActivity(intent);
    }*/

    void UpdateExistingDevice(int position)
    {
        int incrementOne = 1;
        int id_to_search = position + incrementOne ; //Add 1 every time when the recyclerview list item is clicked to avoid counter to go zero.
        //Because item inside database starts from One instead of zero.

        Bundle dataBundle = new Bundle();
        dataBundle.putInt("positionId", id_to_search);

        intent = new Intent(this, DeviceUpdateActivity.class);
        intent.putExtras(dataBundle);
        startActivity(intent);
    }






}




