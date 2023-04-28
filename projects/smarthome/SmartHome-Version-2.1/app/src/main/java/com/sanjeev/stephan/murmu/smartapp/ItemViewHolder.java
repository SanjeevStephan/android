package com.sanjeev.stephan.murmu.smartapp;

import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder
{
    TextView deviceName_textView,ipAddress_textView,relayNum_textView,location_textView;
    ImageView selectedItem_moreOptions;

    public ItemViewHolder(View view, final ItemAdapter.OnItem_ClickListener listener)
    {
        super(view);
        deviceName_textView = (TextView) view.findViewById(R.id.id_device_name_textView);
        ipAddress_textView  = (TextView) view.findViewById(R.id.id_ip_address_textView);
        relayNum_textView   = (TextView) view.findViewById(R.id.id_relay_num_textView);
        location_textView   = (TextView) view.findViewById(R.id.id_room_location);
        selectedItem_moreOptions = (ImageView) view.findViewById(R.id.id_selected_item_more_options);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(listener != null)
                {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION)
                    {
                        listener.onItem_click(position);
                    }
                }

            }
        });

        selectedItem_moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if(listener != null)
                {
                    final int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {

                        PopupMenu pop = new PopupMenu(view.getContext(), selectedItem_moreOptions);
                        pop.getMenuInflater().inflate(R.menu.main_popup_menu, pop.getMenu());

                        pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {

                                final String option_string;
                                switch(menuItem.getItemId())
                                {
                                    case R.id.id_pop_menu_edit :
                                        option_string = "edit";
                                        listener.onMoreOptionMenu_click(position,option_string);
                                        break;
                                    case R.id.id_pop_menu_delete:
                                        option_string = "delete";
                                        listener.onMoreOptionMenu_click(position,option_string);
                                        break;
                                }

                                return true;
                            }
                        });

                        pop.show();


                    }
            }
            }
        });




    }


}
