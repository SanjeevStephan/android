package com.sanjeev.stephan.murmu.ctsmartnet.smarthome;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV.ModelCCTV;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.CCTVDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFirebase.FirebaseActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceMainActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyViews.DialogCategory.DialogNewDeviceCategory;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.ui.main.SectionsPagerAdapter;

/**
 * Tabbed Activity which Serves as a container for Device|CCTV fragments
 *
 * @author Sanjeev Stephan Murmu
 * @since 19 Oct 2019
 */
public class MainActivity extends AppCompatActivity implements InterfaceMainActivity {

    //Classes
    private static final String TAG = "MainActivity";
    private Jump jump;
    private Show s = new Show(TAG);
    private PopupMenu pop;
    private ViewPager viewPager;
    //Connectivity
    WifiManager wifiManager;
    //Variables
    private boolean wifi = false;
    private int menuList = 0, NUM_OF_FRAGMENT = 2, newDeviceImage = 0;
    private String newDeviceTitle = " ";
    private boolean isNetworkAvailable = false;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(NUM_OF_FRAGMENT); //TODO : use this code to prevent recycler-view from overpopulating|refreshing!

        Toolbar toolbar = findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace your favourites items here!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                startActivity(new Intent(MainActivity.this, FirebaseActivity.class));

            }
        });

        s.setContext(this);
        isInternetOn();
    }

    /**
     * Inflates the ActionBar Menu with necessary menu items.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        switch (viewPager.getCurrentItem()) {
            case 0:
                menuList = R.menu.menu_main;
                break;
            case 1:
                menuList = R.menu.menu_cctv;
                break;
        }
        getMenuInflater().inflate(menuList, menu);

        isWifiOn(menu.findItem(R.id.id_menu_wifi)); //get wifi action menu item | use as a wifi indicator
        return true;
    }

    /**
     * Trigger this method when menu item from action-bar is clicked
     *
     * @param item menu item which can provides getItemId().
     * @return boolean value to confirm that menu item is clicked!.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         //TODO : check the wifi status and change the wifi icon accordingly.

        switch (item.getItemId()) {
            case R.id.id_menu_add_device:
                DialogNewDeviceCategory dialogNewDeviceCategory = new DialogNewDeviceCategory();
                dialogNewDeviceCategory.show(getSupportFragmentManager(), getString(R.string.key_device));
                s.setLog("Add New Device Clicked");
                break;
            case R.id.id_menu_refresh:
                jump = new Jump(getSupportFragmentManager());
                jump.replaceDeviceFragment(R.integer.light_bulb_fragment);
                jump.replaceDeviceFragment(R.integer.fan_fragment);
                jump.replaceDeviceFragment(R.integer.socket_fragment);
                jump.replaceDeviceFragment(R.integer.no_fragment);
                break;
            case R.id.id_menu_edit:
                s.setLog("Edit Clicked");
                break;
            case R.id.id_menu_wifi:
                if(wifi)
                {
                      wifiManager.setWifiEnabled(false);
                      item.setIcon(R.drawable.ic_wifi_black_24dp);
                      wifi = false;
                      s.setLog("WIFI Is Turned OFF");
                }
                else
                {
                    wifiManager.setWifiEnabled(true);
                    item.setIcon(R.drawable.ic_wifi_white_24dp);
                    wifi = true;
                    s.setLog("WIFI Is Turned ON");
                }
                break;
            case R.id.id_menu_delete:
                s.setLog("Delete Clicked");
                break;
        }
        return true;
    }

    /**
     * Opens {@link AddActivity} when 'add' button is clicked!
     */
    private void openAddDeviceActivity() {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra(getString(R.string.key_add_device_image), newDeviceImage);
        intent.putExtra(getString(R.string.key_add_device), newDeviceTitle);
        startActivity(intent);
    }

    /**
     * Add New Device Item Selected From Square Device Category.SEE_MORE {@link DialogNewDeviceCategory}
     *
     * @param deviceImage sends the image of the device to the {@link AddActivity}
     * @param deviceTitle sends the title of the device to the {@link AddActivity}
     */
    @Override
    public void onDeviceItemSelectedListener(int deviceImage, String deviceTitle) {
        this.newDeviceTitle = deviceTitle;
        this.newDeviceImage = deviceImage;
        openAddDeviceActivity(); //TODO : this method starts new activity
    }

    @Override
    public void onFragmentSwippedListener(int position) {
        s.setLog("Position is " + position);
    }

    /**
     * Takes necessary actions when PopupMenu triggered
     * SEE_MORE {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.CCTVFragment}
     *
     * @param itemPosition position of the cctv camera item
     * @param modelCCTV    Data Model Structures for the CCTV item SEE_MORE:{@link ModelCCTV}
     * @param menu         which popup menu item is triggered.such as (fullscreen,edit,delete). SEE_MORE {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV.ViewHolderCCTV}
     * @param menuId       id of the menu item
     * @param fm           fragmentManager from the {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.CCTVFragment}
     */
    @Override
    public void onPopupMenuItemSelectedListener(int itemPosition, ModelCCTV modelCCTV, String menu, int menuId, FragmentManager fm) {
        //TODO : Retrieve the CCTV Camera info before making any changes to it and assign it to the String and int variables.
        final int cameraPort = modelCCTV.getCameraPort();
        final String cameraIp = modelCCTV.getCameraIp();
        final String cameraLocation = modelCCTV.getCameraLocation();
        final String cameraType = getString(R.string.title_cctv);
        final String ipAttributes = modelCCTV.getIpAttributes();

        final String link = "http://" + cameraIp + ":" + cameraPort + "/" + ipAttributes;
        //                   http://192.168.225.12:8080/browserfs.html
        jump = new Jump(getSupportFragmentManager());

        //TODO : show current CCTV item information.
        s.setLog("--------------[ CCTV Camera (" + itemPosition + ") ]-----------------");
        s.setMethod("onPopupMenuItemSelectedListener()");
        s.setLog("Index : " + itemPosition);
        s.setLog("MENU : " + menu);
        s.setLog("CAMERA IP :" + cameraIp);
        s.setLog("PORT : " + cameraPort);
        s.setLog("Additional Attributes : " + ipAttributes);
        s.setLog("__________________________________");
        s.setLog("LINK : " + link);

        Intent intent = null;

        switch (menuId) {
            case R.id.id_view_cctv_menu_fullscreen:
                intent = new Intent(MainActivity.this, FullscreenCCTVActivity.class);
                intent.putExtra(getString(R.string.key_link), link);
                startActivity(intent);
                break;
            case R.id.id_view_cctv_menu_edit:
                //TODO : Trigger this case,when cctv item's popup menu 'edit' is clicked.<br/>Sends CCTV item info to EditActivity.
                intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(getString(R.string.key_device_index), itemPosition);
                intent.putExtra(getString(R.string.key_ip), cameraIp);
                intent.putExtra(getString(R.string.key_device_name), ipAttributes);
                intent.putExtra(getString(R.string.key_relay_or_port), cameraPort);
                intent.putExtra(getString(R.string.key_device_type), cameraType);
                intent.putExtra(getString(R.string.key_location), cameraLocation);
                startActivity(intent);
                break;
            case R.id.id_view_cctv_menu_delete:
                //TODO : Trigger this case,when cctv item's popup menu 'delete' is clicked.
                itemPosition = itemPosition + 1;
                CCTVDatabase cctvDb = new CCTVDatabase(getApplicationContext());
                cctvDb.deleteDevice(itemPosition);
                jump.replaceDeviceFragment(R.integer.cctv_fragment); //TODO : after an item is deleted replace the container with 'cctv_fragment'.Refresh the fragments
                s.setLog("CCTV @position : " + itemPosition);
                break;
        }
    }

    private boolean isInternetOn() {
        ConnectivityManager cmgr = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        if (
                cmgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                cmgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                cmgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                cmgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED
        )
        {
            isNetworkAvailable = true;
            s.setToast("Operating Over Internet");
            s.setLog("CONNECTED TO INTERNET");
        }
        else if (cmgr.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTING ||
                   cmgr.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED)
        {
            isNetworkAvailable = false;
            s.setToast("Please Connect to Internet");
            s.setLog("NOT CONNECTED TO INTERNET");
        }

        return isNetworkAvailable;
    }

    private void isWifiOn(MenuItem item)
    {
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled())
        {
            item.setIcon(R.drawable.ic_wifi_white_24dp);
            wifi = true;
        }
        else
        {
            item.setIcon(R.drawable.ic_wifi_black_24dp);
            wifi = false;
        }

       // return wifi;
    }

}