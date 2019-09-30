package com.example.ridebooktest;

import android.annotation.TargetApi;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.ridebooktest.R.layout.add_ride_fragment_layout;

public class AddRideFragment extends DialogFragment {

    private DatePickerDialog datePickerDialog;

    private Ride selectedRide;

    private EditText dateInput;
    private EditText timeInput;
    private EditText distanceInput;
    private EditText speedInput;
    private EditText cadenceInput;
    private EditText commentInput;

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


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        View view = LayoutInflater.from(getActivity()).inflate(add_ride_fragment_layout,null);
        dateInput = view.findViewById(R.id.dateInput);
        timeInput = view.findViewById(R.id.timeInput);
        distanceInput = view.findViewById(R.id.distanceInput);
        speedInput = view.findViewById(R.id.speedInput);
        cadenceInput = view.findViewById(R.id.cadenceInput);
        commentInput = view.findViewById(R.id.commentInput);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(getArguments()!=null){
            selectedRide = (Ride)getArguments().getSerializable("selectedRideObject");
            dateInput.setText(selectedRide.getDate());
            timeInput.setText(selectedRide.getTime());
            distanceInput.setText(String.valueOf(selectedRide.getDistance()));
            speedInput.setText(String.valueOf(selectedRide.getSpeed()));
            cadenceInput.setText(String.valueOf(selectedRide.getCadence()));
            commentInput.setText(String.valueOf(selectedRide.getComment()));
        }

        return builder.setView(view).setTitle("Add ride").setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        // display date picker
                        String date = dateInput.getText().toString();
                        String time = timeInput.getText().toString();
                        float distance = Float.valueOf(distanceInput.getText().toString());
                        float speed = Float.valueOf(speedInput.getText().toString());
                        int cadence = Integer.valueOf(cadenceInput.getText().toString());
                        String comment = commentInput.getText().toString();
                        if(selectedRide == null){
                            listener.onOkPressed(new Ride(date, time, distance, speed, cadence, comment));
                        } else{
                            selectedRide.setDate(date);
                            selectedRide.setTime(time);
                            selectedRide.setDistance(distance);
                            selectedRide.setSpeed(speed);
                            selectedRide.setCadence(cadence);
                            selectedRide.setComment(comment);
                            listener.editRide();
                        }

                    }
                }).create();
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            dateInput.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));
        }
    };
}
