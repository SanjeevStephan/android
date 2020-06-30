package com.sanjeev.stephan.murmu.smartapp;

/*
    -------------------- What does this Class Do [GetItemAtPosition.java] ------------------------------------------
    This Java Class [GetItemAtPosition.java] is used in the MainActivity.java class
    when an RecyclerView ArrayList Item is clicked then instance of this class is created.
    and Some arguments such as [position | cursor | deviceDB] needs to be passed into the Constructor of this class.
    Which then based on the parameter [position] gets the Cursor location of the Data in the Database row.
    Using the syntax i.e (cursor = deviceDB.getItemAtPosition(position));
    When the database row is determined using the Cursor.
    We can move forward in the row's columns to get the desired data
    Using the syntax i.e( cursor.getString(columnsPosition) which returns a String value.
 */
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;


public class GetItemAtPosition
{
    private int deviceID;
    private String deviceName,ipAddress,relayNum,relayState,deviceLocation;
    private int deviceId_atPosition = 0;
    private int deviceName_atPosition = 1;  //cursor(position) of item in the database row
    private int ipAddress_atPosition  = 2;
    private int relayNum_atPosition   = 3;
    private int relayState_atPosition = 4;
    private int deviceLocation_atPosition = 5;
    private Cursor cursor_here;
    public GetItemAtPosition(int position,Cursor cursor,AllDeviceDatabase deviceDB)
    {
        int add_one_to_position = position + 1; // this is because if the index position is zero then it will try to fetch the data at position 0.
        this.cursor_here = cursor;
        cursor_here = deviceDB.getItemAtPosition(add_one_to_position);

        /*
          if cursor is not null and cursor position is greater than zero(0) them..
         */
        if(cursor_here != null && cursor_here.getCount() > 0)
        {
            //execute below code
            cursor_here.moveToFirst();
            do
            {
                /*
                    getString from the cursor(location in database) and assign it to String variable.
                 */
                deviceID   = cursor_here.getInt(deviceId_atPosition);
                deviceName = cursor_here.getString(deviceName_atPosition);
                ipAddress  = cursor_here.getString(ipAddress_atPosition);
                relayNum   = cursor_here.getString(relayNum_atPosition);
                relayState = cursor_here.getString(relayState_atPosition);
                deviceLocation = cursor_here.getString(deviceLocation_atPosition);

                showMe(); //show me what you got from the cursor.
            }
            while (cursor_here.moveToNext()); //loop until you get next position to move.
            deviceDB.close(); // safely close the AllDeviceDatabase for other methods to use it.
        }
    }

   private void showMe()
    {
        setToast("Calling 'GetItemAtPosition() method");
        setToast("Device ID : " + deviceID);
        setToast("Device Name : " + deviceName);
        setToast("IP : " + ipAddress);
        setToast("Relay Num :" + relayNum);
        setToast("Relay State : " + relayState);
        setToast("Location : " + deviceLocation );

    }
    private void setToast(String text)
    {
        Log.i("TAG", text);
    }
    public String[] getStringArrays()
    {
        /*
          deviceID      - 0;
          deviceName    - 1;
          ipAddress      -2;
          relayNum       -3;
          relayState     -4;
          deviceLocation -5;
         */
        /*
           str_deviceId contains int data-type concatenated into string.
           before using this String as Int your need to trim() the spaces and parse it into Integer
           Using the syntax i.e(Interger.parseInt(str_deviceId).trim()) at the receiving end.
         */
        final String str_deviceId = deviceID + "";

        String itemLists[] = {str_deviceId,deviceName, ipAddress, relayNum, relayState, deviceLocation};
        return itemLists;
    }

    public String getLink()
    {

        String link = "http://" + getIpAddress() + "/RELAY_" + getRelayNum() ;/* http://192.168.225.10/RELAY_1 */
        return link;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getRelayNum() {
        return relayNum;
    }

    public String getRelayState() {
        return relayState;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }
}
