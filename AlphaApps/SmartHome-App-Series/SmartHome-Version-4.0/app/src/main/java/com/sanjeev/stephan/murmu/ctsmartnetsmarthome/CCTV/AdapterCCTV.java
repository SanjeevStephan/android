package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceMainActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;

import java.util.ArrayList;

/**
 * Adapter for the CCTV Camera.
 * also see {@link ViewHolderCCTV} | {@link ModelCCTV}
 * also see {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.CCTVFragment}
 * @author Sanjeev Stephan Murmu
 * @since 21th Oct 2019
 */
public class AdapterCCTV extends RecyclerView.Adapter<ViewHolderCCTV>
{
    private FragmentManager fragmentManager;
    private ArrayList<ModelCCTV> arrayList = new ArrayList<>();

    private ModelCCTV model = new ModelCCTV();
    private InterfaceMainActivity popMenuListener;

    private AllCCCTVItemListener allCCCTVItemListener;

    public void setFragmentManager(FragmentManager fragmentManager)
    { this.fragmentManager = fragmentManager; }

    public AdapterCCTV(ArrayList<ModelCCTV> arrayList) {this.arrayList = arrayList; }

    @NonNull
    @Override
    public ViewHolderCCTV onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout_fragment_cctv, viewGroup, false);
        ViewHolderCCTV viewHolder = new ViewHolderCCTV(view, allCCCTVItemListener);
        viewHolder.setFragmentManager(fragmentManager);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCCTV allCCTVVIewHolder, int i) {
        model = arrayList.get(i);
        String link = "http://" + model.getCameraIp() + ":" + model.getCameraPort() + "/" + model.getIpAttributes();
        //example_link =  http://192.168.225.32:8080/browserfs.html
        allCCTVVIewHolder.cameraLocation.setText(model.getCameraLocation());
        allCCTVVIewHolder.displayCCTV.loadUrl(link);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setAllCCCTVItemListener(AllCCCTVItemListener allCCCTVItemListener)
    { this.allCCCTVItemListener = allCCCTVItemListener; }

    public interface AllCCCTVItemListener
    { void onCCTVItem_ClickListener(int itemPosition, String menu, int menuItemId,FragmentManager fragmentManager);}
}
