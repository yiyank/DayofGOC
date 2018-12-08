package com.example.to426.dayofgoc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class RecyclerViewSchedule extends RecyclerView.Adapter<RecyclerViewSchedule.ViewHolder>{
    //Similar to vector in C++, except no square bracket operators
    private ArrayList<Events> GOCEvents;
    //Tells where within the app it is
    private Context mContext;

    //Constructor
    RecyclerViewSchedule(ArrayList<Events> GOCEvents, Context mContext) {
        this.GOCEvents = GOCEvents;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Similar to menu inflater, find a layout and use it with code
        //Do this every time you do RecyclerView
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.event.setText(GOCEvents.get(i).EventName);
        viewHolder.location.setText(GOCEvents.get(i).Location);
        viewHolder.time.setText(GOCEvents.get(i).Time);

        viewHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setPositiveButton(GOCEvents.get(i).Location, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent go1 = new Intent(mContext, Map.class);
                        mContext.startActivity(go1);
                    }
                });
                // Figure out how to click to specific Speaker
                builder.setNegativeButton(GOCEvents.get(i).Speaker, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent go1 = new Intent(mContext, SmartProfile.class);
                        mContext.startActivity(go1);
                    }
                });
                builder.create().show();
            }
        });


    }

    //Dynamically determines number of cells in layout
    @Override
    public int getItemCount() {
        return GOCEvents.size();
    }

    // View for each item in the Adapter class
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView location,event,time;

        ConstraintLayout Layout;

        //Like onCreate
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.location);
            event = itemView.findViewById(R.id.event);
            time = itemView.findViewById(R.id.time);

            Layout = itemView.findViewById(R.id.Schedule_List);
        }
    }
}

