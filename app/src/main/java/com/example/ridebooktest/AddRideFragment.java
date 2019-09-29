package com.example.ridebooktest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.ridebooktest.R.layout.add_ride_fragment_layout;

public class AddRideFragment extends DialogFragment{

    private EditText dateInput;
    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener{
        void onOkPressed(Ride newRide);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;

        }else{
            throw new RuntimeException(context.toString()+"must implement onFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(add_ride_fragment_layout,null);
        dateInput = view.findViewById(R.id.dateInput);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view).setTitle("Add ride").setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Ride selectedRide = RideManager.getRide();
                        String date = dateInput.getText().toString();
                        if(selectedRide == null){
                            listener.onOkPressed(new Ride(date));
                        } else{
                            selectedRide.setDate(date);
                        }

                    }
                }).create();
    }
}
