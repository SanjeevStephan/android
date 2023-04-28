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

public class DialogRelayList extends DialogFragment
{

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.select_relay)
                .setItems(R.array.relay_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String[] relayLists = getResources().getStringArray(R.array.relay_list);

                        listener.onRelayItemSelectedListener(i);

                    }
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }

    /**
     * Use this instance of the interface to deliver action events
     */
    private InterfaceAddActivity listener;
    /**
     * Override the Fragment.onAttach method to instantiate the InterfaceDialogs
     * @param context this.context or getContext()
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Verify that the host activity implements the callback interface.

        try {
            listener = (InterfaceAddActivity) getActivity();
        } catch (ClassCastException e)
        {
            //the activity doesn't implement the interface,throw exception.
           throw new ClassCastException(getActivity() + " must implement the InterfaceDialog listener");
        }
    }
}
