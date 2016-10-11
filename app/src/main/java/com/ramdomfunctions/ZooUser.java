package com.ramdomfunctions;

import android.provider.BaseColumns;

/**
 * Created by xavier on 14/04/2016.
 */
public class ZooUser implements BaseColumns {

    public static final String TABLE_NAME = "zooUsers";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_AUTHORITY = "authority";
}
