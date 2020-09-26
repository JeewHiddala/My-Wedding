package com.example.mywedding.Database;

import android.provider.BaseColumns;

public class WeddingMaster {
    public WeddingMaster() {}

    //implementation of guest table
    public static class Guests implements BaseColumns{

        public static final String TABLE_NAME = "guests";
        public static final String COLUMN_NAME_GNAME = "guestname";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_NOTES = "notes";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";

    }

}
