package com.example.ridebooktest;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddRideFragment.OnFragmentInteractionListener{

    ListView rideList;
    ArrayAdapter<Ride> rideAdapter;
    ArrayList<Ride> rideDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rideList = findViewById(R.id.ride_list);
        rideDataList = new ArrayList<>();
        rideAdapter = new CustomList(this, rideDataList);
        rideList.setAdapter(rideAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add a ride", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                new AddRideFragment().show(getSupportFragmentManager(), "ADD RIDE");
            }
        });

        registerForContextMenu(rideList);

    }

    @Override
    public void onOkPressed(Ride newRide) {
        rideAdapter.add(newRide);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
        super.onCreateContextMenu(menu, view, contextMenuInfo);
        menu.setHeaderTitle("Choose your option");
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit_ride:
                Toast.makeText(this, "Edit your ride...", Toast.LENGTH_SHORT).show();
                editRide(info.position);
                return true;
            case R.id.delete_ride:
                Toast.makeText(this, "Ride Deleted", Toast.LENGTH_SHORT).show();
                deleteRide(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void editRide(int position){
        // TODO edit the selected ride specified by itemId
        // give the position of the selectedRide object to the ride manager
        
    }

    public void deleteRide(int position){
        // TODO: get the ride's distance and remove it from total distance
        rideAdapter.remove(rideDataList.get(position));
        rideAdapter.notifyDataSetChanged();
    }

}
