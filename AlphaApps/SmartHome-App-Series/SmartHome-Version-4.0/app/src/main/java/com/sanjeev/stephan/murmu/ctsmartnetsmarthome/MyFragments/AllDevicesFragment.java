package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory.DialogNewDeviceCategory;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

/**
 * currently not in use.!
 * A simple {@link Fragment} subclass.
 */
public class AllDevicesFragment extends Fragment {

    private static final String TAG = "AllDevicesFragment";
    private Show s = new Show(TAG);
    private int add = 0;
    private View view;
    private int parentContainerLayout = R.layout.fragment_all_devices; //Main Layout Container!

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(parentContainerLayout, container, false);
        onAddNewLayoutClick();

        return view;
    }

    /**
     * invoke method when +Add New Device is clicked!
     * Dialog {@link DialogNewDeviceCategory} Appears
     */
    private void onAddNewLayoutClick()
    {
        RelativeLayout parentLayout_addDevice = view.findViewById(R.id.id_device_add_new_relative_layout);
        parentLayout_addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add += 1;
                s.setLog("Add New Button Clicked : " + add);

                if(getFragmentManager() != null)
                {
                    DialogNewDeviceCategory dialogNewDeviceCategory = new DialogNewDeviceCategory();
                    dialogNewDeviceCategory.show(getFragmentManager(),getString(R.string.key_device));
                }

            }
        });
    }

}
