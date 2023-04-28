package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.ArrayList;


public class AdapterSampleDevice extends RecyclerView.Adapter<ViewHolderSampleDevice> {

    private static final String TAG = "AdapterSampleDevice";
    Show s = new Show(TAG);
    private ArrayList<ModelSampleDevice> allDeviceList = new ArrayList<>();
    private FragmentManager fm;
    private Context fragmentContext;
    private int deviceOn_img = 0, deviceOff_img = 0;
    private int deviceType = 0;

    /**
     * <code> ArrayList<ModelSampleDevice> </code> gets updated
     *
     * @param allDeviceList list of arrays structured to model the device such as name,images and so on!.
     */
    public AdapterSampleDevice(ArrayList<ModelSampleDevice> allDeviceList) {
        this.allDeviceList = allDeviceList;
    }

    public void setDeviceOn_img(int deviceOn_img) {
        this.deviceOn_img = deviceOn_img;
    }

    public void setDeviceOff_img(int deviceOff_img) {
        this.deviceOff_img = deviceOff_img;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public void setFragmentMgr(FragmentManager getFragmentMgr) {
        this.fm = getFragmentMgr;
    }

    public void setFragmentContext(Context fragmentContext) {
        this.fragmentContext = fragmentContext;
    }

    @NonNull
    @Override
    public ViewHolderSampleDevice onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_fragment_devices, viewGroup, false);
        ViewHolderSampleDevice viewholder = new ViewHolderSampleDevice(view, deviceType, allDeviceList, deviceItemClickListener);
        viewholder.setDeviceON_Img(deviceOn_img);
        viewholder.setDeviceOFF_Img(deviceOff_img);
        viewholder.setFragmentManager(fm);
        viewholder.setFragmentContext(fragmentContext);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSampleDevice viewHolderAllBulb, int i) {
        ModelSampleDevice model = new ModelSampleDevice();
        model = allDeviceList.get(i);
        viewHolderAllBulb.deviceName.setText(model.getDeviceName());
        viewHolderAllBulb.deviceStatus.setText(model.getDeviceStatus());
        viewHolderAllBulb.statusDateTime.setText(model.getStatusDataTime());
        viewHolderAllBulb.deviceImage.setImageResource(model.getDeviceImage());
    }

    @Override
    public int getItemCount() {
        return allDeviceList.size();
    }

    public void setDeviceItemClickListener(onDeviceItemClickListener deviceItemClickListener) {
        this.deviceItemClickListener = deviceItemClickListener;
    }

    public onDeviceItemClickListener deviceItemClickListener;

    public interface onDeviceItemClickListener {
        void onSwitchStateChangeListener(int position, boolean isChecked);
        //void onDeviceImgClickListener(int position);
    }

}
