package com.sanjeev.stephan.murmu.ctnetwork.smarthome.tools;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sanjeev.stephan.murmu.ctnetwork.smarthome.GlobalVariables;
import com.sanjeev.stephan.murmu.ctnetwork.smarthome.R;


public class SendLinkRequest extends Fragment {



    View view;
    ShowMsg show = new ShowMsg(getContext());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_send_link_request, container, false);


            setWebView(GlobalVariables.getLink());

       // else { show.setMsgLog("sendLinkRequest","No Link Received");}

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    void setWebView(String url)
    {

        WebView w = (WebView) view.findViewById(R.id.id_web_view_send_link_request);
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setLoadWithOverviewMode(true);
        w.getSettings().setUseWideViewPort(true);
        w.getSettings().setSupportZoom(false);
        w.getSettings().setBuiltInZoomControls(false);
        w.loadUrl(url);
        show.setMsgLog("SendLinkRequest Fragment",url);
        w.setWebViewClient(new WebViewClient());

    }


}
