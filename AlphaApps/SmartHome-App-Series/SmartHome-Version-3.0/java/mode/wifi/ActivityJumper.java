package com.sanjeev.stephan.murmu.ctnetwork.smarthome.mode.wifi;

import android.content.Context;
import android.content.Intent;

public class ActivityJumper
{
    static Intent intent;

    public static void setJumper(Context context,String activity_name)
    {
        switch(activity_name)
        {
            case "WiFiActivity":
                intent = new Intent(context, WiFiActivity.class);
                context.startActivity(intent);
                break;
            case "AddDeviceActivity":
                intent = new Intent(context, AddDeviceActivity.class);
                context.startActivity(intent);
                break;

        }

    }
}
