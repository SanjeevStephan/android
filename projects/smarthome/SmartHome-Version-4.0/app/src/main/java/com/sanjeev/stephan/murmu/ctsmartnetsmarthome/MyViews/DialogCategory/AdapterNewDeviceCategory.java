package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

import java.util.ArrayList;

public class AdapterNewDeviceCategory extends RecyclerView.Adapter<ViewHolderNewDeviceCategory>
{
    private ArrayList<ModelNewDeviceCategory> arrayList = new ArrayList<>();

    public AdapterNewDeviceCategory(ArrayList<ModelNewDeviceCategory> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolderNewDeviceCategory onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_layout_dialog_fragment_new_device, viewGroup, false);
        ViewHolderNewDeviceCategory viewHolder = new ViewHolderNewDeviceCategory(view,deviceClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNewDeviceCategory viewHolderNewDeviceCategory, int i) {
        ModelNewDeviceCategory model = new ModelNewDeviceCategory();
        model = arrayList.get(i);
        viewHolderNewDeviceCategory.deviceImage.setImageResource(model.getDeviceImage());
        viewHolderNewDeviceCategory.deviceTitle.setText(model.getDeviceTitle());
        viewHolderNewDeviceCategory.totalDevices.setText(model.getTotalDevices());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setDeviceCardItem_clickListener(onNewDevice_ClickListener deviceCardItem_clickListener) {
        this.deviceClickListener = deviceCardItem_clickListener;
    }

    public onNewDevice_ClickListener deviceClickListener;

    public interface onNewDevice_ClickListener
    {
        void onCardItemClickListener(int position);
    }
}
