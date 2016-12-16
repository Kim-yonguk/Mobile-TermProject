package com.example.a1a1a1.lifelogger;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DBManager extends SQLiteOpenHelper {




    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
            // create table 테이블명 (컬럼명 타입 옵션);
            db.execSQL("CREATE TABLE FOOD_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, lat REAL, lon REAL, dbyear INTEGER, dbmonth INTEGER, dbday INTEGER, name TEXT, price TEXT, count1 INTEGER, count2 INTEGER, count3 INTEGER);");
            db.execSQL("CREATE TABLE COUNT_LIST(count1 INTEGER, count2 INTEGER , count3 INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }


    public void countinsert(String _query) {
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
    public void mapinsert(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from FOOD_LIST",null);
        while(cursor.moveToNext()) {
            MainActivity.arr_latitude.add(cursor.getDouble(1));
            MainActivity.arr_longtitude.add(cursor.getDouble(2));
        }
        db.close();
    }

    public void timeinsert(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from FOOD_LIST",null);
        while(cursor.moveToNext()) {
            MainActivity.arr_month.add(cursor.getDouble(3));
            MainActivity.arr_day.add(cursor.getDouble(4));
        }
        db.close();
    }
    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from FOOD_LIST", null);

        while(cursor.moveToNext()) {
            str +=
                    "제목 = "
                    + cursor.getString(6)
                    + ", 한 일 = "
                    + cursor.getString(7)
                    + "2, 년도 = "
                    + cursor.getInt(3)
                    + ", 월 = "
                    + cursor.getInt(4)
                    + ", 일 = "
                    + cursor.getInt(5)
                    + "\n"
                    + "위도 = "
                    + cursor.getDouble(1)
                    + ", 경도 = "
                    + cursor.getDouble(2)


                    + "\n\n";
        }

        return str;
    }
    public String PrintCount() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from COUNT_LIST", null);

        while(cursor.moveToNext()) {
            str +=
                    "1번 목표 = "
                            + cursor.getInt(0)
                            + " "
                            + "             2번 목표 = "
                            + cursor.getInt(1)
                            + " "
                            + "             3번 목표 = "
                            + cursor.getInt(2)
                            + "\n";
        }

        return str;
    }
}