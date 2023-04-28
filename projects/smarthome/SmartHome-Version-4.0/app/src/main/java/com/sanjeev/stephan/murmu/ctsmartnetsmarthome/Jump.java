package com.sanjeev.stephan.murmu.ctsmartnet.smarthome;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.CCTVFragment;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.FanFragment;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.LightBulbFragment;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.PlugSocketFragment;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.VoidBlankFragment;

/**
 * Display the Fragment to its necessary layout container by passing the 'fragment_name(int)"
 * @author Sanjeev Stephan Murmu
 * @since 18th Oct 2019
 */
public class Jump {
    private static final String TAG = "Jump";
    Show s = new Show(TAG);

    private int devices_container = 0;
    private int device_light_bulb_container = R.id.id_devices_light_bulb_fragment_container;
    private int device_fan_container = R.id.id_devices_fan_fragment_container;
    private int device_socket_container = R.id.id_devices_socket_fragment_container;
    private int device_cctv_container = R.id.id_device_cctv_fragment_container;
    private String whatIsReplaced = " ";
    private boolean isReplaced = false;
    private Fragment fragment = new Fragment(); //initialized so that will not throw null exception!
    private FragmentManager getFragmentManager;

    public Jump(FragmentManager getFragmentManager) {
        this.getFragmentManager = getFragmentManager;
    }

    public boolean replaceDeviceFragment(int fragmentName) {
        FragmentManager fm = getFragmentManager;
        FragmentTransaction ft = fm.beginTransaction();

        switch (fragmentName) {
            case R.integer.light_bulb_fragment:
                this.fragment = new LightBulbFragment();
                this.devices_container = device_light_bulb_container;
                this.whatIsReplaced = "Light Bulb";
                isReplaced = true;
                break;
            case R.integer.fan_fragment:
                this.fragment = new FanFragment();
                this.devices_container = device_fan_container;
                whatIsReplaced = "Fan";
                isReplaced = true;
                break;
            case R.integer.socket_fragment:
                this.fragment = new PlugSocketFragment();
                this.devices_container = device_socket_container;
                this.whatIsReplaced = "Plug Socket";
                isReplaced = true;
                break;
            case R.integer.cctv_fragment:
                this.fragment = new CCTVFragment();
                this.devices_container = device_cctv_container;
                whatIsReplaced = "CCTV";
                isReplaced = true;
            case R.integer.no_fragment:
                isReplaced = false;
            default:
                s.setLog("No Fragment Received!");
                isReplaced = false;
        }
        ft.replace(devices_container, fragment);
        ft.commit();
        confirmReplaced();
        return isReplaced;
    }


    public boolean replaceVoidFragment(FragmentManager fragmentManager,int fragmentName) {
        FragmentManager fm = fragmentManager;
        FragmentTransaction ft = fm.beginTransaction();

        switch (fragmentName) {
            case R.integer.light_bulb_fragment:
                this.fragment = new VoidBlankFragment();
                this.devices_container = device_light_bulb_container;
                this.whatIsReplaced = "Light Bulb";
                this.isReplaced = true;
                break;
            case R.integer.fan_fragment:
                this.fragment = new VoidBlankFragment();
                this.devices_container = device_fan_container;
                whatIsReplaced = "Fan";
                this.isReplaced = true;
                break;
            case R.integer.socket_fragment:
                this.fragment = new VoidBlankFragment();
                this.devices_container = device_socket_container;
                this.whatIsReplaced = "Plug Socket";
                this.isReplaced = true;
                break;
            case R.integer.cctv_fragment:
                this.fragment = new VoidBlankFragment();
                this.devices_container = device_cctv_container;
                whatIsReplaced = "CCTV";
                this.isReplaced = true;
                break;
            default:
                s.setLog("No Fragment Received!");
                this.isReplaced = false;
        }
        ft.replace(devices_container, fragment);
        ft.commit();
        confirmReplaced();
        return isReplaced;
    }
    void confirmReplaced() {
        s.setLog(whatIsReplaced + " Fragment is Replaced");
    }

}
