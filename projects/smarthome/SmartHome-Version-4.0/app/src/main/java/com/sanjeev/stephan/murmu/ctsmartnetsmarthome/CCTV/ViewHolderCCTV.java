package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV;


import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

/**
 * also see {@link AdapterCCTV} | {@link ModelCCTV}
 * also see {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.CCTVFragment}
 */
public class ViewHolderCCTV extends RecyclerView.ViewHolder
{
    private static final String TAG = "ViewHolderCCTV";
    Show s = new Show(TAG);
    public TextView cameraLocation;
    public WebView displayCCTV;
    public ImageView moreOption;
    private String url = " ";
    private PopupMenu pop;
    private FragmentManager fragmentManager;

    public ViewHolderCCTV(@NonNull final View itemView, final AdapterCCTV.AllCCCTVItemListener listener)
    {
        super(itemView);

        cameraLocation = (TextView)itemView.findViewById(R.id.id_view_cctv_location);
        displayCCTV    = (WebView)itemView.findViewById(R.id.id_web_view_cctv_fragment);
        moreOption     = (ImageView)itemView.findViewById(R.id.id_view_cctv_more_option);

        moreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(listener != null)
                {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        pop = new PopupMenu(itemView.getContext(), moreOption);
                        setPopUpOptions(position,listener);
                    }
                }
            }
        });

        setWebview();
    }
    public void setFragmentManager(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    /**
     * WebView browser to display Camera Footage
     */
    private void setWebview()
    {
        displayCCTV.getSettings().setJavaScriptEnabled(true);
        displayCCTV.getSettings().setLoadWithOverviewMode(true);
        displayCCTV.getSettings().setUseWideViewPort(false);
        displayCCTV.getSettings().setSupportZoom(false);
        displayCCTV.getSettings().setBuiltInZoomControls(false);
        displayCCTV.setWebViewClient(new WebViewClient());
    }
    private void setPopUpOptions(final int indexPosition,final AdapterCCTV.AllCCCTVItemListener listener)
    {
        pop.getMenuInflater().inflate(R.menu.menu_cctv,pop.getMenu());
        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                listener.onCCTVItem_ClickListener(indexPosition,menuItem.getTitle().toString(),menuItem.getItemId(),fragmentManager);

                return false;
            }
        });

        pop.show();
    }




}
