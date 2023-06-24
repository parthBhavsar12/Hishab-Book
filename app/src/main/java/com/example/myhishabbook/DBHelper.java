package com.example.myhishabbook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(MainActivity context) {
        super(context, "hishabData.db", null, 1);
    }
    public DBHelper(MainActivity2 context) {
        super(context, "hishabData.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table HishabDetails(name TEXT, descr TEXT, amount REAL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists HishabDetails");
    }
    public Boolean insertData(String name, String descr, String amount)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("descr", descr);
        contentValues.put("amount", amount);
        long result=DB.insert("HishabDetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean deleteData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from HishabDetails",null);
        if (cursor.getCount() > 0) {
            long result = DB.delete("HishabDetails",null,null);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from HishabDetails", null);
        return cursor;
    }
    public double getTotal()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select sum(amount) from HishabDetails", null);
        double total=0;
        if (cursor.getCount() > 0){
            while(cursor.moveToNext())
                total = cursor.getDouble(0);
        }
        else
            total = 0;
        return total;
    }
}