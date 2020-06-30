package com.sanjeev.stephan.murmu.smartapp;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class AllDeviceDatabase extends SQLiteOpenHelper
{

    public static final int DB_VERSION = 1;
    public static final String DATABASE_NAME = "testLink.db";
    public static final String DB_TABLE_NAME = "Weblinks";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String DB_DEVICE_NAME = "DeviceName";
    public static final String DB_DEVICE_IP = "DeviceIP";
    public static final String DB_RELAY_SWITCH_NUMBER = "RelayNum";
    public static final String DB_RELAY_STATE = "DeviceState";
    public static final String DB_LOCATION = "Location";
    private HashMap hp;

    public AllDeviceDatabase(Context context)
    {
        super(context, DATABASE_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+ DB_TABLE_NAME + " "
                + "(id INTEGER PRIMARY KEY,"
                + DB_DEVICE_NAME +" TEXT,"
                + DB_DEVICE_IP + " TEXT,"
                + DB_RELAY_SWITCH_NUMBER + " INTEGER,"
                + DB_RELAY_STATE + " INTEGER,"
                + DB_LOCATION + " TEXT"
                +")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDevice(String device_name, String device_ip, int relay_num, int relayState, String location) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_DEVICE_NAME, device_name);
        contentValues.put(DB_DEVICE_IP, device_ip);
        contentValues.put(DB_RELAY_SWITCH_NUMBER, relay_num);
        contentValues.put(DB_RELAY_STATE, relayState);
        contentValues.put(DB_LOCATION, location);
        db.insert(DB_TABLE_NAME, null, contentValues);
        return true;

    }

    public Cursor getAllData(SQLiteDatabase db)
    {
        /*
          This will retuns all the Cursor of all the Items in the Database.
         */

        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " ", null);
        return cursor;
    }

    public Cursor getItemAtPosition(int id)
    {
        /*
        This method will return the Cursor(position) of an item at Database by using th item id.
         */
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "SELECT * FROM " + DB_TABLE_NAME + " where id=" + id +"", null );
        return cursor;

    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DB_TABLE_NAME);
        return numRows;
    }

    public boolean updateDevice (Integer id, String device_name, String device_ip,int relay_num,int relayState,String location)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_DEVICE_NAME,device_name);
        contentValues.put(DB_DEVICE_IP , device_ip);
        contentValues.put(DB_RELAY_SWITCH_NUMBER,relay_num);
        contentValues.put(DB_RELAY_STATE, relayState);
        contentValues.put(DB_LOCATION, location);
        db.update(DB_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteDevice  (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE_NAME, "id = ? ", new String[] { Integer.toString(id) });
    }

    public ArrayList<String> ReadDatabase()
    {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+DB_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            array_list.add(res.getString(res.getColumnIndex(DB_DEVICE_NAME)));
            res.moveToNext();
        }

        return array_list;
    }
}

