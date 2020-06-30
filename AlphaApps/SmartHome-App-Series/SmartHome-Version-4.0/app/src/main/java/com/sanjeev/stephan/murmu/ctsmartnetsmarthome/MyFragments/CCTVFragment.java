package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV.AdapterCCTV;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV.ModelCCTV;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyDatabase.CCTVDatabase;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyInterfaces.InterfaceMainActivity;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.R;
import com.sanjeev.stephan.murmu.ctsmartnet.smarthome.Show;

import java.util.ArrayList;

/**
 * Fragment Container for Populating the CCTV Recycler's items
 * also see {@link AdapterCCTV} | {@link ModelCCTV}
 * also see {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV.ViewHolderCCTV}
 */
public class CCTVFragment extends Fragment {

    private static final String TAG = "CCTVFragment";
    private Show show = new Show(TAG);
    private ArrayList<ModelCCTV> arrayList = new ArrayList<>();
    private ModelCCTV cctvModel = new ModelCCTV();
    private AdapterCCTV adapter;
    private View view;
    private Cursor cursor;
    private InterfaceMainActivity listener;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cctv, container, false);

        createModelList();
        //MyCameraList.createList(arrayList);
        buildRecyclerView();
        refreshCCTV();

        return view;
    }

    private void refreshCCTV() {
        refreshLayout = view.findViewById(R.id.id_cctv_fragment_swipe_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();

                if (refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }

            }
        });
    }


    private void createModelList() {
        CCTVDatabase cameraDb = new CCTVDatabase(getContext());
        SQLiteDatabase sqLiteDatabase = cameraDb.getReadableDatabase();
        cursor = cameraDb.getAllData(sqLiteDatabase);

        show.setLog("Displaying Data From Database");
        show.setLog("------------------------[ CCTV-Fragment() ]---------------------------------");
        show.setLog("[Index]       IP      | PORT | Location | URL Attributes");

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int index = cursor.getInt(cursor.getColumnIndex(CCTVDatabase.DB_COLUMN_ID));
                int port = cursor.getInt(cursor.getColumnIndex(CCTVDatabase.DB_PORT_NUM));
                String ip = cursor.getString(cursor.getColumnIndex(CCTVDatabase.DB_DEVICE_IP));
                String location = cursor.getString(cursor.getColumnIndex(CCTVDatabase.DB_DEVICE_LOCATION));
                String attribute = cursor.getString(cursor.getColumnIndex(CCTVDatabase.DB_URL_ATTRIBUTE));

                arrayList.add(new ModelCCTV(index, ip, port, attribute, location));
                show.setLog("[" + index + "]    " + ip + " | " + port + " | " + location + " | " + attribute);
            }
            while (cursor.moveToNext());

            cameraDb.close();
        } else {
            show.setLog("Not CCTV Cameras Found in Database!");
        }

    }


    private void buildRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.id_recycler_view_cctv_fragment);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterCCTV(arrayList);
        recyclerView.setAdapter(adapter);

        adapter.setAllCCCTVItemListener(new AdapterCCTV.AllCCCTVItemListener() {
            @Override
            public void onCCTVItem_ClickListener(int itemPosition, String menu, int menuItemId, FragmentManager fragmentManager) {
                cctvModel = arrayList.get(itemPosition);

                listener.onPopupMenuItemSelectedListener(itemPosition, cctvModel, menu, menuItemId, fragmentManager);
            }

        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (InterfaceMainActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement InterfaceMainActivity");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        show.setLog("Fan Array List Adapter Refreshed");
    }
}
