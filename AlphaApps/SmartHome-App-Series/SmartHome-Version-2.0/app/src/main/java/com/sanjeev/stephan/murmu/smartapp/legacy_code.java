package com.sanjeev.stephan.murmu.smartapp;

public class legacy_code
{
    /*
  -------------------------------------------------------------------------------------------------
                                     MainActivity.java ==> getItemHere();
    -------------------------------------------------------------------------------------------------

        void getItemHere(int position)
    {

        Cursor cursor = deviceDB.getItemAtPosition(position);

        int no_data_available = 0;

       // if (deviceDB.numberOfRows() > no_data_available) {
            //if(cursor != null)
            //   {
        if(cursor != null && cursor.getCount() > 0)

            cursor.moveToFirst();
            do {
                device_name = cursor.getString(1); //position of device_name in the database
                ip_address = cursor.getString(2); //position of ip_address in the database.
                relay_num = cursor.getString(3);
                location = cursor.getString(5);
               // ItemModel model = new ItemModel(device_name, ip_address, relay_num); // pass the data to ItemModel
            //    arrayList.add(model);// add the model dataset to the arrayList.

                //   getLink();
                setToast("Inserting Item Into Database");
                setToast("Device Name : " + device_name);
                setToast("IP : " + ip_address);
                setToast("Relay Num :" + relay_num);
                setToast("Relay State : " + relayState);
                setToast("Location : " + location );


                 url = "http://" + ip_address + "/RELAY_" + relay_num + "=ON";
                String info = "Device [" + device_name + "] " + url;


                setToast(info);
                setUrl(url);
            }
            while (cursor.moveToNext());

            deviceDB.close();
        }

     */

    /*
    -------------------------------------------------------------------------------------------------
                                     MainActivity.java ==> addTaskDialog();
    -------------------------------------------------------------------------------------------------

     private void addTaskDialog()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.sub_layout_add_new_device, null);

        final EditText editDeviceName = (EditText) subView.findViewWithTag(R.id.id_subview_input_device_name);
        final EditText editIpAddress = (EditText) subView.findViewWithTag((R.id.id_subview_input_ip_address));
       // final Spinner spinner = (Spinner) subView.findViewWithTag(R.id.id_relay_spinner);

        String lists[] = {"Relay 1", "Relay 2", "Relay 3"};
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lists);
       // spinner.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Device");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("Add Device", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String deviceName = editDeviceName.getText().toString();
                String ipAddress  = editIpAddress.getText().toString();
                if (TextUtils.isEmpty(deviceName))
                {

                    setToast("Please Enter Device Name and IP Address");
                }
                else
                {
                    setToast("DeviceName : " + deviceName + " | " + "IP : " + ipAddress);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 setToast("Cancelled");
            }
        });

        builder.show();

    }

     */


}
