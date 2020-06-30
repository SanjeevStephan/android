package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Jump;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory.DialogNewDeviceCategory;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.Timer;
import java.util.TimerTask;

public class DevicesFragmentContainer extends Fragment {

    private static final String TAG = "DevicesFragmentContaine";
    Show s = new Show(TAG);
    Jump jump;
    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    int add = 0;
    boolean isLoaded = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_devices_container, container, false);

        jump = new Jump(getFragmentManager());

        defaultFragment();
        onAddNewLayoutClick();


       /* swipeRefreshLayout = view.findViewById(R.id.id_fragment_device_container_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                defaultFragment();

                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });*/


        return view;
    }

    private void setProgress() {

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading Devices");
        dialog.setMessage("Please Wait..");
        dialog.show();
        long delay = 500;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                dialog.dismiss();

            }
        };

        timer.schedule(task,delay);

    }


    private void defaultFragment() {
        setProgress();
        jump.replaceDeviceFragment(R.integer.light_bulb_fragment);
        jump.replaceDeviceFragment(R.integer.fan_fragment);
        jump.replaceDeviceFragment(R.integer.socket_fragment);
        jump.replaceDeviceFragment(R.integer.no_fragment);

    }

    /**
     * invoke method when +Add New Device is clicked!
     * Dialog {@link DialogNewDeviceCategory} Appears
     */
    private void onAddNewLayoutClick() {
        RelativeLayout parentLayout_addDevice = view.findViewById(R.id.id_device_add_new_relative_layout);
        parentLayout_addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add += 1;
                s.setLog("Add New Button Clicked : " + add);

                if (getFragmentManager() != null) {
                    DialogNewDeviceCategory dialogNewDeviceCategory = new DialogNewDeviceCategory();
                    dialogNewDeviceCategory.show(getFragmentManager(), getString(R.string.key_device));
                }

            }
        });
    }
}
