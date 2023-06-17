package com.example.villagetravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReservationDatabase extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String Database_NAME = "ReservationDB";

    public ReservationDatabase(@Nullable Context context) {
        super(context, Database_NAME, null, VERSION);
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
            "CREATE TABLE " + ResProfile.Res.TABLE_NAME + " (" +
                    ResProfile.Res._ID + " INTEGER PRIMARY KEY," +
                    ResProfile.Res.COL_1 + " TEXT," +
                    ResProfile.Res.COL_2 + " TEXT," +
                    ResProfile.Res.COL_3 + " TEXT," +
                    ResProfile.Res.COL_4 + " TEXT," +
                    ResProfile.Res.COL_5 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ResProfile.Res.TABLE_NAME;


    public long addInfo (String phonenumber, String pickupdate, String returndate, String modle,String submodle){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ResProfile.Res.COL_1, phonenumber);
        values.put(ResProfile.Res.COL_2, pickupdate);
        values.put(ResProfile.Res.COL_3, returndate);
        values.put(ResProfile.Res.COL_4, modle);
        values.put(ResProfile.Res.COL_5, submodle);

        long newRowId = db.insert(ResProfile.Res.TABLE_NAME, null, values);

        return newRowId;

    }

    public Boolean updateInfo (String phonenumber, String pickupdate, String returndate, String modle,String submodle){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ResProfile.Res.COL_2, pickupdate);
        values.put(ResProfile.Res.COL_3, returndate);
        values.put(ResProfile.Res.COL_4, modle);
        values.put(ResProfile.Res.COL_5, submodle);

        String selection = ResProfile.Res.COL_1 + " LIKE ?";
        String[] selectionArgs = { phonenumber};

        int count = db.update(
                ResProfile.Res.TABLE_NAME,
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

    public void deleteInfo (String phonenumber){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = ResProfile.Res.COL_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { phonenumber };
        // Issue SQL statement.
        int deletedRows = db.delete(ResProfile.Res.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllInfo (String phonenumber){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                ResProfile.Res.COL_1,
                ResProfile.Res.COL_2,
                ResProfile.Res.COL_3,
                ResProfile.Res.COL_4,
                ResProfile.Res.COL_5


        };

        String selection = ResProfile.Res.COL_1 + " LIKE ?";
        String[] selectionArgs = { phonenumber };

        String sortOrder =
                ResProfile.Res.COL_1 + " ASC";

        Cursor cursor = db.query(
                ResProfile.Res.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List Reservationinfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String pno = cursor.getString(cursor.getColumnIndexOrThrow(ResProfile.Res.COL_1));
            String pdate = cursor.getString(cursor.getColumnIndexOrThrow(ResProfile.Res.COL_2));
            String rdate = cursor.getString(cursor.getColumnIndexOrThrow(ResProfile.Res.COL_3));
            String model= cursor.getString(cursor.getColumnIndexOrThrow(ResProfile.Res.COL_4));
            String submodel = cursor.getString(cursor.getColumnIndexOrThrow(ResProfile.Res.COL_5));
            Reservationinfo.add(pno);//0
            Reservationinfo.add(pdate);//1
            Reservationinfo.add(rdate);//2
            Reservationinfo.add(model);//3
            Reservationinfo.add(submodel);//4
        }
        cursor.close();
        return Reservationinfo;
    }

}
