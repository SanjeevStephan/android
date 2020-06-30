package com.sanjeev.stephan.murmu.testtwo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/*
  Create the basic adapter extending from RecyclerView.Adapter
  Note that we specify the custom ViewHolder which give us access to our views
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>
{
    private ArrayList<ItemModel> arrayList;

    // Pass in the arraylist in the ItemAdapter Constructor
    public ItemAdapter(ArrayList<ItemModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    /*
    onCreateViewHolder usually involves around inflating a item_layout from xml as a View and
    returning that view as ViewHolder.
     */
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View view = inflater.inflate(R.layout.item_layout, viewGroup, false);
        // return the new holder instances.
        ItemViewHolder viewHolder = new ItemViewHolder(view,listener_here);
        return viewHolder;
    }

    @Override
    // onBindViewHolder involves in populating the data into the item through ViewHolder.
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        // get the data model based on the position
        ItemModel model = arrayList.get(position);
        // set item view based on your view and data model.
        itemViewHolder.imageView.setImageResource(model.getImage());
        itemViewHolder.textView.setText(model.getText());

    }


    @Override
    //Retun the total count of item in the list.
    public int getItemCount() {
        return arrayList.size();
    }

    private OnItem_ClickListener listener_here;

    public interface OnItem_ClickListener
    {
        void onItem_click(int position);
        void onImage_click(int position);
        void onText_click(int position);
    }

    public void setOnItem_ClickListener(OnItem_ClickListener listener)
    {
        this.listener_here = listener;
    }


}
