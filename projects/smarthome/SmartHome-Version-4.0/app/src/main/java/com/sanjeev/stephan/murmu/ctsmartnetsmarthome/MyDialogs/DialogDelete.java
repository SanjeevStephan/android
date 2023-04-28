package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Jump;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.FanDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.LightBulbDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.SocketDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

/**
 * Confirmation DialogDelete from {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyBottomSheets.BottomDeviceEditDelete}
 * passed bundle data <code> [devIndex : int][devName : String][devType : int]</code>
 */
public class DialogDelete extends DialogFragment
{
    private static final String TAG = "DialogDelete";
    private Show s = new Show(TAG);
    private int NO_DEVICE  = -1;
    private int devIndex = NO_DEVICE;
    private String devName = " ";
    private int devType = NO_DEVICE;
    private FragmentManager fragmentManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            devIndex = bundle.getInt(getString(R.string.key_device_index));
            devName  = bundle.getString(getString(R.string.key_device_name));
            devType  = bundle.getInt(getString(R.string.key_device_type));
        }
    }

    /**
     * Devices Fragment Mangger such as(lightBulbFragent,fanFragment) and so on.
     * @param fragmentManager gets fragment manager
     */
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete "+ devName)
                .setMessage("Do you want to delete?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteDevice();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }

    /**
     * checks the deviceType and calls deleteDevice method from respective deviceType's database!.
     */
    private void deleteDevice()
    {
        final LightBulbDatabase bulbDb = new LightBulbDatabase(getContext());
        final FanDatabase fanDb = new FanDatabase(getContext());
        final SocketDatabase socketDb = new SocketDatabase(getContext());

        Jump jump = new Jump(fragmentManager);
        switch (devType) {
            case R.string.title_lighting:
                bulbDb.deleteDevice(devIndex);
                jump.replaceDeviceFragment(R.integer.light_bulb_fragment);
                break;
            case R.string.title_fan:
                fanDb.deleteDevice(devIndex);
                jump.replaceDeviceFragment(R.integer.fan_fragment);
                break;
            case R.string.title_smart_plug:
                socketDb.deleteDevice(devIndex);
                jump.replaceDeviceFragment(R.integer.socket_fragment);
                break;
        }

        Toast.makeText(getContext(),  devName + " Deleted Successfully", Toast.LENGTH_SHORT).show();

        s.setLog("Device [" + getContext().getString(devType) + " at position[" + devIndex + "] deleted successfully");


    }

}
