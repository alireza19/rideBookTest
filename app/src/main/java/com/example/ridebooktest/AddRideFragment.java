package com.example.ridebooktest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
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

    private Ride selectedRide;
    private EditText dateInput;
    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener{
        void onOkPressed(Ride newRide);
        void editRide();
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

    public static AddRideFragment newInstance(Ride ride){
        Bundle args = new Bundle();
        args.putSerializable("selectedRideObject",ride);
        AddRideFragment addRideFragment = new AddRideFragment();
        addRideFragment.setArguments(args);
        return addRideFragment;
    }

    private boolean validInput(){
//        date = dateInput.getText().toString();
        if(TextUtils.isEmpty(dateInput.getText().toString())){
            dateInput.setError("REQUIRES INPUT");
            return false;
        }
        return true;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(add_ride_fragment_layout,null);
        dateInput = view.findViewById(R.id.dateInput);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(getArguments()!=null){
            selectedRide = (Ride)getArguments().getSerializable("selectedRideObject");
            dateInput.setText(selectedRide.getDate());
        }

        return builder.setView(view).setTitle("Add ride").setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        String date = dateInput.getText().toString();
                        if(selectedRide == null){
                            if(validInput()){
                                listener.onOkPressed(new Ride(date));
                            }else{
                                dateInput.setError("REQUIRES");
                            }
                        } else{
                            selectedRide.setDate(date);
                            listener.editRide();
                        }

                    }
                }).create();
    }
}
