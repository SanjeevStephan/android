package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyArrayLists;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV.ModelCCTV;

import java.util.ArrayList;

/**
 * not in use
 */
public class MyCameraList
{
    public static void createList(ArrayList<ModelCCTV> arrayList)
    {
        arrayList.add(new ModelCCTV(1, "192.168.225.30", 8080,"browserfs.html", "Computer Room"));
        arrayList.add(new ModelCCTV(2, "192.168.225.31", 8080,"browserfs.html",  "Outside"));
        arrayList.add(new ModelCCTV(1, "192.168.225.32", 8080,"browserfs.html",  "Computer Room"));
        arrayList.add(new ModelCCTV(2, "192.168.225.33", 8080,"browserfs.html",  "Outside"));
    }
}
