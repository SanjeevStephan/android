package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces;

import androidx.fragment.app.FragmentManager;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV.ModelCCTV;

public interface InterfaceMainActivity
{
    /*
       Below Method is triggered when the dialog category is opened and an item is selected such as (lighting|fan|socket) and so on.!
     */
    void onDeviceItemSelectedListener(int deviceImage,String deviceTitle);
    void onPopupMenuItemSelectedListener(int itemPosition, ModelCCTV cctvModel, String menu, int menuId, FragmentManager fm);
     void onFragmentSwippedListener(int position);
}
