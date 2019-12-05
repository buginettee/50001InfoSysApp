package com.example.app50001;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.List;

public class backendDeliveryCustomRecyclerView extends RecyclerView.Adapter<backendDeliveryCustomRecyclerView.customViewHolder> {

    private Context context;
    private List<String> titles;
    private HashMap<String, List<String>> details; //title, (address, addinfo)
    private DeliveryHomeFragment deliveryfrag;


    //constructor
    public backendDeliveryCustomRecyclerView(Context context, List<String> titles, HashMap<String, List<String>> details, DeliveryHomeFragment deliveryfragment) {
        this.context = context;
        this.titles = titles;
        this.details = details;
        this.deliveryfrag = deliveryfragment;
    }

    @NonNull
    @Override
    public backendDeliveryCustomRecyclerView.customViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.backend_delivery_recycler_view_layout, parent, false);

        customViewHolder newview = new customViewHolder(view);

        return newview;
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder viewHolder, int position) {
        //get data to replace the custom fields

        viewHolder.titleDisplay.setText(titles.get(position).toString());
        viewHolder.addressDisplay.setText(details.get(titles.get(position)).get(0).toString());
        viewHolder.addinfoDisplay.setText(details.get(titles.get(position)).get(1).toString());


    }

    @Override
    public int getItemCount() {
        return this.titles.size();
    }


    public class customViewHolder extends RecyclerView.ViewHolder {
        public TextView titleDisplay;
        public TextView addressDisplay;
        public TextView addinfoDisplay;


        public void onClick(View v) {

        }
        //  }

        public customViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.titleDisplay = (TextView) itemView.findViewById(R.id.recycler_delivery_title_display);
            this.addressDisplay = (TextView) itemView.findViewById(R.id.recycler_delivery_address_display);
            this.addinfoDisplay = (TextView) itemView.findViewById(R.id.recycler_delivery_addinfo_display);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final int position = getLayoutPosition();

                    //prepare the items to be shown in the alert dialog
                    String title = titles.get(position).toString();
                    String address = details.get(title).get(0).toString();
                    String addinfo = details.get(title).get(1).toString();


                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(title);
                    builder.setMessage("Are you ready to complete the delivery?");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Open Box", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deliveryfrag.completeDelivery(titles.get(position).toString(),details.get(titles.get(position)).get(0).toString());

                        }
                    });
                    builder.setNeutralButton("Contact", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deliveryfrag.callPerson(titles.get(position).toString());
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }


    }
}
//COMPLETED

