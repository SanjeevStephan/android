package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A helper class to manage database creation and version management
 * @author Sanjeev Stephan Murmu
 * @since 3rd oct 19
 * version 1.0
 */
public class CCTVDatabase extends SQLiteOpenHelper
{

    public static final int DB_VERSION = 1;
    public static final String DATABASE_NAME = "cctv.db";
    public static final String DB_TABLE = "tablee";
    public static final String DB_COLUMN_ID = "id";
    public static final String DB_DEVICE_TYPE_NO = "device_type_no";
    public static final String DB_DEVICE_TYPE_NAME = "device_type_name";
    public static final String DB_URL_ATTRIBUTE = "device_name";
    public static final String DB_DEVICE_IP = "device_ip";
    public static final String DB_DEVICE_IMG = "device_img";
    public static final String DB_PORT_NUM = "relay_num";
    public static final String DB_DEVICE_LOCATION = "device_location";


    /**
     *
     * @param context Determines where the class is called from
     */
    public CCTVDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    /**
     * Called when the database is created for the first time.This is where the creation of the tables and
     * the initial population of the tables should happen
     * @param db The database
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + DB_TABLE + " "
                + "(id INTEGER PRIMARY KEY,"
             //   + DB_DEVICE_TYPE_NO + " INTEGER,"
                + DB_DEVICE_TYPE_NAME + " TEXT,"
                + DB_URL_ATTRIBUTE + " TEXT,"
                + DB_DEVICE_IP + " TEXT,"
             //   + DB_DEVICE_IMG + " INTEGER,"
                + DB_PORT_NUM + " INTEGER,"
                + DB_DEVICE_LOCATION + " TEXT" +
                ")");
    }

    /**
     * Called when the database needs to be upgraded.
     * @param db The database
     * @param i the old Database version
     * @param i1 The new Database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    /**
     *
     * @param deviceTypeName device type such as 'light bulb'
     * @param urlAttributes
     * @param deviceIp
     * @param portNum
     * @param deviceLocation
     * @return <CODE>true</CODE> is device info inserted otherwise <CODE>false</CODE>
     */
    public boolean insertDevice(String deviceTypeName,String urlAttributes,String deviceIp,int portNum,String deviceLocation)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalues = new ContentValues();
      //  cvalues.put(DB_DEVICE_TYPE_NO,deviceTypeNo);
        cvalues.put(DB_DEVICE_TYPE_NAME, deviceTypeName);
        cvalues.put(DB_URL_ATTRIBUTE, urlAttributes);
        cvalues.put(DB_DEVICE_IP, deviceIp);
     //   cvalues.put(DB_DEVICE_IMG, deviceImg);
        cvalues.put(DB_PORT_NUM,portNum);
        cvalues.put(DB_DEVICE_LOCATION,deviceLocation);
        db.insert(DB_TABLE, null, cvalues);
        return true;
    }
    public Cursor getAllData(SQLiteDatabase db)
    {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE + " ",null);
        return cursor;
    }
    public Cursor getItemAtPosition(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE id=" + id + "",null);
        return cursor;
    }
    public int getCountRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,DB_TABLE);
        return numRows;
    }
    public boolean updateDevice(Integer id,String deviceTypeName,String urlAttributes,String deviceIp,int portNum,String deviceLocation)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalues = new ContentValues();
     //   cvalues.put(DB_DEVICE_TYPE_NO,deviceTypeNo);
        cvalues.put(DB_DEVICE_TYPE_NAME, deviceTypeName);
        cvalues.put(DB_URL_ATTRIBUTE, urlAttributes);
        cvalues.put(DB_DEVICE_IP, deviceIp);
//        cvalues.put(DB_DEVICE_IMG, deviceImg);
        cvalues.put(DB_PORT_NUM,portNum);
        cvalues.put(DB_DEVICE_LOCATION,deviceLocation);
        db.update(DB_TABLE,cvalues,"id = ? ",new String[] {Integer.toString(id)});
        return true;
    }

    public Integer deleteDevice(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE,"id = ? ",new String[] { Integer.toString(id)});
    }

}
