package com.sanjeev.stephan.murmu.ctsmartnet.smarthome;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Show
{

    private String location,tagName = "TAG";
    private String methodName = " ";
    private Context context;
    public Show(String location) {
        this.location = location;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setLog(String msg)
    {
        Log.i(tagName,location.concat("=>").concat(methodName).concat(msg));
    }

    public void setMethod(String methodName)
    {
       this.methodName = "[" + methodName + "]";
    }

    public void setToast(String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

}
