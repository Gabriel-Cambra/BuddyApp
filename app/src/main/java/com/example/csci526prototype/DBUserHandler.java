package com.example.csci526prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBUserHandler extends SQLiteOpenHelper  {

    private static final String DB_NAME = "Users";

    private static final int DB_VERSION = 1;
    private static final String USER_TABLE_NAME = "Users";
    private static final String USER_ID_COL = "id";
    private static final String USER_NAME_COL = "name";
    private static final String USER_ABOUT_COL = "about";
    private static final String USER_EMAIL_COL = "email";
    private static final String USER_PASSWORD_COL = "password";

    private static final String SESSION_TABLE_NAME = "Sessions";
    private static final String SESSION_ID_COL = "id";
    private static final String SESSION_CREATOR_COL = "creator";
    private static final String SESSION_MUSCLES_COL = "muscles";
    private static final String SESSION_NAME_COL = "name";
    private static final String SESSION_DESC_COL = "description";
    private static final String SESSION_DATE_COL = "date";
    private static final String SESSION_TIME_COL = "time";
    private static final String SESSION_LOCATION_COL = "location";

    private static final String FRIEND_TABLE_NAME = "Friends";
    private static final String FRIEND_USERID_COL = "userid";
    private static final String FRIENDID_COL = "friendid";

    private static final String PARTICIPANT_TABLE_NAME = "Participants";
    private static final String PARTICIPANTID_COL = "participantID";
    private static final String PARTICIPANT_SESSIONID_COL = "sessionID";




    public DBUserHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + USER_TABLE_NAME + " ("
                + USER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME_COL + " TEXT,"
                + USER_ABOUT_COL + " TEXT,"
                + USER_EMAIL_COL + " TEXT,"
                + USER_PASSWORD_COL + " TEXT)";

        db.execSQL(query);

        query = "CREATE TABLE " + SESSION_TABLE_NAME + " ("
                + SESSION_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SESSION_CREATOR_COL + " INTEGER,"
                + SESSION_MUSCLES_COL + " INTEGER,"
                + SESSION_NAME_COL + " TEXT,"
                + SESSION_DESC_COL + " TEXT,"
                + SESSION_DATE_COL + " TEXT,"
                + SESSION_TIME_COL + " TEXT,"
                + SESSION_LOCATION_COL + " TEXT)";

        db.execSQL(query);

        query = "CREATE TABLE " + FRIEND_TABLE_NAME + " ("
                + FRIEND_USERID_COL + " INTEGER, "
                + FRIENDID_COL + " INTEGER)";

        db.execSQL(query);

        query = "CREATE TABLE " + PARTICIPANT_TABLE_NAME + " ("
                + PARTICIPANT_SESSIONID_COL + " INTEGER, "
                + PARTICIPANTID_COL + " INTEGER)";

        db.execSQL(query);
    }

    /*-----------------------------------------------------------
                        USER TABLE METHODS
     -----------------------------------------------------------*/

    public User login(String userName, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_NAME_COL + " = \"" + userName + "\" AND " + USER_PASSWORD_COL +" = \"" + password +"\"", null);

        if (cursorUsers.getCount() <= 0){
            cursorUsers.close();
            return null;
        }

        cursorUsers.moveToFirst();

        ArrayList<Integer> friends = getFriends(cursorUsers.getInt(0));
        User user = new User(cursorUsers.getInt(0),cursorUsers.getString(1),cursorUsers.getString(2),cursorUsers.getString(3),cursorUsers.getString(4), friends);
        cursorUsers.close();

        return user;
    }

    public ArrayList<UserModel> getUsers(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_NAME_COL + " != \"" + username + "\"", null);
        ArrayList<UserModel> userModelArrayList = new ArrayList<>();
        if(cursorUsers.moveToFirst()){
            do{
                ArrayList<Integer> friends = getFriends(cursorUsers.getInt(0));
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
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null);
        if(cursorUsers.moveToFirst()){
            do{
                System.out.println("DATABASE: " + cursorUsers.getString(1));
            }while (cursorUsers.moveToNext());
        }
        cursorUsers.close();
    }

    public boolean checkUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME +" WHERE " + USER_NAME_COL + " = \"" + username + "\"", null);
        if (cursorUsers.getCount() > 0){
            cursorUsers.close();
            return false;
        }

        cursorUsers.close();
        return true;

    }
    public void addNewUser(String userName, String about, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USER_NAME_COL, userName);
        values.put(USER_ABOUT_COL, about);
        values.put(USER_EMAIL_COL, email);
        values.put(USER_PASSWORD_COL, password);

        db.insert(USER_TABLE_NAME, null, values);

        db.close();
    }

    /*-----------------------------------------------------------
                        SESSION TABLE METHODS
     -----------------------------------------------------------*/

    public void deleteSession(int id, int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SESSION_TABLE_NAME, "id=? AND creator=?", new String[]{""+id, ""+userId});
        db.delete(PARTICIPANT_TABLE_NAME, "sessionID=? AND participantID=?", new String[]{""+id, ""+userId});
        db.close();
    }
    public void addNewSession(int creator, int muscles, String name, String desc, String date, String time, String location) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SESSION_CREATOR_COL, creator);
        values.put(SESSION_MUSCLES_COL, muscles);
        values.put(SESSION_NAME_COL, name);
        values.put(SESSION_DESC_COL, desc);
        values.put(SESSION_DATE_COL, date);
        values.put(SESSION_TIME_COL, time);
        values.put(SESSION_LOCATION_COL, location);

        db.insert(SESSION_TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<Sessions> getUserSessions(int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorSessions = db.rawQuery("SELECT * FROM " + SESSION_TABLE_NAME + " WHERE " + SESSION_CREATOR_COL + " = " + userId + " OR " + SESSION_ID_COL + " IN (SELECT " +PARTICIPANT_SESSIONID_COL + " FROM "+ PARTICIPANT_TABLE_NAME+" WHERE "+PARTICIPANTID_COL+" = " + userId + ") " +
                "ORDER BY SUBSTR("+SESSION_DATE_COL+",8,4) ASC, (CASE SUBSTR("+SESSION_DATE_COL+",1,3) " +
                "WHEN 'JAN' THEN 1 " +
                "WHEN 'FEB' THEN 2 " +
                "WHEN 'MAR' THEN 3 " +
                "WHEN 'APR' THEN 4 " +
                "WHEN 'MAY' THEN 5 " +
                "WHEN 'JUN' THEN 6 " +
                "WHEN 'JUL' THEN 7 " +
                "WHEN 'AUG' THEN 8 " +
                "WHEN 'SEP' THEN 9 " +
                "WHEN 'OCT' THEN 10 " +
                "WHEN 'NOV' THEN 11 " +
                "WHEN 'DEC' THEN 12 " +
                "END) ASC, SUBSTR("+SESSION_DATE_COL+",5,2) ASC, SUBSTR("+SESSION_TIME_COL+",1,2) ASC, SUBSTR("+SESSION_TIME_COL+",4,2) ASC" , null);
        ArrayList<Sessions> sessionModelArrayList = new ArrayList<>();
        if(cursorSessions.moveToFirst()){
            do{
                ArrayList<Integer> participants = getParticipants(cursorSessions.getInt(0));
                sessionModelArrayList.add(new Sessions(
                        cursorSessions.getInt(0),
                        cursorSessions.getInt(1),
                        cursorSessions.getInt(2),
                        cursorSessions.getString(3),
                        cursorSessions.getString(4),
                        cursorSessions.getString(5),
                        cursorSessions.getString(6),
                        cursorSessions.getString(7),
                        participants
                ));
            }while (cursorSessions.moveToNext());
        }
        cursorSessions.close();
        return sessionModelArrayList;
    }

    public ArrayList<Sessions> getSearchSessions(int userId, String location, boolean dateEmpty, boolean timeEmpty, String date, String time, int muscle){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorSessions;
        if (dateEmpty && timeEmpty){
            String sql = "SELECT * FROM " + SESSION_TABLE_NAME + " WHERE " + SESSION_CREATOR_COL + " != " + userId + " AND " + SESSION_ID_COL + " NOT IN (SELECT " +PARTICIPANT_SESSIONID_COL + " FROM "+ PARTICIPANT_TABLE_NAME+" WHERE "+PARTICIPANTID_COL+" = " + userId + ") ";
            if (muscle != 0) {
                sql += "AND " + SESSION_MUSCLES_COL + " = " + muscle + " " ;
            }
            sql += "AND " + SESSION_LOCATION_COL + " LIKE \"" + location + "\" ORDER BY SUBSTR("+SESSION_DATE_COL+",8,4) ASC, (CASE SUBSTR("+SESSION_DATE_COL+",1,3) " +
                    "WHEN 'JAN' THEN 1 " +
                    "WHEN 'FEB' THEN 2 " +
                    "WHEN 'MAR' THEN 3 " +
                    "WHEN 'APR' THEN 4 " +
                    "WHEN 'MAY' THEN 5 " +
                    "WHEN 'JUN' THEN 6 " +
                    "WHEN 'JUL' THEN 7 " +
                    "WHEN 'AUG' THEN 8 " +
                    "WHEN 'SEP' THEN 9 " +
                    "WHEN 'OCT' THEN 10 " +
                    "WHEN 'NOV' THEN 11 " +
                    "WHEN 'DEC' THEN 12 " +
                    "END) ASC, SUBSTR("+SESSION_DATE_COL+",5,2) ASC, SUBSTR("+SESSION_TIME_COL+",1,2) ASC, SUBSTR("+SESSION_TIME_COL+",4,2) ASC";

            cursorSessions = db.rawQuery(sql, null);

        }else if(dateEmpty){
            String tempTime = time.substring(0,2);
            String sql = "SELECT * FROM " + SESSION_TABLE_NAME + " WHERE " + SESSION_CREATOR_COL + " != " + userId + " AND " + SESSION_ID_COL + " NOT IN (SELECT " +PARTICIPANT_SESSIONID_COL + " FROM "+ PARTICIPANT_TABLE_NAME+" WHERE "+PARTICIPANTID_COL+" = " + userId + ") ";
            if (muscle != 0) {
                sql += "AND " + SESSION_MUSCLES_COL + " = " + muscle + " " ;
            }
            sql += "AND " + SESSION_LOCATION_COL + " LIKE \"" + location + "\" AND " + SESSION_TIME_COL + " LIKE \"" + tempTime + "%\" ORDER BY SUBSTR("+SESSION_DATE_COL+",8,4) ASC, (CASE SUBSTR("+SESSION_DATE_COL+",1,3) " +
                    "WHEN 'JAN' THEN 1 " +
                    "WHEN 'FEB' THEN 2 " +
                    "WHEN 'MAR' THEN 3 " +
                    "WHEN 'APR' THEN 4 " +
                    "WHEN 'MAY' THEN 5 " +
                    "WHEN 'JUN' THEN 6 " +
                    "WHEN 'JUL' THEN 7 " +
                    "WHEN 'AUG' THEN 8 " +
                    "WHEN 'SEP' THEN 9 " +
                    "WHEN 'OCT' THEN 10 " +
                    "WHEN 'NOV' THEN 11 " +
                    "WHEN 'DEC' THEN 12 " +
                    "END) ASC, SUBSTR("+SESSION_DATE_COL+",5,2) ASC, SUBSTR("+SESSION_TIME_COL+",1,2) ASC, SUBSTR("+SESSION_TIME_COL+",4,2) ASC";
            cursorSessions = db.rawQuery(sql, null);

        }else if(timeEmpty){
            String sql = "SELECT * FROM " + SESSION_TABLE_NAME + " WHERE " + SESSION_CREATOR_COL + " != " + userId + " AND " + SESSION_ID_COL + " NOT IN (SELECT " +PARTICIPANT_SESSIONID_COL + " FROM "+ PARTICIPANT_TABLE_NAME+" WHERE "+PARTICIPANTID_COL+" = " + userId + ") ";
            if (muscle != 0) {
                sql += "AND " + SESSION_MUSCLES_COL + " = " + muscle + " " ;
            }
            sql += "AND " + SESSION_LOCATION_COL + " LIKE \"" + location + "\" AND " + SESSION_DATE_COL + " LIKE \"" + date + "\" ORDER BY SUBSTR("+SESSION_DATE_COL+",8,4) ASC, (CASE SUBSTR("+SESSION_DATE_COL+",1,3) " +
                    "WHEN 'JAN' THEN 1 " +
                    "WHEN 'FEB' THEN 2 " +
                    "WHEN 'MAR' THEN 3 " +
                    "WHEN 'APR' THEN 4 " +
                    "WHEN 'MAY' THEN 5 " +
                    "WHEN 'JUN' THEN 6 " +
                    "WHEN 'JUL' THEN 7 " +
                    "WHEN 'AUG' THEN 8 " +
                    "WHEN 'SEP' THEN 9 " +
                    "WHEN 'OCT' THEN 10 " +
                    "WHEN 'NOV' THEN 11 " +
                    "WHEN 'DEC' THEN 12 " +
                    "END) ASC, SUBSTR("+SESSION_DATE_COL+",5,2) ASC, SUBSTR("+SESSION_TIME_COL+",1,2) ASC, SUBSTR("+SESSION_TIME_COL+",4,2) ASC";
            cursorSessions = db.rawQuery(sql, null);

        }else {
            String tempTime = time.substring(0,2);
            String sql = "SELECT * FROM " + SESSION_TABLE_NAME + " WHERE " + SESSION_CREATOR_COL + " != " + userId + " AND " + SESSION_ID_COL + " NOT IN (SELECT " +PARTICIPANT_SESSIONID_COL + " FROM "+ PARTICIPANT_TABLE_NAME+" WHERE "+PARTICIPANTID_COL+" = " + userId + ") ";
            if (muscle != 0) {
                sql += "AND " + SESSION_MUSCLES_COL + " = " + muscle + " " ;
            }
            sql += "AND " + SESSION_LOCATION_COL + " LIKE \"" + location + "\" AND " + SESSION_DATE_COL + " LIKE \"" + date + "\" AND " + SESSION_TIME_COL + " LIKE \"" + tempTime + "%\" ORDER BY SUBSTR("+SESSION_DATE_COL+",8,4) ASC, (CASE SUBSTR("+SESSION_DATE_COL+",1,3) " +
                    "WHEN 'JAN' THEN 1 " +
                    "WHEN 'FEB' THEN 2 " +
                    "WHEN 'MAR' THEN 3 " +
                    "WHEN 'APR' THEN 4 " +
                    "WHEN 'MAY' THEN 5 " +
                    "WHEN 'JUN' THEN 6 " +
                    "WHEN 'JUL' THEN 7 " +
                    "WHEN 'AUG' THEN 8 " +
                    "WHEN 'SEP' THEN 9 " +
                    "WHEN 'OCT' THEN 10 " +
                    "WHEN 'NOV' THEN 11 " +
                    "WHEN 'DEC' THEN 12 " +
                    "END) ASC, SUBSTR("+SESSION_DATE_COL+",5,2) ASC, SUBSTR("+SESSION_TIME_COL+",1,2) ASC, SUBSTR("+SESSION_TIME_COL+",4,2) ASC";
            cursorSessions = db.rawQuery(sql, null);

        }
        ArrayList<Sessions> sessionModelArrayList = new ArrayList<>();
        if(cursorSessions.moveToFirst()){
            do{
                ArrayList<Integer> participants = getParticipants(cursorSessions.getInt(0));
                sessionModelArrayList.add(new Sessions(
                        cursorSessions.getInt(0),
                        cursorSessions.getInt(1),
                        cursorSessions.getInt(2),
                        cursorSessions.getString(3),
                        cursorSessions.getString(4),
                        cursorSessions.getString(5),
                        cursorSessions.getString(6),
                        cursorSessions.getString(7),
                        participants
                ));
            }while (cursorSessions.moveToNext());
        }
        cursorSessions.close();
        return sessionModelArrayList;
    }


    /*-----------------------------------------------------------
                        FRIENDS TABLE METHODS
     -----------------------------------------------------------*/

    public ArrayList<Integer> getFriends(int user){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorFriends = db.rawQuery("SELECT * FROM " + FRIEND_TABLE_NAME + " WHERE " + FRIEND_USERID_COL + " = \"" + user+ "\"", null);

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

        userValues.put(FRIEND_USERID_COL, userId);
        userValues.put(FRIENDID_COL, friendId);

        db.insert(FRIEND_TABLE_NAME, null, userValues);

        ContentValues friendValues = new ContentValues();

        friendValues.put(FRIEND_USERID_COL, friendId);
        friendValues.put(FRIENDID_COL, userId);

        db.insert(FRIEND_TABLE_NAME, null, friendValues);

        db.close();
    }

    /*-----------------------------------------------------------
                        PARTICIPANT TABLE METHODS
     -----------------------------------------------------------*/

    // method adds friend to friend database
    public void addNewParticipant(int sessionId, int participantId) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PARTICIPANT_SESSIONID_COL, sessionId);
        values.put(PARTICIPANTID_COL, participantId);

        db.insert(PARTICIPANT_TABLE_NAME, null, values);


        db.close();
    }

    public ArrayList<Integer> getParticipants(int session){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorParticipant = db.rawQuery("SELECT * FROM " + PARTICIPANT_TABLE_NAME + " WHERE " + PARTICIPANT_SESSIONID_COL + " = " + session, null);

        ArrayList<Integer> participantArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorParticipant.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                participantArrayList.add(cursorParticipant.getInt(0));
            } while (cursorParticipant.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorParticipant.close();
        return participantArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FRIEND_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PARTICIPANT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SESSION_TABLE_NAME);
        onCreate(db);
    }
}
