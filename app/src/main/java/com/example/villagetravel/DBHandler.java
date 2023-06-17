package com.example.villagetravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.COLUMN_1 + " TEXT," +
                    UserProfile.Users.COLUMN_2 + " TEXT," +
                    UserProfile.Users.COLUMN_3 + " TEXT," +
                    UserProfile.Users.COLUMN_4 + " TEXT," +
                    UserProfile.Users.COLUMN_5 + " TEXT," +
                    UserProfile.Users.COLUMN_6 + " TEXT," +
                    UserProfile.Users.COLUMN_7 + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;

    public long addInfo (String userName, String userFName, String userLName, String userEmail, String phone, String password, String city){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_1, userName);
        values.put(UserProfile.Users.COLUMN_2, userFName);
        values.put(UserProfile.Users.COLUMN_3, userLName);
        values.put(UserProfile.Users.COLUMN_4, userEmail);
        values.put(UserProfile.Users.COLUMN_5, phone);
        values.put(UserProfile.Users.COLUMN_6, password);
        values.put(UserProfile.Users.COLUMN_7, city);

        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);

        return newRowId;

    }

    public Boolean updateInfo (String username, String userFName, String userLName, String userEmail, String phone, String password, String city){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_2, userFName);
        values.put(UserProfile.Users.COLUMN_3, userLName);
        values.put(UserProfile.Users.COLUMN_4, userEmail);
        values.put(UserProfile.Users.COLUMN_5, phone);
        values.put(UserProfile.Users.COLUMN_6, password);
        values.put(UserProfile.Users.COLUMN_7, city);

        String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        int count = db.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >=1 ) {
            return true;
        }
        else {
            return false;
        }

    }

    public void deleteInfo (String username){

        SQLiteDatabase db = getWritableDatabase();

        String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };
        int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);


    }

    public List readAllInfo (String username){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_2,
                UserProfile.Users.COLUMN_3,
                UserProfile.Users.COLUMN_4,
                UserProfile.Users.COLUMN_5,
                UserProfile.Users.COLUMN_6,
                UserProfile.Users.COLUMN_7
        };

        String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            String fname = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_2));
            String lname = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_3));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_4));
            String pno = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_5));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_6));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_7));
            userInfo.add(user);
            userInfo.add(fname);
            userInfo.add(lname);
            userInfo.add(email);
            userInfo.add(pno);
            userInfo.add(pass);
            userInfo.add(city);


        }
        cursor.close();
        return userInfo;
    }

    public Boolean LoginUser(String username,String password){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_6
        };
        String selection = UserProfile.Users.COLUMN_1 + " = ? AND "+UserProfile.Users.COLUMN_6 +"= ?";
        String[] selectionArgs = { username,password};

        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        String validUser = null;
        while(cursor.moveToNext()){
            validUser = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));

        }
        cursor.close();
        if(validUser==null){
            return false;
        }
        else{
            return true;
        }
    }


}

