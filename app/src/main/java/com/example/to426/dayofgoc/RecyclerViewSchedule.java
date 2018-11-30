package com.example.to426.dayofgoc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.en.setText(GOCEvents.get(i).EventName);
        viewHolder.lo.setText(GOCEvents.get(i).Location);
        viewHolder.tm.setText(GOCEvents.get(i).Time);
        viewHolder.sp.setText(GOCEvents.get(i).Speaker);

        viewHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, GOCEvents.get(i).EventName, Toast.LENGTH_SHORT).show();
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
        TextView lo,en,sp,tm;

        ConstraintLayout Layout;

        //Like onCreate
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lo = itemView.findViewById(R.id.lo);
            en = itemView.findViewById(R.id.en);
            sp = itemView.findViewById(R.id.sp);
            tm = itemView.findViewById(R.id.tm);

            Layout = itemView.findViewById(R.id.Schedule_List);
        }
    }
}

