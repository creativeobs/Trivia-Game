package com.example.triviagame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper{

    SQLiteDatabase db;
    Cursor cursor;
    final float SCORE = 0;
    final String BIO = "Add some notes here.";
    Context context;

    public Database(Context context) {
        super(context,"Userdata.db", null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_Members (member_ID INTEGER PRIMARY KEY AUTOINCREMENT, member_Username TEXT, member_Password TEXT, member_Name TEXT, member_Score FLOAT, member_Avatar INTEGER, member_Bio TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_Members");
        onCreate(db);
    }

    public boolean add_record(String member_Username, String member_Password, String member_Name, int member_Avatar){
        db = getWritableDatabase();
        try{
            db.execSQL("INSERT INTO tbl_Members (member_Username, member_Password, member_Name, member_Score, member_Avatar, member_Bio) VALUES ('" + member_Username + "', '" + member_Password + "', '" + member_Name + "', '" + SCORE + "', '" +  member_Avatar + "', '"+ BIO +"')");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void set_Notes(String id, String note){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            db.execSQL("UPDATE tbl_Members SET member_Bio ='" + note + "' WHERE member_ID = " + id);
        }
    }

    public String get_Notes(String id){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id});
        if(cursor.moveToFirst()){
            return cursor.getString(6).toString();
        }
        return "";
    }

    public boolean search_Name(String username){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_Username = ?", new String[] {username});
        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public String search_Username_ID(String username){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_Username = ?", new String[] {username});
        if(cursor.moveToFirst()){
            return cursor.getString(0).toString();
        }
        return "";
    }

    public String search_Id_name(String id){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            return cursor.getString(3).toString();
        }
        return "";
    }

    public void add_Score(String id, String score){
        float score2 = search_Score(id); // current
        float score1 = Float.parseFloat(score);
        float totalScore = score2 + score1;
        score = String.valueOf(totalScore);
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            db.execSQL("UPDATE tbl_Members SET member_Score ='" +  score + "' WHERE member_ID = " + id);
        }
    }

    public void updatePassword(String id, String password){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            db.execSQL("UPDATE tbl_Members SET member_Password ='" +  password + "' WHERE member_ID = " + id);
        }
    }

    public String search_Username(String id){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            return cursor.getString(1).toString();
        }
        return "";
    }

    public String search_Password(String id){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            return cursor.getString(2).toString();
        }
        return "";
    }

    public Cursor getAllData(){
        db = getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members", null);
        if(cursor.getCount() == 0)
            return null;
        return cursor;
    }

    public String search_Image(String id){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            return cursor.getString(5).toString();
        }
        return "";
    }

    public int login(String username, String password){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_Username = ? AND member_Password = ?", new String[] {username,password});
        if (cursor.moveToFirst()) {
            return Integer.parseInt(cursor.getString(0));
        }
        return 0;
    }

    public float search_Score(String id){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_Members WHERE member_ID = ?", new String[] {id} );
        if(cursor.moveToFirst()){
            return Float.parseFloat(cursor.getString(4));
        }
        return 0;
    }
}
