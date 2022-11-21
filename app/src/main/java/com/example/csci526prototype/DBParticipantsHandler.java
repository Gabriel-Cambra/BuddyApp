package com.example.csci526prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

public class DBParticipantsHandler extends SQLiteOpenHelper implements Serializable {

    private static final String DB_NAME = "Participants";

    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Participants";
    private static final String PARTICIPANTID_COL = "participantID";
    private static final String SESSIONID_COL = "sessionID";

    public DBParticipantsHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + SESSIONID_COL + " INTEGER, "
                + PARTICIPANTID_COL + " INTEGER)";

        db.execSQL(query);
    }

    // method adds friend to friend database
    public void addNewFriend(int sessionId, int participantId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SESSIONID_COL, sessionId);
        values.put(PARTICIPANTID_COL, participantId);

        db.insert(TABLE_NAME, null, values);


        db.close();
    }
    public void deleteSession(int sessionId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "sessionID=?", new String[]{""+sessionId});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
