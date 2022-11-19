package com.example.csci526prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSessionsHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "Sessions";

    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Sessions";
    private static final String ID_COL = "id";
    private static final String CREATOR_COL = "creator";
    private static final String MUSCLES_COL = "muscles";
    private static final String NAME_COL = "name";
    private static final String DESC_COL = "description";
    private static final String DATE_COL = "date";
    private static final String TIME_COL = "time";
    private static final String LOCATION_COL = "location";

    public DBSessionsHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CREATOR_COL + " INTEGER,"
                + MUSCLES_COL + " INTEGER,"
                + NAME_COL + " TEXT,"
                + DESC_COL + " TEXT,"
                + DATE_COL + " DATE,"
                + TIME_COL + " TIME,"
                + LOCATION_COL + " TEXT)";

        db.execSQL(query);
    }
    public void deleteSession(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{""+id});
        db.close();
    }
    public void addNewSession(int creator, int muscles, String name, String desc, String date, String time, String location) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CREATOR_COL, creator);
        values.put(MUSCLES_COL, muscles);
        values.put(NAME_COL, name);
        values.put(DESC_COL, desc);
        values.put(DATE_COL, date);
        values.put(TIME_COL, time);
        values.put(LOCATION_COL, location);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

