package com.sanjeev.stephan.murmu.testtwo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/*
   Provide a direct reference to each of the views within a data item
   used to cache the views within the item layout for fast access.
*/
public class ItemViewHolder extends RecyclerView.ViewHolder
{
    /*
     Your holder should contain a member variable;
     for any view that will be set as you render a row
      */

    public ImageView imageView;
    public TextView textView;
    /*
    We also create a constructor that accepts the entire item row
    and does the view lookups to find each subview;
     */
    public ItemViewHolder(View itemView,final ItemAdapter.OnItem_ClickListener listener)
    {
        /*
        Stores the itemView in a public final member variable that can be used
        to access the context from any viewHolder instance.
         */
        super(itemView);
        imageView = itemView.findViewById(R.id.id_imageView);
        textView = itemView.findViewById(R.id.id_textView);

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(listener != null)
                {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onItem_click(position);
                    }
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(listener != null)
                {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onImage_click(position);
                    }
                }
            }
        });


        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(listener != null)
                {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onText_click(position);
                    }
                }
            }
        });

    }

}
