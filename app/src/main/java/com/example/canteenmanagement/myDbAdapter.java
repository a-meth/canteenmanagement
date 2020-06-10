package com.example.canteenmanagement;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AlertDialog;

public class myDbAdapter extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "Credentials.db";
        public static final String TABLE_NAME_STUDENT = "Student";
        public static final String  COL_1 = "USERNAME";
        public static final String COL_2 = "PASSWORD";
        public static final String COL_3 = "CATEGORY";
        public final String s = "STUDENT";
        public final String c = "CANTEEN";
        //public static final String TABLE_NAME_CANTEEN = "Canteen";

        activity_admindash a;

        public myDbAdapter(Context context) {
                super(context, DATABASE_NAME, null, 1);
        }
        //myDbAdapter helper;



        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table " +  TABLE_NAME_STUDENT + " (USERNAME TEXT PRIMARY KEY,PASSWORD TEXT,CATEGORY TEXT)");
                //db.execSQL("create table " +  TABLE_NAME_CANTEEN + " (USERNAME TEXT PRIMARY KEY,PASSWORD TEXT)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STUDENT);
                //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CANTEEN);
                onCreate(db);
        }

        public boolean insertData(String uname,String upass, boolean category) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COL_1,uname);
                contentValues.put(COL_2,upass);
                /*if(category)
                contentValues.put(COL_3,s);*/
                if(category){
                        //i = 0;
                        contentValues.put(COL_3,s);
                        long result = db.insert(TABLE_NAME_STUDENT, null, contentValues);
                        if(result == -1) {
                            //Toast.makeText(a.getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
                                showmessage("Database","Error");
                                return false;
                        }
                        else
                                return true;

                }
                else {  contentValues.put(COL_3,c);
                        long result = db.insert(TABLE_NAME_STUDENT, null, contentValues);
                        if(result == -1) {
                                showmessage("Database","Error");
                                return false;
                        }
                        else
                                return true;
                }


        }

        public Cursor getStudentData(){
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor res = db.rawQuery("Select * from "+ TABLE_NAME_STUDENT,null);
                return res;
        }

        /*public Cursor getCanteenData(){
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor res = db.rawQuery("Select * from "+ TABLE_NAME_CANTEEN,null);
                return res;
        }*/
       public void showmessage(String title,String message){
                AlertDialog.Builder builder = new AlertDialog.Builder(a.getApplicationContext());
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.show();
        }
}