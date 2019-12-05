package com.example.app50001;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class backendHistoryCustomRecyclerView extends RecyclerView.Adapter<backendHistoryCustomRecyclerView.ViewHolder> {

    private Context context;
    private List<String> titles;
    private HashMap<String, List<String>> details;

    //constructor
    public backendHistoryCustomRecyclerView(Context context, List<String> titles, HashMap<String, List<String>> details){
        this.context = context;
        this.titles = titles;
        this.details = details;
    }

    @NonNull
    @Override
    public backendHistoryCustomRecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.backend_history_recycler_view_layout,parent,false);

        ViewHolder newview = new ViewHolder(view);

        return newview;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //get data to replace the custom fields

        viewHolder.titleDisplay.setText(titles.get(position).toString());
        viewHolder.detailsDisplay.setText(details.get(titles.get(position).toString()).toString());

    }

    @Override
    public int getItemCount() {
        return this.titles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView titleDisplay;
        public TextView detailsDisplay;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleDisplay = (TextView) itemView.findViewById(R.id.recycler_history_title_display);
            detailsDisplay = (TextView) itemView.findViewById(R.id.recycler_history_items_display);
        }
    }
}

//COMPLETED