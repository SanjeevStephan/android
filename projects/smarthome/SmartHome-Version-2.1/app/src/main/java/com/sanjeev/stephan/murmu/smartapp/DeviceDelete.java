package com.sanjeev.stephan.murmu.smartapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.ArrayList;

public class DeviceDelete
{
    AllDeviceDatabase deviceDb ;
    int id_to_delete = 0;
    Debugger debug = new Debugger();
    ArrayList<ItemModel> arrayList = new ArrayList<>();
    ItemAdapter adapter = new ItemAdapter(arrayList);
    public DeviceDelete(final Context context, final int position)
    {
        id_to_delete = position + 1;
        deviceDb = new AllDeviceDatabase(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are You Sure? You Want to Delete this Device")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deviceDb.deleteDevice(id_to_delete);
                        debug.setToast(context, "-----------------------[ Deleted ]-------------------------");
                        debug.setToast(context, "Device ID : " + id_to_delete);
                        debug.setToast(context,"Device Deleted Successfully");
                        go2Activity(context);
                        //adapter.notifyItemRemoved(position);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        debug.setToast(context,"Cancelled");
                    }
                });
        AlertDialog d = builder.create();
        d.setTitle("Delete this Device?");
        d.show();
    }
    void go2Activity(Context context)
    {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
     }
}
