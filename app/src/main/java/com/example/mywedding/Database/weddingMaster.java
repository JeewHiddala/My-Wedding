package com.example.mywedding.Database;

import android.provider.BaseColumns;

public class weddingMaster {
    public weddingMaster(){}

    //column names by using static variables
    public static class Vendors implements BaseColumns{
        public static final String TABLE_NAME = "vendor";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_VNAME = "vendorname";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_CONTACTNO = "contactno";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_STATUS= "status";
        public static final String COLUMN_NAME_AMOUNT = "amount";


    }
}
