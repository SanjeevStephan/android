package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceAddActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

public class InputDialog extends DialogFragment
{
    private int dialog_title = 0 ;
    private int input_hint = 0;
    private String input_text = " ";

    InterfaceAddActivity listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            dialog_title = bundle.getInt(getString(R.string.key_dialog_title));
            input_text   = bundle.getString(getString(R.string.key_input));
            input_hint   = bundle.getInt(getString(R.string.key_hint));

            if(input_text == getString(R.string.empty)) { input_text = "";}
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(dialog_title));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout_input, null);
        final EditText inputField = view.findViewById(R.id.id_dialog_device_input_text);
        inputField.setHint(getString(input_hint));
        inputField.setText(input_text);
        builder.setView(view)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onReceivedInputListener(inputField.getText().toString().trim(),input_hint);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        InputDialog.this.dismiss();
                    }
                });



        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try
        {
            listener = (InterfaceAddActivity)getActivity();
        } catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString() + " must implement InterfaceAddActivity");
        }

    }
}
