package com.example.csci526prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DBFriendsHandler extends SQLiteOpenHelper implements Serializable {

    private static final String DB_NAME = "Friends";

    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Friends";
    private static final String USERID_COL = "userid";
    private static final String FRIENDID_COL = "friendid";

    public DBFriendsHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + USERID_COL + " INTEGER, "
                + FRIENDID_COL + " INTEGER)";

        db.execSQL(query);
    }

    public ArrayList<Integer> getFriends(int user){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorFriends = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USERID_COL + " = \"" + user+ "\"", null);

        ArrayList<Integer> friendArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorFriends.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                friendArrayList.add(cursorFriends.getInt(1));
            } while (cursorFriends.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorFriends.close();
        return friendArrayList;
    }

    // method adds friend to friend database
    public void addNewFriend(int userId, int friendId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues userValues = new ContentValues();

        userValues.put(USERID_COL, userId);
        userValues.put(FRIENDID_COL, friendId);

        db.insert(TABLE_NAME, null, userValues);

        ContentValues friendValues = new ContentValues();

        friendValues.put(USERID_COL, friendId);
        friendValues.put(FRIENDID_COL, userId);

        db.insert(TABLE_NAME, null, friendValues);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
