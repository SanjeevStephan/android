package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceMainActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.ArrayList;

public class DialogNewDeviceCategory extends DialogFragment
{
    private static final String TAG = "DialogNewDeviceCategory";
    private Show s = new Show(TAG);
    private View view;
    private int container  = R.layout.dialog_layout_new_device_category;      //view which will be visible to user
    private int recyclerID = R.id.id_recycler_container_new_devices;
    private int title      = R.string.select_device;
    private int numOfGrids = 2;                                   //number of column in single row!

    private ArrayList<ModelNewDeviceCategory> arrayList = new ArrayList<>();
    private Dialog dialog;
    private InterfaceMainActivity listener = null;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        if(getContext() != null)
        {
            dialog = new Dialog(getContext(),R.style.CustomTransparentDialog);
            view = LayoutInflater.from(getContext()).inflate(container, null);
            dialog.setTitle(title);
            dialog.setContentView(view);
            dialog.show();
            MyNewDeviceArrayList.setNewDeviceArrayList(getContext(),arrayList);
            setRecyclerView();
        }
        return dialog;
    }
    private void setRecyclerView()
    {
        RecyclerView recyclerView = view.findViewById(recyclerID);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),numOfGrids);
        AdapterNewDeviceCategory adapter = new AdapterNewDeviceCategory(arrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setDeviceCardItem_clickListener(new AdapterNewDeviceCategory.onNewDevice_ClickListener() {
            @Override
            public void onCardItemClickListener(int position) {
                ModelNewDeviceCategory model = arrayList.get(position);
                s.setLog("[" +  position +"] "+ model.getDeviceTitle());
                listener.onDeviceItemSelectedListener(model.getDeviceImage(),model.getDeviceTitle());
                dialog.dismiss();
            }
        });

    }

    /**
     * notify the Activity about the changes,Activity which have implemented the necessary interfaces
     * @param context getContext();
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (InterfaceMainActivity)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity() + " must implement Interface AddActivity");
        }
    }
}
