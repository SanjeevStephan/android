package com.sanjeev.stephan.murmu.smartapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Debugger
{
    public void setToast(Context context, String text_message)
    {
        Toast.makeText(context, text_message, Toast.LENGTH_SHORT).show();
        setLog(text_message);
    }
    public void setLog(String log_message)
    {
        final String tag_name = "TAG";
        Log.i(tag_name, log_message);
    }
}
