package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase;

import android.content.Context;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

/**
 * Saves the Devices to separate Databases based on the their Device-Types!
 * @author Sanjeev Stephan Murmu
 * @since 17th Oct 2019
 */
public class AddNewRelayDevice
{
    private Show show = new Show("AddNewRelayDevice");
    private LightBulbDatabase lightBulbDB;
    private FanDatabase fanDB;
    private SocketDatabase socketDB;
    private CCTVDatabase cctvDb;
    private int deviceImg, relayOrPortNum;
    private String deviceTypeName, deviceNickName,deviceIp,deviceLocation;
    private boolean isSaved = false;
    private String[] deviceLists = {"Light Bulb","Fan","SmartPlug","Sensor","CCTV","TV"}; //not in use!.
    private Context c;
    public AddNewRelayDevice(Context context)
    {
        this.c = context;
        //TODO : initialize the Database and creates new instances
        lightBulbDB = new LightBulbDatabase(context);
        fanDB       = new FanDatabase(context);
        socketDB    = new SocketDatabase(context);
        cctvDb      = new CCTVDatabase(context);
    }
    private void setMsg()
    {
        show.setLog("----------------[ saveDevice() ]----------------------");
        show.setLog("DEVICE TYPE NAME : " + deviceTypeName);
        show.setLog("DEVICE NICKNAME : "  + deviceNickName);
        show.setLog("DEVICE IP : "        + deviceIp);
        show.setLog("RELAY|PORT NUMBER: "      + relayOrPortNum);
        show.setLog("DEVICE LOCATION : "  + deviceLocation);
    }

    public boolean saveDevice( int relayOrPortNum, String deviceTypeName, String deviceNickName, String deviceIp, String deviceLocation) {
        this.deviceImg = deviceImg; //not  in use!
        this.relayOrPortNum = relayOrPortNum;
        this.deviceTypeName = deviceTypeName;
        this.deviceNickName = deviceNickName;
        this.deviceIp = deviceIp;
        this.deviceLocation = deviceLocation;


        setMsg();

        switch(deviceTypeName)
        {
            case "Lighting" :

                if(lightBulbDB.insertDevice(this.deviceTypeName,this.deviceNickName,this.deviceIp,this.relayOrPortNum,this.deviceLocation))
                { isSaved = true; show.setLog("Light Bulb Inserted Successfully"); }
                else
                { isSaved = false; show.setLog("Failed to Insert Data!."); }

                break;
            case "Fan":

                if(fanDB.insertDevice(this.deviceTypeName,this.deviceNickName,this.deviceIp,this.relayOrPortNum,this.deviceLocation))
                { isSaved = true; show.setLog("Fan Inserted Successfully"); }
                else
                { isSaved = false; show.setLog("Failed to Insert Data!."); }

                break;
            case "SmartPlug":

                if(socketDB.insertDevice(this.deviceTypeName,this.deviceNickName,this.deviceIp,this.relayOrPortNum,this.deviceLocation))
                { isSaved = true; show.setLog("Socket Switch Inserted Successfully"); }
                else
                { isSaved = false; show.setLog("Failed to Insert Data!."); }

                break;
            case "Sensor":

                break;
            case "CCTV":

                if(cctvDb.insertDevice(this.deviceTypeName,this.deviceNickName,this.deviceIp,this.relayOrPortNum,this.deviceLocation))
                { isSaved = true; show.setLog("CCTV Camera Inserted Successfully"); }
                else
                { isSaved = false; show.setLog("Failed to Insert Data!."); }

                break;
            case "T.V":
                 //space left for other appliances!
                break;
        }
        return isSaved;
    }


}
