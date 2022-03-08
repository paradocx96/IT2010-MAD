package com.sliit.madtutorial5.Database;

import android.provider.BaseColumns;

public final class UsersMaster {

    private UsersMaster() {}

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }
}