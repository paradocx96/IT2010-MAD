package com.sliit.android_movie_management_app;

import android.provider.BaseColumns;

public final class DatabaseMaster {

    private DatabaseMaster() {}

    public static final class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USERNAME = "Username";
        public static final String COLUMN_PASSWORD = "Password";
        public static final String COLUMN_USER_TYPE = "UserType";
    }

    public static final class Movie implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_MOVIE_NAME = "MovieName";
        public static final String COLUMN_MOVIE_YEAR = "MovieYear";
    }

    public static final class Comments implements BaseColumns {
        public static final String TABLE_NAME = "comments";
        public static final String COLUMN_MOVIE_NAME = "MovieName";
        public static final String COLUMN_MOVIE_RATING = "MovieRating";
        public static final String COLUMN_MOVIE_COMMENTS = "MovieComments";
    }
}
