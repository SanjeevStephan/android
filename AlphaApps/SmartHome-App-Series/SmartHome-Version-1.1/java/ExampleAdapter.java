package com.sanjeev.stephan.murmu.recyclerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
  Create the basic adapter extending from RecyclerView.Adapter
  Note that we specify the custom ViewHolder which give us access to our views
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
{
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
        void openSelectedItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // Provide a direct reference to each of the views within a data item
    // used to cache the views within the item layout for fast access.

    public static class ExampleViewHolder extends RecyclerView.ViewHolder
    {
        // Your holder should contain a member variable;
        // for any view that will be set as you render a row
        public ImageView mImageView,mSelectedItem;
        public TextView mTextView1,mTextView2;

        // We also create a constructor that accepts the entire item row
        //and does the view lookups to find each subview;
        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any viewHolder instance.
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mSelectedItem = itemView.findViewById(R.id.id_selected_item);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     if(listener != null)
                     {
                         int position = getAdapterPosition();
                         if(position != RecyclerView.NO_POSITION)
                         {
                             listener.onItemClick(position);
                         }
                     }

                 }
             });

             mSelectedItem.setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View view) {

                     if(listener != null)
                     {
                         int position = getAdapterPosition();

                         if(position != RecyclerView.NO_POSITION)
                         {
                             listener.openSelectedItemClick(position);
                         }
                     }
                 }
             });

        }
    }

    @NonNull
    /*
       Usually involves inflating a layout from XML and return the holder
     */
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View v = inflater.inflate(R.layout.example_item, viewGroup, false);
        //retun a new holder instance.
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(v,mListener);
        return exampleViewHolder;
    }

    // pass in the array into the constructor
    public ExampleAdapter(ArrayList<ExampleItem> exampleItems)
    {
        mExampleList = exampleItems;
    }

    //involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i)
    {
        // Get the Data Model based on position.
        ExampleItem currentItem = mExampleList.get(i);

        //Set item views based on your view and data model
        exampleViewHolder.mImageView.setImageResource(currentItem.getImageResource());
        exampleViewHolder.mTextView1.setText(currentItem.getText1());
        exampleViewHolder.mTextView2.setText(currentItem.getText2());

    }

    //Retun the total count of item in the list.
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
