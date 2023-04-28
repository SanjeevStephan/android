package com.sanjeev.stephan.murmu.smarthomecontrol;
/*
  Developed by Sanjeev Stephan Murmu
  Date : 6th September 2019

  This SmartHome Control App needs to be connected to WiFi in order to work.
  Because this app is @hardcoded,developed for the purpose of testing Smarthome Functions
   SSID : 'SmartHome' | Password : 'smarthome123'

  Once this App is connected to the Desired WiFi Network,say 'SmartHome'
  it will then makes HTTP Request to the Client NodeMCU(ESP8266) Devices
  connected to this network.The Request comprises an IP Address of the Client Device and
  the Request to trigger certain task such as turing the bulb ON/OFF.
  The Request may something looks like this.
        http://192.168.225.10/RELAY_1_ON
        http://192.168.225.10/RELAY_1_OFF
  For Simplicity,all the ESP8266 Device have the Same Request Code
  but Static IP Address may vary from board to board.
      say RELAY_1_ON,RELAY_2_ON
  because this help in making request to board without having to worry much about
  which appliances is connected to Relay 1 or Relay 2.

  please check the Arduino Sketch for the NodeMCU/ESP8266 Static IP.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Defining Switch object variables.
    Switch relay_switch_1,relay_switch_2,relay_switch_3,relay_switch_4,relay_switch_5,
            relay_switch_6,relay_switch_7,relay_switch_8,relay_switch_9,relay_switch_10;
    ImageView img_1,img_2,img_3,img_4,img_5,img_6,img_7,img_8,img_9,img_10;
    WebView webView;

    String ip[] = {
            "http://192.168.225.10",  // IP Address for the main control board
            "http://192.168.225.11",  //IP Address for the LCD Status Display
            "http://192.168.225.12", //IP Address for the Three Socket and One Bulb Control Board
            "http://192.168.225.13", //IP Address for the Fan
    };


    String state_on = "ON";
    String state_off = "OFF";

    NetworkInfo wifiCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.id_home_webview);

        img_1 = (ImageView) findViewById(R.id.id_image_one);
        img_2 = (ImageView) findViewById(R.id.id_image_two);
        img_3 = (ImageView) findViewById(R.id.id_image_three);
        img_4 = (ImageView) findViewById(R.id.id_image_four);
        img_5 = (ImageView) findViewById(R.id.id_image_five);
        img_6 = (ImageView) findViewById(R.id.id_image_six);
        img_7 = (ImageView) findViewById(R.id.id_image_seven);
        img_8 = (ImageView) findViewById(R.id.id_image_eight);
        img_9 = (ImageView) findViewById(R.id.id_image_nine);
        img_10 = (ImageView) findViewById(R.id.id_image_ten);

        relay_switch_1 = (Switch) findViewById(R.id.id_switch_one);
        relay_switch_2 = (Switch) findViewById(R.id.id_switch_two);
        relay_switch_3 = (Switch) findViewById(R.id.id_switch_three);
        relay_switch_4 = (Switch) findViewById(R.id.id_switch_four);
        relay_switch_5 = (Switch) findViewById(R.id.id_switch_five);
        relay_switch_6 = (Switch) findViewById(R.id.id_switch_six);
        relay_switch_7 = (Switch) findViewById(R.id.id_switch_seven);
        relay_switch_8 = (Switch) findViewById(R.id.id_switch_eight);
        relay_switch_9 = (Switch) findViewById(R.id.id_switch_nine);
        relay_switch_10 = (Switch)findViewById(R.id.id_switch_ten);


        /*
           Fetching Connectivity Services using ConnectivityManager Class object
         */
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiCheck = connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI);

        OnSwitchChangeListener();

    }
    void OnSwitchChangeListener()
    {
        Switch_1();
        Switch_2();
        Switch_3();
        Switch_4();
        Switch_5();
        Switch_6();
        Switch_7();
        Switch_8();
        Switch_9();
        Switch_10();
    }
    void Switch_1()
    {
        relay_switch_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(checkWifi() == true)
                {
                    if(relay_switch_1.isChecked())
                    {
                        setToast(getResources().getString(R.string.relay_one_on_toast));
                        setUrl(ip[2] + setRelay_RequestCode(1,"ON")); //sends http://192.168.225.27/BULB=ON
                        img_1.setImageResource(R.drawable.light_bulb_on);  //set BULB ON image.
                    }
                    else
                    {

                        setToast(getString(R.string.relay_one_off_toast));
                        //setUrl(ip3 + "/RELAY_1=OFF");
                        setUrl(ip[2] + setRelay_RequestCode(1, "OFF")); //sends http://192.168.225.27/BULB=OFF
                        img_1.setImageResource(R.drawable.light_bulb_off); //set BULB OFF image
                    }
                }
                else
                {
                    relay_switch_1.setChecked(false); //if wifi not connected,keep switch unchecked.
                    setToast(getString(R.string.wifi_error));
                }
            }
        });



    }

    void Switch_2()
    {

        relay_switch_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi())
                {
                    if(relay_switch_2.isChecked())
                    {
                        setToast(getString(R.string.relay_two_on_toast));
                        setUrl(ip[2] + setRelay_RequestCode(3,state_on));
                        img_2.setImageResource(R.drawable.light_bulb_on);
                    }
                    else
                    {

                        setToast(getString(R.string.relay_two_off_toast));
                        setUrl(ip[2] + setRelay_RequestCode(3,state_off));
                        img_2.setImageResource(R.drawable.light_bulb_off);

                    }
                }
                else
                {
                    relay_switch_2.setChecked(false);
                    setToast(getString(R.string.wifi_error));
                }

            }
        });
    }
    void Switch_3()
    {
        relay_switch_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi())
                {
                    if (relay_switch_3.isChecked()) {
                        setToast(getString(R.string.relay_three_on_toast));
                        setUrl(ip[0] + setRelay_RequestCode(3, state_on)); //sends http://192.168.225.10/RELAY_3=ON
                        img_3.setImageResource(R.drawable.light_bulb_on);
                    } else {
                        setToast(getString(R.string.relay_three_off_toast));
                        setUrl(ip[0] + setRelay_RequestCode(3, state_off));
                        img_3.setImageResource(R.drawable.light_bulb_off);
                    }
                } else
                {
                    relay_switch_3.setChecked(false);
                    setToast(getString(R.string.wifi_error));
                }

            }
        });
    }
    void Switch_4()
    {

        relay_switch_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi()) //if(checkWiFi() == true) can be simplified as if(checkWifi());
                {
                    if (relay_switch_4.isChecked())
                    {

                        setToast(getString(R.string.relay_four_on_toast));
                        setUrl(ip[3] + setRelay_RequestCode(1,state_on));
                        img_4.setImageResource(R.drawable.light_bulb_on);

                    } else {

                        setToast(getString(R.string.relay_four_off_toast));
                        setUrl(ip[3] + setRelay_RequestCode(1,state_off));
                        img_4.setImageResource(R.drawable.light_bulb_off);

                    }
                }
                else
                {
                    relay_switch_4.setChecked(false);
                    setToast(getString(R.string.wifi_error));
                }
            }
        });

    }
    void Switch_5()
    {

        relay_switch_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi())
                {
                    if(relay_switch_5.isChecked())
                    {
                        setToast(getString(R.string.relay_five_on_toast));
                        setUrl(ip[0] + setRelay_RequestCode(1,state_on));
                        img_5.setImageResource(R.drawable.light_bulb_on);

                    }
                    else
                    {
                        setToast(getString(R.string.relay_five_on_toast));
                        setUrl(ip[0] + setRelay_RequestCode(1,state_off));
                        img_5.setImageResource(R.drawable.light_bulb_off);
                    }
                }
                else
                {
                    relay_switch_5.setChecked(false);
                    setToast(getString(R.string.wifi_error));
                }

            }
        });

    }
    void Switch_6()
    {
        relay_switch_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi())
                {
                    if (relay_switch_6.isChecked())
                    {
                        setToast(getString(R.string.relay_six_on_toast));
                        setUrl(ip[0] + setRelay_RequestCode(4,state_off));
                        img_6.setImageResource(R.drawable.light_bulb_on); //set BULB ON image
                    }
                    else
                    {
                        setToast(getString(R.string.relay_six_off_toast));
                        setUrl(ip[0] + setRelay_RequestCode(4,state_on));
                        img_6.setImageResource(R.drawable.light_bulb_off); //set BULB OFF images
                    }
                }
                else
                {
                    relay_switch_6.setChecked(false); //if not connected,keep switch uncheck!
                    setToast(getString(R.string.wifi_error));//display 'WiFi not connected toast message
                }
            }
        });
    }
    void Switch_7()
    {
        relay_switch_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi())
                {
                    if(relay_switch_7.isChecked())
                    {
                        setToast(getString(R.string.relay_seven_on_toast));
                        setUrl(ip[2] + setRelay_RequestCode(2,state_on));
                        img_7.setImageResource(R.drawable.light_bulb_on);
                    }
                    else
                    {
                        setToast(getString(R.string.relay_seven_off_toast));
                        setUrl(ip[2] + setRelay_RequestCode(2,state_off));
                        img_7.setImageResource(R.drawable.light_bulb_off);
                    }
                }
                else
                {
                    relay_switch_7.setChecked(false);
                    setToast(getString(R.string.wifi_error));
                }
            }
        });
    }
    void Switch_8()
    {
        relay_switch_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi())
                {
                    if(relay_switch_8.isChecked())
                    {
                        setToast(getString(R.string.relay_eight_on_toast));
                        setUrl(ip[2] + setRelay_RequestCode(4,state_on));
                        img_8.setImageResource(R.drawable.light_bulb_on);
                    }
                    else
                    {
                        setToast(getString(R.string.relay_eight_off_toast));
                        setUrl(ip[2] + setRelay_RequestCode(4,state_off));
                        img_8.setImageResource(R.drawable.light_bulb_off);
                    }
                }
                else
                {
                    relay_switch_8.setChecked(false);
                    setToast(getString(R.string.wifi_error));
                }
            }
        });
    }
    void Switch_9()
    {

        relay_switch_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkWifi())
                {
                    if(relay_switch_9.isChecked())
                    {
                        setToast(getString(R.string.relay_eight_on_toast));
                        setUrl(ip[0] + setRelay_RequestCode(2,state_off));
                        img_9.setImageResource(R.drawable.light_bulb_on);
                    }
                    else
                    {
                        setToast(getString(R.string.relay_eight_off_toast));
                        setUrl(ip[0] + setRelay_RequestCode(2,state_on));
                        img_9.setImageResource(R.drawable.light_bulb_off);
                    }
                }
                else
                {
                    relay_switch_9.setChecked(false);
                    setToast(getString(R.string.wifi_error));
                }
            }
        });
    }
    void Switch_10()
    {
        relay_switch_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(checkWifi())
                {
                    relay_switch_10.setChecked(false);
                  /*  if(relay_switch_10.isChecked())
                    {
                        setToast("Outside Light ON!");
                        setUrl(ip + "/RELAY_1=ON"); //send http://192.168.225.27/LAST_RELAY=ON
                        img_10.setImageResource(R.drawable.light_bulb_on);
                    }
                    else
                    {
                        setToast("Lights have not been setup yet!");
                        setUrl(ip + "/RELAY_1=OFF");  //send http://192.168.225.27/LAST_RELAY=OFF
                        img_10.setImageResource(R.drawable.light_bulb_off);
                    }*/
                }
                else
                {
                    relay_switch_10.setChecked(false);
                    setToast(getString(R.string.not_setup_yet));
                }
            }
        });
    }

    String setRelay_RequestCode(int relay_position,String state)
    {
        String requestCode = "/RELAY_" + relay_position + "=" + state ;
        return requestCode;
    }
    void setToast(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
    void setUrl(String url_link)
    {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setSupportZoom(false);
        webView.loadUrl(url_link);
        webView.setWebViewClient(new WebViewClient());

    }
    boolean checkWifi()
    {
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled())  //Wifi Adapter is ON
        {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo.getNetworkId() == -1)
            {
                return false; //Not connected to any access point
            }
            return true; // Connected to an access point
        }
        else
        {
            return false; //Wifi Adapter is OFF;
        }
    }
}
