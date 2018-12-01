package com.example.aniltaskiran.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseManager {

    DatabaseHelper databaseHelper;

    public DatabaseManager(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public long insertReminderData(Reminder reminder){
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COLUMN_TITLE, reminder.title);
        contentValues.put(databaseHelper.COLUMN_DETAIL, reminder.detail);
        contentValues.put(databaseHelper.COLUMN_DATE, reminder.date);
        contentValues.put(databaseHelper.COLUMN_TIME, reminder.time);


        long id = database.insert(databaseHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList<Reminder> getReminderData(){
        ArrayList<Reminder> arrayList = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String[] columns = {
                databaseHelper.COLUMN_ID,
                databaseHelper.COLUMN_TITLE,
                databaseHelper.COLUMN_DETAIL,
                databaseHelper.COLUMN_DATE,
                databaseHelper.COLUMN_TIME};
        Cursor cursor = db.query(databaseHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()){

            int id =cursor.getInt(cursor.getColumnIndex(databaseHelper.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_TITLE));
            String detail = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_DETAIL));
            String date = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_DATE));
            String time = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_TIME));

            arrayList.add(new Reminder(title,detail,time,date));
        }
        return arrayList;
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        public Context context;
        static final String databaseName = "reminderDB";
        static final String TABLE_NAME = "reminder";
        static final String COLUMN_ID = "ID";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_DETAIL = "detail";
        static final String COLUMN_TIME = "time";
        static final String COLUMN_DATE = "date";

        static final int databaseVersion = 1;
        static final String createTable = "Create table " +
                TABLE_NAME +
                " (ID integer PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE +
                " text, " +
                COLUMN_DETAIL +
                " text, " +
                COLUMN_TIME +
                " text, " +
                COLUMN_DATE +
                " text )";

        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+ TABLE_NAME;

        public DatabaseHelper(Context context){
            super(context, databaseName, null, databaseVersion);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(createTable);
            } catch (Exception e) {
                Toast.makeText(context, "error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "OnUpgrade", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Toast.makeText(context, "OnUpgrade error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
