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

public class RecyclerViewAttendee extends RecyclerView.Adapter<RecyclerViewAttendee.ViewHolder>{
    //Similar to vector in C++, except no square bracket operators
    private ArrayList<Attendees> GOCAttendees;
    //Tells where within the app it is
    private Context mContext;

    //Constructor
    RecyclerViewAttendee(ArrayList<Attendees> GOCAttendees, Context mContext) {
        this.GOCAttendees = GOCAttendees;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Similar to menu inflater, find a layout and use it with code
        //Do this every time you do RecyclerView
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attendee_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(GOCAttendees.get(i).Name);
        viewHolder.org.setText(GOCAttendees.get(i).Organization);
        viewHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Toast.makeText(mContext, GOCAttendees.get(i).Email, Toast.LENGTH_SHORT).show();
                Intent go = new Intent(mContext,SmartProfile.class);
                go.putExtra("email_value",GOCAttendees.get(i).Email);
                go.putExtra("name_value",GOCAttendees.get(i).Name);
                go.putExtra("industry_value",GOCAttendees.get(i).Industry);
                go.putExtra("organization_value",GOCAttendees.get(i).Organization);
                mContext.startActivity(go);
            }
        });
    }

    //Dynamically determines number of cells in layout
    @Override
    public int getItemCount() {
        return GOCAttendees.size();
    }

    // View for each item in the Adapter class
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,org;

        ConstraintLayout Layout;

        //Like onCreate
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            org = itemView.findViewById(R.id.org);

            Layout = itemView.findViewById(R.id.Attendee_List);
        }
    }
}

