package com.example.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String NOTE_TABLE = "NOTE_TABLE";
    public static final String COLUMN_NOTE_NAME = "NOTE_NAME";
    public static final String COLUMN_NOTE_CONTENT = "NOTE_CONTENT";
    public static final String COLUMN_ID_INTEGER = "ID INTEGER";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "notes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTableStatment = "CREATE TABLE " + NOTE_TABLE + " (" + COLUMN_ID_INTEGER + " PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_NAME + " TEXT, " + COLUMN_NOTE_CONTENT + " TEXT)";
        db.execSQL(creatTableStatment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(NotesModel notesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOTE_NAME, notesModel.getNoteName());
        cv.put(COLUMN_NOTE_CONTENT, notesModel.getNoteContent());

        long insert = db.insert(NOTE_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public List<NotesModel> getEveryone(){
    List<NotesModel> returnList = new ArrayList<>();
    String queryString = "SELECT * FROM " + NOTE_TABLE ;

    SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int noteId = cursor.getInt(0);
                String noteName = cursor.getString(1);
                String noteContent = cursor.getString(2);

                NotesModel newNote = new NotesModel(noteId, noteName, noteContent);

                returnList.add(newNote);
            }while(cursor.moveToNext());
        }
        else{
        //add nothing
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + NOTE_TABLE ;
        Cursor data = db.rawQuery(queryString, null);

        return data;
    }


}
