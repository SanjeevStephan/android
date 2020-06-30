package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyBottomSheets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs.DialogDelete;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice.ModelSampleDevice;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.EditActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.ArrayList;

/**
 * Displays the Bottom Sheet which contains ListView
 * with items such as (Add to Favourite|Edit|Delete)
 */
public class BottomDeviceEditDelete {
    private static final String TAG = "BottomDeviceEditDelete";
    private Show s = new Show(TAG);
    private BottomSheetDialog deviceInfoView;
    private ArrayList<ModelSampleDevice> arrayList;
    private int position = -1;
    private Context context,fragentContext;

    private int deviceTypeInteger = 0;
    private String[] titleArray = {"Add To Favourite", "Edit", "Delete"};
    private FragmentManager fragmentManager;

    //Variables
    private int NO_DEVICE = -1; //let assume not device should be at '-1'
    private int devIndex = NO_DEVICE;
    private int devRelay = NO_DEVICE;
    private int itemPosition = NO_DEVICE;
    private String devName = "";
    private String devIP = "";
    private String devLocation = "";
    private String devType = " ";

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setFragentContext(Context fragentContext) {
        this.fragentContext = fragentContext;
    }

    public BottomDeviceEditDelete(@NonNull Context context) {
        s.setLog("------------------ BOttom sheet ---------------------");
        this.context = context;
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.layout_device_bottom_menu, null);
        deviceInfoView = new BottomSheetDialog(context, R.style.CustomTransparentDialog);
        deviceInfoView.setContentView(view);
        ListView listView = view.findViewById(R.id.id_bottom_menu_list);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.bottom_selected_device_menu, android.R.layout.simple_list_item_1);
        ArrayAdapter<String> adapter = new CustomAdapterDeviceInfoList(context, titleArray);
        listView.setAdapter(adapter);
        getListItem(listView);

    }

    public void setPosition(int position) {
        this.position = position;
        s.setLog("the item position is :" + position);
    }

    public void setDeviceTypeInteger(int deviceTypeInteger) {
        this.deviceTypeInteger = deviceTypeInteger;
        s.setLog("device type : " + context.getString(deviceTypeInteger) + " received");
    }

    public void setArrayList(ArrayList<ModelSampleDevice> arrayList) {
        this.arrayList = arrayList;
    }

    public void show() {
        deviceInfoView.show();
    }

    private  void getListItem(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getDevice(); //calling the getDevice() method

                switch (titleArray[position]) {
                    case "Edit":
                        s.setLog("Edit List-Item clicked again");
                        saveDevice(); //call the saveDevice() method
                        break;
                    case "Delete":
                        s.setLog("Delete List-Item Clicked again");
                        //TODO : displays the confirmation delete dialog
                        DialogDelete dialogDelete = new DialogDelete();
                        dialogDelete.setFragmentManager(fragmentManager);
                        Bundle bundle = new Bundle();
                        bundle.putInt(context.getString(R.string.key_device_index),devIndex);
                        bundle.putString(context.getString(R.string.key_device_name), devName);
                        bundle.putInt(context.getString(R.string.key_device_type),deviceTypeInteger);
                        dialogDelete.setArguments(bundle);
                        dialogDelete.show(fragmentManager,"key_delete");
                        break;
                }
               deviceInfoView.dismiss(); // hide the bottomSheet
            }
        });
    }

    private void saveDevice() {
        //TODO : Send the Selected Item Data to the 'EditActivity.class'
        //along with device information with keys
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(context.getString(R.string.key_device_index), devIndex);
        intent.putExtra(context.getString(R.string.key_device_name), devName);
        intent.putExtra(context.getString(R.string.key_ip), devIP);
        intent.putExtra(context.getString(R.string.key_relay_or_port), devRelay);
        intent.putExtra(context.getString(R.string.key_device_type), devType);
        intent.putExtra(context.getString(R.string.key_location), devLocation);
        context.startActivity(intent);

    }

    private void getDevice()
    {
        ModelSampleDevice model = new ModelSampleDevice();
        itemPosition = position;
        model        = arrayList.get(position);
        devIndex     = model.getIndex();
        devName      = model.getDeviceName();
        devIP        = model.getDeviceIp();
        devRelay     = model.getRelayNum();
        devLocation  = model.getDeviceLocation();
        devType      = model.getDeviceType();

        s.setMethod("getDevice()");
        s.setLog("itemPosition : " + itemPosition);
        s.setLog("mDeviceIndex : " + devIndex);
        s.setLog("mDeviceName : " + devName);
        s.setLog("mDeviceIp : " + devIP);
        s.setLog("mRelayNum : " + devRelay);
        s.setLog("mLocation " + devLocation);
        s.setLog("mDeviceType : " + devType);

    }


}
