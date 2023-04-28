package com.sanjeev.stephan.murmu.testtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemModel> arrayList;
    RecyclerView recyclerView;
    ItemAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createItemList();
        buildRecyclerView();
    }

    void createItemList()
    {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemModel(R.drawable.ic_touch,"Simple Text"));
        arrayList.add(new ItemModel(R.drawable.ic_touch,"Simple Text"));
    }
    void buildRecyclerView()
    {
        //Look for the RecyclerView in activity_main.xml layout using their Reference ID.
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        //set Layout Manager to position the items
        recyclerView.setLayoutManager(layoutManager);
        //Create adapter passing the simple user dataset
        adapter = new ItemAdapter(arrayList);
        //Attach the Adapter to the recyclerView to populate items.
        recyclerView.setAdapter(adapter);

        adapter.setOnItem_ClickListener(new ItemAdapter.OnItem_ClickListener() {
            @Override
            public void onItem_click(int position) {
                //Code the action you want when an item is being clicked
                setToast("Item At Position : " + position + " Clicked");

            }

            @Override
            public void onImage_click(int position) {
              //Code the action that you want when an image is being clicked.
                arrayList.get(position).setImage(R.drawable.ic_tag_faces);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onText_click(int position) {
                //Code the action that you want when an text is being clicked.
                arrayList.get(position).setText("Text Just Clicked");
                adapter.notifyItemChanged(position);
            }
        });
    }

    void setToast(String message)
    {
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
