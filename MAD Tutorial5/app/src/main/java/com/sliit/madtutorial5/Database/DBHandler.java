package com.sliit.madtutorial5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserInfo.db";
    private static final String TAG = "DBHandler";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TABLE =
                "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + " (" +
                        UsersMaster.Users.COLUMN_ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.Users.COLUMN_USERNAME + " TEXT," +
                        UsersMaster.Users.COLUMN_PASSWORD + " TEXT)";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo (String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UsersMaster.Users.COLUMN_USERNAME, username);
        values.put(UsersMaster.Users.COLUMN_PASSWORD, password);

        long result = db.insert(UsersMaster.Users.TABLE_NAME, null, values);

        return result;
    }

    public List readAllInfo() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_USERNAME,
                UsersMaster.Users.COLUMN_PASSWORD
        };

        String sortOrder = UsersMaster.Users.COLUMN_USERNAME + " DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List USERNAME = new ArrayList<>();
        List PASSWORD = new ArrayList<>();

        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_PASSWORD));
            USERNAME.add(username);
            PASSWORD.add(password);
        }

        cursor.close();
        return USERNAME;
    }

    public List readUserInfo(String request) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_USERNAME,
                UsersMaster.Users.COLUMN_PASSWORD
        };

        String sortOrder = UsersMaster.Users.COLUMN_USERNAME + " DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List USERNAME = new ArrayList<>();
        List PASSWORD = new ArrayList<>();

        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_PASSWORD));
            USERNAME.add(username);
            PASSWORD.add(password);
        }

        cursor.close();
        Log.i(TAG,"readUserInfo : " + USERNAME);

        if (request == "User" || request == "user") {
            return USERNAME;
        }
        else if (request == "Password" || request == "password") {
            return PASSWORD;
        }
        else {
            return null;
        }
    }

    public int deleteInfo (String username) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.Users.COLUMN_USERNAME + " LIKE ?";
        String[] selectionArgs = {username};

        int result = db.delete(UsersMaster.Users.TABLE_NAME, selection, selectionArgs);

        return result;
    }

    public int updateInfo (String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(UsersMaster.Users.COLUMN_PASSWORD, password);

        String selection = UsersMaster.Users.COLUMN_USERNAME + " LIKE ?";
        String[] selectionArgs = {username};

        int result = db.update(UsersMaster.Users.TABLE_NAME, values, selection, selectionArgs);

        return result;
    }
}
