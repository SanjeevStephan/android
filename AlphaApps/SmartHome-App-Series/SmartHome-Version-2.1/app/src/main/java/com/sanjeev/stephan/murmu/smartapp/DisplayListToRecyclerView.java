package com.sanjeev.stephan.murmu.smartapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import java.util.ArrayList;

public  class DisplayListToRecyclerView
{

    ArrayList<ItemModel> arrayList;
    AllDeviceDatabase deviceDB;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    TempItemData itemData;
    ItemModel model;
    String deviceName,ipAddress,relayNum,relayState,deviceLocation;
    private boolean isListEmpty = false;
    int deviceName_atPosition = 1;
    int ipAddress_atPosition = 2;
    int relayNum_atPosition = 3;
    int relayState_atPosition = 4;
    int deviceLocation_atPosition = 5;

    public DisplayListToRecyclerView(SQLiteDatabase sqLiteDatabase)
    {
        itemData = new TempItemData();
     //   deviceDB = new AllDeviceDatabase(context);
     //   sqLiteDatabase = deviceDB.getReadableDatabase();
        cursor  = deviceDB.getAllData(sqLiteDatabase);

        if(cursor != null && cursor.getCount() >0 )
        {
           isListEmpty = true;
           cursor.moveToFirst();
           do
           {
               deviceName     = cursor.getString(deviceName_atPosition);
               ipAddress      = cursor.getString(ipAddress_atPosition);
               relayNum       = cursor.getString(relayNum_atPosition);
               relayState     = cursor.getString(relayState_atPosition);
               deviceLocation = cursor.getString(deviceLocation_atPosition);


               storeTempData(deviceName,ipAddress,relayNum,relayState,deviceLocation);

           }
           while(cursor.moveToNext());
           deviceDB.close();
        }
        else
        {
            isListEmpty = false;
        }
    }

    public boolean isDisplayListEmpty()
    {
        return isListEmpty;
    }

    private void storeTempData(String deviceName,String ipAddress,String relayNum,String relayState,String deviceLocation)
    {
        itemData.setDeviceName(deviceName);
        itemData.setIpAddress(ipAddress);
        itemData.setRelayNum(relayNum);
        itemData.setRelayState(relayState);
        itemData.setDeviceLocation(deviceLocation);

        model = new ItemModel(deviceName, ipAddress, relayNum, deviceLocation);
        arrayList = new ArrayList<>();
        arrayList.add(model);
    }
}
