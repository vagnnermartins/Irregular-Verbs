package com.vagnnermartins.irregularverbs.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by renan on 2/13/15.
 */
public class Database extends SQLiteAssetHelper {

    public static final String DATABASE_SPEC = "verbs.spec";
    public static final String DATABASE_NAME = "verbs.db";
    public static final int DATABASE_VERSION = 1;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}