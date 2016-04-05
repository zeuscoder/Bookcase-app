package com.zeus.bookcase.app.home.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zeus_coder on 2016/3/31.
 */
public class BookCaseDBHelper extends SQLiteOpenHelper {

    //数据库常量
    private static final int DB_VERSION = 6;
    private static final String DB_NAME = "Bookcase";
    private static final String TABLE_BOOK = "Book";
    private static final String TABLE_ADDRESS = "Address";
    private static final String TABLE_CART = "Cart";
    private static final String TABLE_FAVORITE = "Favorite";

    private static final String CREATE_BOOK = "create table if not exists " + TABLE_BOOK + " (" +
            "id text, " +
            "uid text, " +
            "isbn text, " +
            "author text, " +
            "title text, " +
            "price text, " +
            "image text, " +
            "summary text, " +
            "publisher text, " +
            "pubdate text, " +
            "primary key(id,uid))";

    private static final String CREATE_ADDRESS = "create table if not exists " + TABLE_ADDRESS + " (" +
            "id text primary key, " +
            "uid text, " +
            "name text, " +
            "address text, " +
            "phone text)";

    private static final String CREATE_CART = "create table if not exists " + TABLE_CART + " (" +
            "bid text, " +
            "uid text, " +
            "title text, " +
            "image text, " +
            "publisher text, " +
            "number text, " +
            "price text, " +
            "primary key(bid,uid))";

    private static final String CREATE_FAVORITE = "create table if not exists " + TABLE_FAVORITE + " (" +
            "bid text, " +
            "uid text, " +
            "title text, " +
            "author text, " +
            "image text, " +
            "publisher text, " +
            "summary text, " +
            "price text, " +
            "primary key(bid,uid))";

    private static final String DROP_BOOK = "DROP TABLE IF EXISTS " + TABLE_BOOK;;
    private static final String DROP_ADDRESS = "DROP TABLE IF EXISTS " + TABLE_ADDRESS;
    private static final String DROP_CART = "DROP TABLE IF EXISTS " + TABLE_CART;
    private static final String DROP_FAVORITE = "DROP TABLE IF EXISTS " + TABLE_FAVORITE;

    public BookCaseDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_ADDRESS);
        sqLiteDatabase.execSQL(CREATE_CART);
        sqLiteDatabase.execSQL(CREATE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_BOOK);
        sqLiteDatabase.execSQL(DROP_ADDRESS);
        sqLiteDatabase.execSQL(DROP_CART);
        sqLiteDatabase.execSQL(DROP_FAVORITE);
        this.onCreate(sqLiteDatabase);
    }

}
