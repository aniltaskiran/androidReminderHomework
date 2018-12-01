package com.example.aniltaskiran.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AddNewReminderActivity extends AppCompatActivity {
    Button timeButton;
    String time;

    EditText titleText;
    EditText detailText;

    FloatingActionButton backButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_reminder_activity);

        timeButton = findViewById(R.id.timeButton);

        time = new Date().toString();
        timeButton.setText(time);

        titleText = findViewById(R.id.titleEditText);
        backButton = findViewById(R.id.revertFAB);
        detailText = findViewById(R.id.detailEditText);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                TimePicker popTime = new TimePicker();
                popTime.show(fragmentManager,"as");

            }
        });


        Button button = findViewById(R.id.doneButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseManager manager = new DatabaseManager(getApplicationContext());

                manager.insertReminderData(new Reminder(
                        titleText.getText().toString(),
                        detailText.getText().toString(),
                        time,
                        "date"));
            }
        });

    }

    public void setTime(String time){
        timeButton.setText(time);
        this.time = time;
        Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
    }
}
