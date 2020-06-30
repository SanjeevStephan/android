package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyBottomSheets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

public class CustomAdapterDeviceInfoList extends ArrayAdapter
{
    String[] title;
    TextView textView;
    public CustomAdapterDeviceInfoList(@NonNull Context context, String[] titleList)
    {
        super(context,R.layout.item_layout_bottom_menu_list_adapter,titleList);
        this.title = titleList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.item_layout_bottom_menu_list_adapter,parent,false);

        textView = view.findViewById(R.id.id_bottom_menu_list_text_item);
        textView.setText(title[position]);

        return view;
    }
}
