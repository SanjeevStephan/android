package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceAddActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

public class DialogLocationList extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.select_location)
                .setItems(R.array.room_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        String[] roomLocations = getResources().getStringArray(R.array.room_list);

                        listener.onLocationPositionChangeListener(roomLocations[position],position);
                    }
                });
        AlertDialog dialog = builder.create();
        return dialog;
    }

    //Use this instance of the interface to deliver the action events
    InterfaceAddActivity listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Verify that the host activity implement the callback interface

        try {
            //instantiate the InterfaceDialogs so that we can send events to the host
            listener = (InterfaceAddActivity)getActivity();
        } catch (ClassCastException e) {
            //The activity doesn't implement the interface,throw exception
            throw new ClassCastException(getArguments().toString() + " must implement InterfaceDialogs interface");
        }
    }


}
