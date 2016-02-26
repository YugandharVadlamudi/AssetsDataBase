package com.example.kiran.assetsdatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Kiran on 15-12-2015.
 */
public class DatabaseHelperClass extends SQLiteOpenHelper {
    private String db_path;
    private static String dbName = "yugandhar_one";
    static int version=1;
    Context context;
    public DatabaseHelperClass(Context context) {
        super(context, dbName,null, version);
        this.context=context;
        db_path = "//data//data//" + context.getPackageName() + "//databases//";
    }

    public boolean checkExists() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = db_path + dbName;
            /*checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);*/
//            checkDB=context.openOrCreateDatabase(dbName,Context.MODE_PRIVATE,null);
            File file=new File(myPath);
            if(file.exists())
                return true;
            else return false;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
            // database does't exist yet.

        } catch (Exception ep) {
            ep.printStackTrace();
        }

        if(checkDB!=null)
        {
            checkDB.close();
        }
       return (checkDB!=null?true:false);
    }
    public void importIfNotExist()
    {
        if(checkExists())
        {
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from yugandhar_one.yuga_one",null);
            while (cursor.moveToNext())
            {
                Log.d("data",cursor.getString(cursor.getColumnIndex("yu_one")));
            }
//                copyData();


        }
        else {
            this.getReadableDatabase();
            /*try {*/
                copyData();
          /*  }
            catch (IOException e1)
            {
                throw  new Error("error in copying");

            }*/
        }
    }
public void copyData() {
    InputStream inputStream;
    OutputStream outputStream;
    try {
        inputStream=context.getAssets().open(db_path+dbName);
         outputStream=new FileOutputStream(db_path+dbName);
    } catch (IOException e) {
        e.printStackTrace();
    }

//    if(outputStream==null)
//    {
//        Toast.makeText(context,"outputstremnull",Toast.LENGTH_SHORT).show();
//    }
//    else
//    {
//        Toast.makeText(context,"outputstrem not null",Toast.LENGTH_SHORT).show();
//    }
}
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
