package com.sliit.android_movie_management_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "Database.db";

    List USERNAME = new ArrayList<>();
    List PASSWORD = new ArrayList<>();
    List USER_TYPE = new ArrayList<>();
    List MOVIE_NAME = new ArrayList<>();
    List MOVIE_YEAR = new ArrayList<>();
    List MOVIE_COMMENTS = new ArrayList<>();
    List MOVIE_RATING = new ArrayList<>();

    public List getUSERNAME() {
        return USERNAME;
    }

    public List getPASSWORD() {
        return PASSWORD;
    }

    public List getUSER_TYPE() {
        return USER_TYPE;
    }

    public List getMOVIE_NAME() {
        return MOVIE_NAME;
    }

    public List getMOVIE_YEAR() {
        return MOVIE_YEAR;
    }

    public List getMOVIE_COMMENTS() {
        return MOVIE_COMMENTS;
    }

    public List getMOVIE_RATING() {
        return MOVIE_RATING;
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERS =
                "CREATE TABLE " + DatabaseMaster.Users.TABLE_NAME + " (" +
                        DatabaseMaster.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.Users.COLUMN_USERNAME + " TEXT," +
                        DatabaseMaster.Users.COLUMN_PASSWORD + " TEXT," +
                        DatabaseMaster.Users.COLUMN_USER_TYPE + " TEXT)";
        db.execSQL(CREATE_TABLE_USERS);

        String CREATE_TABLE_MOVIE =
                "CREATE TABLE " + DatabaseMaster.Movie.TABLE_NAME + " (" +
                        DatabaseMaster.Movie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.Movie.COLUMN_MOVIE_NAME + " TEXT," +
                        DatabaseMaster.Movie.COLUMN_MOVIE_YEAR + " INTEGER)";
        db.execSQL(CREATE_TABLE_MOVIE);

        String CREATE_TABLE_COMMENTS =
                "CREATE TABLE " + DatabaseMaster.Comments.TABLE_NAME + " (" +
                        DatabaseMaster.Comments._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.Comments.COLUMN_MOVIE_NAME + " TEXT," +
                        DatabaseMaster.Comments.COLUMN_MOVIE_RATING + " INTEGER," +
                        DatabaseMaster.Comments.COLUMN_MOVIE_COMMENTS + " TEXT)";
        db.execSQL(CREATE_TABLE_COMMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String SQL_DELETE_ENTRIES_USERS = "DROP TABLE IF EXISTS " + DatabaseMaster.Users.TABLE_NAME;
        String SQL_DELETE_ENTRIES_MOVIE = "DROP TABLE IF EXISTS " + DatabaseMaster.Movie.TABLE_NAME;
        String SQL_DELETE_ENTRIES_COMMENTS = "DROP TABLE IF EXISTS " + DatabaseMaster.Comments.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES_USERS);
        db.execSQL(SQL_DELETE_ENTRIES_MOVIE);
        db.execSQL(SQL_DELETE_ENTRIES_COMMENTS);
        onCreate(db);
    }

    public String type;
    public long registerUser(String username, String password) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();


        if (username.equals("Admin") || username.equals("admin")) {
            type = "admin";
        }
        else {
            type = "customer";
        }

        // Put parameter values to baseColumns
        values.put(DatabaseMaster.Users.COLUMN_USERNAME, username);
        values.put(DatabaseMaster.Users.COLUMN_PASSWORD, password);
        values.put(DatabaseMaster.Users.COLUMN_USER_TYPE, type);

        // Insert the new row, returning the primary key value of the new row
        long result = db.insert(DatabaseMaster.Users.TABLE_NAME, null, values);

        // Return result of inserting data to database
        return result;
    }


    public void loginUser() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DatabaseMaster.Users._ID,
                DatabaseMaster.Users.COLUMN_USERNAME,
                DatabaseMaster.Users.COLUMN_PASSWORD,
                DatabaseMaster.Users.COLUMN_USER_TYPE
        };
        String sortOrder = DatabaseMaster.Users._ID + " DESC";
        Cursor cursor = db.query(
                DatabaseMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            String un = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_USERNAME));
            String pw = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_PASSWORD));
            String tp = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_USER_TYPE));

            USERNAME.add(un);
            PASSWORD.add(pw);
            USER_TYPE.add(tp);
        }
        cursor.close();
    }


    public boolean addMovie (String name, int year) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        // Put parameter values to baseColumns
        values.put(DatabaseMaster.Movie.COLUMN_MOVIE_NAME, name);
        values.put(DatabaseMaster.Movie.COLUMN_MOVIE_YEAR, year);

        // Insert the new row, returning the primary key value of the new row
        long result = db.insert(DatabaseMaster.Movie.TABLE_NAME, null, values);

        // Return result of inserting data to database
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }


    public void viewMovies() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DatabaseMaster.Movie._ID,
                DatabaseMaster.Movie.COLUMN_MOVIE_NAME,
                DatabaseMaster.Movie.COLUMN_MOVIE_YEAR
        };
        String sortOrder = DatabaseMaster.Movie._ID + " DESC";
        Cursor cursor = db.query(
                DatabaseMaster.Movie.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder,
                null
        );

        while (cursor.moveToNext()) {
            String movieName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Movie.COLUMN_MOVIE_NAME));
            String movieYear = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Movie.COLUMN_MOVIE_YEAR));
            MOVIE_NAME.add(movieName);
            MOVIE_YEAR.add(movieYear);
        }
        cursor.close();
    }


    public long insertComments(String name, int rating, String comment) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        // Put parameter values to baseColumns
        values.put(DatabaseMaster.Comments.COLUMN_MOVIE_NAME, name);
        values.put(DatabaseMaster.Comments.COLUMN_MOVIE_RATING, rating);
        values.put(DatabaseMaster.Comments.COLUMN_MOVIE_COMMENTS, comment);

        // Insert the new row, returning the primary key value of the new row
        long result = db.insert(DatabaseMaster.Comments.TABLE_NAME, null, values);

        // Return result of inserting data to database
        return result;
    }

    public void viewComments() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DatabaseMaster.Comments._ID,
                DatabaseMaster.Comments.COLUMN_MOVIE_NAME,
                DatabaseMaster.Comments.COLUMN_MOVIE_RATING,
                DatabaseMaster.Comments.COLUMN_MOVIE_COMMENTS
        };
        String sortOrder = DatabaseMaster.Comments._ID + " DESC";
        Cursor cursor = db.query(
                DatabaseMaster.Comments.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            String movieName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_MOVIE_NAME));
            int movieRating = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_MOVIE_RATING)));
            String movieComment = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_MOVIE_COMMENTS));
            MOVIE_NAME.add(movieName);
            MOVIE_RATING.add(movieRating);
            MOVIE_COMMENTS.add(movieComment);
        }
        cursor.close();
    }
}
