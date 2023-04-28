package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.SampleDevice;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyBottomSheets.BottomDeviceEditDelete;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.ArrayList;


public class ViewHolderSampleDevice extends RecyclerView.ViewHolder
{

    private static final String TAG = "ViewHolderSampleDevice";
    private Show s = new Show(TAG);
    TextView deviceName,deviceStatus,statusDateTime;
    ImageView deviceImage,deviceEditImage;
    WebView webView;
    Switch deviceSwitch;
    private int deviceON_img  =  0; //R.mipmap.ic_device_light_bulb_on_round;
    private int deviceOFF_img =  0; //R.mipmap.ic_device_light_bulb_off_round;
    private int countLongPress = 0;
    private FragmentManager fm;
    private Context fragmentContext;
    public void setFragmentManager(FragmentManager fm) {
        this.fm = fm;
    }

    public void setFragmentContext(Context fragmentContext) {
        this.fragmentContext = fragmentContext;
    }

    /**
     *
     * @param itemView helps to find IDs using the View!
     * @param listener listens to event
     */
    public ViewHolderSampleDevice(@NonNull final View itemView, final int deviceType, final ArrayList<ModelSampleDevice> arrayList, final AdapterSampleDevice.onDeviceItemClickListener listener)
    {
        super(itemView);
        deviceName = itemView.findViewById(R.id.id_device_item_name);
        deviceStatus = itemView.findViewById(R.id.id_device_item_status);
        statusDateTime = itemView.findViewById(R.id.id_device_item_status_data_time);
        deviceImage = itemView.findViewById(R.id.id_device_item_image);
        deviceSwitch = itemView.findViewById(R.id.id_device_switch);
        webView      = itemView.findViewById(R.id.id_device_item_web_view);

        deviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(listener != null)
                {
                    int position = getAdapterPosition();
                   // model = arrayList.get(position);
                    if(position != RecyclerView.NO_POSITION)
                    {
                        try { listener.onSwitchStateChangeListener(position,isChecked); } catch (Exception e) {
                            e.printStackTrace();
                            s.setLog(" Something wrong with this listener");
                        }

                        s.setLog("[".concat(position+"]").concat(" is ").concat(isChecked+""));

                        String state = "Device Is ";
                       if(isChecked)
                       {
                           deviceStatus.setText(state.concat("ON"));
                          deviceImage.setImageResource(deviceON_img);
                       }
                       else{
                           deviceStatus.setText(state.concat("OFF"));
                           deviceImage.setImageResource(deviceOFF_img);
                       }

                    }
                }
            }
        });

        deviceImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(listener !=null)
                {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        //listener.onDeviceImgClickListener(position);

                        BottomDeviceEditDelete deviceInfo = new BottomDeviceEditDelete(v.getContext());
                        deviceInfo.setDeviceTypeInteger(deviceType);
                        deviceInfo.setArrayList(arrayList);
                        deviceInfo.setPosition(position);
                        deviceInfo.setFragmentManager(fm);
                        deviceInfo.setFragentContext(fragmentContext);
                        deviceInfo.show();
                    }
                }
            }
        });

        setWebView();
    }

    public void setDeviceON_Img(int deviceON_img)
    {this.deviceON_img = deviceON_img; }

    public void setDeviceOFF_Img(int deviceOFF_img)
    { this.deviceOFF_img = deviceOFF_img; }

    public void setWebView()
    {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
    //    webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}
