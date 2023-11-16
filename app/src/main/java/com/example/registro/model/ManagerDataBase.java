package com.example.registro.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManagerDataBase extends SQLiteOpenHelper {


    private static final String DATA_BASE = "dbUsers";
    private static final int VERSION = 1;
    public static final String TABLE_USERS = "users";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_USERS+"(use_document INTEGER PRIMARY KEY," +
            "use_user VARCHAR(50) NOT NULL, use_name VARCHAR(100), use_lastName VARCHAR(100) NOT NULL," +
            "use_pass VARCHAR(25) NOT NULL, use_status VARCHAR(1));";

    private static final String DELETE_TABLE ="DROP TABLE IF EXISTS "+TABLE_USERS;

    public ManagerDataBase(Context context) {
        super(context, DATA_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
}
