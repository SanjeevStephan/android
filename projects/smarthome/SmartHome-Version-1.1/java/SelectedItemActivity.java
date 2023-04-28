package com.sanjeev.stephan.murmu.recyclerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.widget.EditText;
public class SelectedItemActivity extends AppCompatActivity {

    EditText editPosition,editDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        Intent intent = getIntent();
        String position = intent.getStringExtra("position");
        String device_name = intent.getStringExtra("device");
        editPosition = (EditText) findViewById(R.id.id_editText_position);
        editDevice = (EditText) findViewById(R.id.id_editText_device);
        editPosition.setText(position);
        editDevice.setText(device_name);

    }

}
