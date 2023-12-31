package com.example.villagetravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class PDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PaymentDB.db";

    public PDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PProfile.Payments.TABLE_NAME + " (" +
                    PProfile.Payments._ID + " INTEGER PRIMARY KEY," +
                    PProfile.Payments.COLUMN_1 + " TEXT," +
                    PProfile.Payments.COLUMN_2 + " TEXT," +
                    PProfile.Payments.COLUMN_3 + " TEXT," +
                    PProfile.Payments.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PProfile.Payments.TABLE_NAME;

    public long addInfo (String holdername, String cardnum, String exdate, String cvvnum){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PProfile.Payments.COLUMN_1, holdername);
        values.put(PProfile.Payments.COLUMN_2,  cardnum);
        values.put(PProfile.Payments.COLUMN_3, exdate);
        values.put(PProfile.Payments.COLUMN_4, cvvnum);

        long newRowId = db.insert(PProfile.Payments.TABLE_NAME, null, values);

        return newRowId;
    }

    public Boolean updateInfo (String holdername, String cardnum, String exdate, String cvvnum){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PProfile.Payments.COLUMN_2, cardnum);
        values.put(PProfile.Payments.COLUMN_3, exdate);
        values.put(PProfile.Payments.COLUMN_4, cvvnum);

        String selection = PProfile.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };

        int count = db.update(
                PProfile.Payments.TABLE_NAME,
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

    public void deleteInfo (String holdername){

        SQLiteDatabase db = getWritableDatabase();

        String selection = PProfile.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };
        int deletedRows = db.delete(PProfile.Payments.TABLE_NAME, selection, selectionArgs);


    }

    public List readAllInfo (String holdername){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                PProfile.Payments.COLUMN_1,
                PProfile.Payments.COLUMN_2,
                PProfile.Payments.COLUMN_3,
                PProfile.Payments.COLUMN_4
        };

        String selection = PProfile.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };

        String sortOrder =
                PProfile.Payments.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                PProfile.Payments.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List PaymenetDet = new ArrayList<>();
        while(cursor.moveToNext()) {
            String hname = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_1));
            String cardno = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_2));
            String edate = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_3));
            String cvvno = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_4));
            PaymenetDet.add(hname);
            PaymenetDet.add(cardno);
            PaymenetDet.add(edate);
            PaymenetDet.add(cvvno);
        }
        cursor.close();
        return PaymenetDet;
    }



}
