package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

public class FirebaseActivity extends AppCompatActivity {

    EditText devName,ipAddress,relayNum;
    Button btnSave;
    Switch devState;

    DatabaseReference mRef;
    FirebaseDeviceModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        devName = findViewById(R.id.id_device_name_edit_firebase);
        ipAddress = findViewById(R.id.id_ip_address_edit_firebase);
        relayNum = findViewById(R.id.id_relay_edit_firebase);
        devState = findViewById(R.id.id_device_state_firebase);

        btnSave = findViewById(R.id.id_save_firebase);

        model = new FirebaseDeviceModel();
        mRef = FirebaseDatabase.getInstance().getReference().child("DeviceName");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int relay = Integer.parseInt(relayNum.getText().toString().trim());
                String name = devName.getText().toString().trim();
                String ip   = ipAddress.getText().toString().trim();

                model.setDeviceName(name);
                model.setDeviceIp(ip);
                model.setRelayNum(relay);

                mRef.setValue(model);

                Toast.makeText(FirebaseActivity.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });


        devState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    model.setDeviceState(1);
                }
                else
                {
                    model.setDeviceState(0);
                }
                mRef.setValue(model);

            }
        });
    }
}
