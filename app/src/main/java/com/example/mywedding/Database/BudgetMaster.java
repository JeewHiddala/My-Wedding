package com.example.mywedding.Database;

import android.provider.BaseColumns;

public class BudgetMaster {
    public BudgetMaster(){
    }

    public static class Budget implements BaseColumns{
        public static final String TABLE_NAME = "budget";
        public static final String COLUMN_NAME_BUDGETNAME = "budgetName";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_NOTES = "notes";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_PAID = "paidAmount";
        public static final String COLUMN_NAME_DATE = "paidDate";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
