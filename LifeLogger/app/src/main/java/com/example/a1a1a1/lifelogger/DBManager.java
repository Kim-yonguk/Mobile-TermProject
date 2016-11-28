package com.example.a1a1a1.lifelogger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
            // create table 테이블명 (컬럼명 타입 옵션);
            db.execSQL("CREATE TABLE FOOD_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, lat REAL, lon REAL, dbyear INTEGER, dbmonth INTEGER, dbday INTEGER, name TEXT, price TEXT);");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from FOOD_LIST", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : 위도 = "
                    + cursor.getDouble(1)
                    + ", 경도 = "
                    + cursor.getInt(2)
                    + ", 년도 = "
                    + cursor.getInt(3)
                    + ", 월 = "
                    + cursor.getInt(4)
                    + ", 일 = "
                    + cursor.getInt(5)
                    + ", 제목 = "
                    + cursor.getString(6)
                    + ", 한 일 = "
                    + cursor.getString(7)
                    + "\n";
        }

        return str;
    }
}