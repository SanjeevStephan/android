package com.sanjeev.stephan.murmu.ctnetwork.smarthome.tools;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;
import android.view.View;

public class ShowMsg
{
    Context context;
    public static String WhereAmI;
    static String tagName = "TAG";
    public ShowMsg(Context context)
    {
        this.context = context;
    }
    public static void setToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static void setMsgLog(String location,String message)
    {
        //Log.i(tagName,"-----------------------[ " + location + " ]-----------------------");
        String msg = "@" + location + " : " + message;
        Log.i(tagName,msg);
    }
    public static void setSnackbar(View view,String message)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
