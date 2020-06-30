package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Jump;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

/**
 * Tabbed Activity's Fragment Number 1
 * class used in {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.ui.main.SectionsPagerAdapter}
 * also see {@link CCTVFragment}
 */
public class CCTVFragmentContainer extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cctv_container,container,false);
        Jump jump = new Jump(getFragmentManager());
        jump.replaceDeviceFragment(R.integer.cctv_fragment);
        return view;
    }
}
