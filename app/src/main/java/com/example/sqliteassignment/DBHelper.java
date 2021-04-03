package com.example.sqliteassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname="DBStudent";

    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table student(sno text primary key,sname text,sage text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists student");
        onCreate(db);
    }

    public boolean Insert(String sno,String sname,String sage)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("sno",sno);
        cv.put("sname",sname);
        cv.put("sage",sage);

        long r=db.insert("student","null",cv);

        if(r==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public Boolean getInfo(String ssno)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=db.rawQuery("select sno from student where sno=?",new String[]{ssno});
        if(cr.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getAll()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("select * from student",null);
        return cr;
    }

    public boolean delete(String ssno)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=db.rawQuery("select sno from student where sno=?",new String[]{ssno});
        if(cr.getCount()>0)
        {
            long r=db.delete("student","sno=?",new String[]{ssno});
            if(r==-1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean update(String ssno,String sname,String sage)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("sno",ssno);
        cv.put("sname",sname);
        cv.put("sage",sage);
        Cursor cr=db.rawQuery("select * from student where sno=?",new String[]{ssno});



        if(cr.getCount()>0)
        {
            long r=db.update("student",cv,"sno=?",new String[]{ssno});
            if(r==-1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return true;
        }

    }
}
