package com.example.lab03_2;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentsDB";
    private static final String TABLE_NAME = "students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MSSV = "mssv";
    private static final String KEY_BIRTH = "birthYear";
    private static final String KEY_MAJOR = "major";
    private static final String KEY_CLASS = "className";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA encoding = 'UTF-8';");
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_MSSV + " TEXT,"
                + KEY_BIRTH + " TEXT,"
                + KEY_MAJOR + " TEXT,"
                + KEY_CLASS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(KEY_NAME, s.getName());
        v.put(KEY_MSSV, s.getMssv());
        v.put(KEY_BIRTH, s.getBirthYear());
        v.put(KEY_MAJOR, s.getMajor());
        v.put(KEY_CLASS, s.getClassName());
        db.insert(TABLE_NAME, null, v);
        db.close();
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                list.add(new Student(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5)
                ));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public void updateStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(KEY_NAME, s.getName());
        v.put(KEY_MSSV, s.getMssv());
        v.put(KEY_BIRTH, s.getBirthYear());
        v.put(KEY_MAJOR, s.getMajor());
        v.put(KEY_CLASS, s.getClassName());
        db.update(TABLE_NAME, v, KEY_ID + "=?", new String[]{String.valueOf(s.getId())});
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
    }
}

