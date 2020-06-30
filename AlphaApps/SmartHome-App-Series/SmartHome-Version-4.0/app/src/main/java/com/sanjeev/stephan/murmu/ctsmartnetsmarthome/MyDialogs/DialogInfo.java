package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

public class DialogInfo extends DialogFragment
{
    String msg = " ";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            msg = bundle.getString(getString(R.string.key_dialog_msg));
        }
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          DialogInfo.this.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }

}
