package com.example.mywedding.Database;

import android.provider.BaseColumns;

public class UserMaster {
    public UserMaster(){
    }

    public static class User implements BaseColumns{
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "userName";
        public static final String COLUMN_NAME_USEREMAIL = "userMail";
        public static final String COLUMN_NAME_USERCONTACT = "userContact";
        public static final String COLUMN_NAME_USERSTATUS = "userStatus";
        public static final String COLUMN_NAME_PARTNERNAME = "parterName";
        public static final String COLUMN_NAME_PARTNEREMAIL = "partnerMail";
        public static final String COLUMN_NAME_PARTNERCONTACT = "partnerContact";
        public static final String COLUMN_NAME_PARTNERSTATUS = "partnerStatus";
        public static final String COLUMN_NAME_WEDDINGNAME = "wedName";
        public static final String COLUMN_NAME_WEDDINGDATE = "wedDate";
    }
}
