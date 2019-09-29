package com.example.ridebooktest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<Ride> {
    private ArrayList<Ride> rides;
    private Context context;

    public CustomList(Context context, ArrayList<Ride> rides){
        super(context,0,rides);
        this.rides = rides;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content,parent,false);

        }
        Ride ride = rides.get(position);

        TextView rideDate = view.findViewById(R.id.date_text);

        rideDate.setText(ride.getDate().toString());

        return view;
    }
}
