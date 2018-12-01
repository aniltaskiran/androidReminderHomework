package com.example.aniltaskiran.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TimePicker extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.time_picker, container, false);

        final android.widget.TimePicker timePicker = view.findViewById(R.id.timePicker);
        Button doneButton = view.findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timePicker.getHour() + ":" + timePicker.getMinute();

                AddNewReminderActivity activity = (AddNewReminderActivity) getActivity();
                activity.setTime(time);
            }
        });

        return view;
    }

}