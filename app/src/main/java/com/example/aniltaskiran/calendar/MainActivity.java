package com.example.aniltaskiran.calendar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.reminderSpinner);
        floatingActionButton = findViewById(R.id.reminderFAB);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddNewReminderActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = findViewById(R.id.reminderListView);


        ArrayList<Reminder> reminders = new ArrayList<>();

        reminders.add(new Reminder("başlık","detay","time", "date"));
        reminders.add(new Reminder("başlık2","detay2","time2", "date2"));
        // Create the adapter to convert the array to views
        ReminderListAdapter adapter = new ReminderListAdapter(reminders, this);
        // Attach the adapter to a ListView

        listView.setAdapter(adapter);



        ArrayList<String> spinnerList = new ArrayList<>();

        spinnerList.add("Reminder");
        spinnerList.add("Calendar");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spinnerList);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
