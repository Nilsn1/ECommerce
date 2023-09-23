package com.nilscreation.ecommerce.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nilscreation.ecommerce.model.CartModel;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Table1";

    public static final String COLUMN_ORDER_ID = "orderId";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_ITEM_TOTAL_PRICE = "itemTotalPrice";
    public static final String COLUMN_FINAL_PRICE = "finalPrice";
    public static final String COLUMN_IMAGE = "image";

    Context context;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ORDER_ID + " TEXT PRIMARY KEY, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_PRICE + " REAL, " +
                        COLUMN_QTY + " INTEGER, " +
                        COLUMN_ITEM_TOTAL_PRICE + " REAL, " +
                        COLUMN_FINAL_PRICE + " REAL, " +
                        COLUMN_IMAGE + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(String orderid, String title, Float price, int qty, Float itemTotalPrice, Float finalPrice, String image) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ID, orderid);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_QTY, qty);
        values.put(COLUMN_ITEM_TOTAL_PRICE, itemTotalPrice);
        values.put(COLUMN_FINAL_PRICE, finalPrice);
        values.put(COLUMN_IMAGE, image);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<CartModel> readData() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<CartModel> cartlist = new ArrayList<>();

        while (cursor.moveToNext()) {

            cartlist.add(new CartModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getInt(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getString(6)));
        }
        return cartlist;
    }

    public void deleteData(String title) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_TITLE + " = ? ", new String[]{title});
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void deleteandAdd(String orderid, String title, Float price, int qty, Float itemTotalPrice, Float finalPrice, String image) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TITLE + " = ? ", new String[]{title});
        addData(orderid, title, price, qty, itemTotalPrice, finalPrice, image);
    }
}
