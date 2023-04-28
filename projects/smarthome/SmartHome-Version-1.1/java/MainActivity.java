package com.sanjeev.stephan.murmu.recyclerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ExampleItem> mExampleItem;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static boolean state = true;
    static boolean relayOne_state = true,
            relayTwo_state = true,
            relayThree_state = true,
            relayFour_state = true,
            relayFive_state = true,
            relaySix_state = true,
            relaySeven_state = true,
            relayEight_state = true,
            relayNine_state = true,
            relayTen_state = true,
            relayEleven_state = true,
            relayTwelve_state = true;
    WebView webView;
    String ip[] = {
            "192.168.225.10",
            "192.168.225.11",
            "192.168.225.12",
            "192.168.225.13"
    };
    String rooms[] = {
            "Computer Light 1",
            "Computer Light 2",
            "Desktop Switch",
            "Mummy/'s Room Light",
            "Fan Switch",
            "Kitchen Light",
            "Bathroom Light",
            "Laptop Charger",
            "Monitor Switch"
    };
    String devices[] = {
            "Light Bulb",
            "FAN",

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.id_webview);


        createExampleList();
        buildRecyclerView();
       
    }
    public void createExampleList()
    {
        mExampleItem = new ArrayList<>();
        
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off,rooms[0],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off,rooms[1],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off, rooms[2],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off, rooms[3],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off,rooms[4],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off,rooms[5],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off, rooms[6],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off, rooms[7],devices[0]));
        mExampleItem.add(new ExampleItem(R.drawable.light_bulb_off,rooms[8],devices[0]));
    }

    public void buildRecyclerView()
    {


        //Look for the RecyclerView in activity_main.xml layout using their Reference ID.
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        //Create adapter passing the simple user datas
        mAdapter = new ExampleAdapter(mExampleItem);

        //set Layout Manager to position the items
        mRecyclerView.setLayoutManager(mLayoutManager);
        //Attach the Adapter to the recyclerView to populate items.
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
               chooseDevice(position);
            }

            @Override
            public void openSelectedItemClick(int position) {

                Intent intent = new Intent(MainActivity.this, SelectedItemActivity.class);
                intent.putExtra("position",position + "");
                intent.putExtra("device", rooms[position]);
                startActivity(intent);

            }
        });
    }
       public void changeItem(int position, String text, int image) {
        mExampleItem.get(position).changeText1(text);
        mExampleItem.get(position).changeImage(image);
        mAdapter.notifyItemChanged(position);
    }

    public void chooseDevice(int position)
    {

        switch(position)
        {
            case 0 : relayOne_state =  changeDeviceState(relayOne_state,position,ip[2],3); break;
            case 1 : relayTwo_state =  changeDeviceState(relayTwo_state,position,ip[2],1); break;
            case 2 : relayThree_state =  changeDeviceState(relayThree_state,position,ip[0],3); break;
            case 3 : relayFour_state =  changeDeviceState(relayFour_state,position,ip[0],2); break;
            case 4 : relayFive_state =  changeDeviceState(relayFive_state,position,ip[3],1); break;
            case 5 : relaySix_state =  changeDeviceState(relaySix_state,position,ip[0],1); break;
            case 6 : relaySeven_state =  changeDeviceState(relaySeven_state,position,ip[0],4); break;
            case 7 : relayEight_state =  changeDeviceState(relayEight_state,position,ip[2],2); break;
            case 8 : relayNine_state =  changeDeviceState(relayNine_state,position,ip[2],4); break;

        }
    }
    public boolean changeDeviceState(boolean device_state,int position,String ip_address,int relay_number)
    {
        String url;
        String http_plus_ip = "http://" + ip_address ;
        if(device_state == true)
        {
            url = http_plus_ip + "/RELAY_" + relay_number + "=" + "ON";
            setToast(url);
            openURL(url);
            changeItem(position,url,R.drawable.light_bulb_on);
            return false;
        }
        else
        {
            url = http_plus_ip + "/RELAY_" + relay_number + "=" + "OFF";
            setToast(url);
            openURL(url);
            changeItem(position,url,R.drawable.light_bulb_off);
            return true;
        }
    }
    void setToast(String text)
    {
        String tagName = "TAG";
        Log.i(tagName, text);
    }
    void openURL(String url)
    {
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }

}
