package com.example.csci526prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class DBUserHandler extends SQLiteOpenHelper implements Serializable {

    private static final String DB_NAME = "Users";

    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Users";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String ABOUT_COL = "about";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";

    public DBUserHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ABOUT_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        db.execSQL(query);
    }

    public User login(String userName, String password, DBFriendsHandler temp){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COL + " = \"" + userName + "\" AND " + PASSWORD_COL +" = \"" + password +"\"", null);

        if (cursorUsers.getCount() <= 0){
            return null;
        }

        cursorUsers.moveToFirst();

        ArrayList<Integer> friends = temp.getFriends(cursorUsers.getInt(0));
        User user = new User(cursorUsers.getInt(0),cursorUsers.getString(1),cursorUsers.getString(2),cursorUsers.getString(3),cursorUsers.getString(4), friends);

        return user;
    }

    public ArrayList<UserModel> getUsers(String username, DBFriendsHandler temp){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COL + " != \"" + username + "\"", null);
        ArrayList<UserModel> userModelArrayList = new ArrayList<>();
        if(cursorUsers.moveToFirst()){
            do{
                ArrayList<Integer> friends = temp.getFriends(cursorUsers.getInt(0));
                userModelArrayList.add(new UserModel(
                        cursorUsers.getInt(0),
                        cursorUsers.getString(1),
                        cursorUsers.getString(2),
                        cursorUsers.getString(3),
                        cursorUsers.getString(4),
                        friends
                ));
            }while (cursorUsers.moveToNext());
        }
        cursorUsers.close();
        return userModelArrayList;
    }

    public void readUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        if(cursorUsers.moveToFirst()){
            do{
                System.out.println("DATABASE: " + cursorUsers.getString(1));
            }while (cursorUsers.moveToNext());
        }
        cursorUsers.close();
    }

    public void addNewUser(String userName, String about, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, userName);
        values.put(ABOUT_COL, about);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
