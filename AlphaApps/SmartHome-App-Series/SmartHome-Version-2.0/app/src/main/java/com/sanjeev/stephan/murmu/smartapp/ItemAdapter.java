package com.sanjeev.stephan.murmu.smartapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>
{
    ArrayList<ItemModel> arrayList ;
    public ItemAdapter(ArrayList<ItemModel> arrayList)
    {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_layout, viewGroup, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view,listener_here);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i)
    {
        ItemModel model = arrayList.get(i);
        itemViewHolder.deviceName_textView.setText(model.getDeviceName());
        itemViewHolder.ipAddress_textView.setText(model.getIpAddress());
        itemViewHolder.relayNum_textView.setText(model.getRelay_num());
        itemViewHolder.location_textView.setText(model.getLocation());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public OnItem_ClickListener listener_here;

    public interface OnItem_ClickListener
    {
        void onItem_click(int position);
        void onMoreOptionMenu_click(int position,String menuOption);
    }

    public void SetOnItem_ClickListener(OnItem_ClickListener listener)
    {
       this.listener_here = listener;
    }
}
