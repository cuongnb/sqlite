package com.example.cuongnb.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by cuongnb on 8/9/16.
 */
public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "vocab.db";
    public static final int DATA_VERSION = 1;

    public static final String TABLE = "tbl_vocab";
    public static final String KEY_ID = "id";
    public static final String KEY_TERM = "term";
    public static final String KEY_DEF = "def";

    public static final String CREATE_TABLE_VOCAB = "CREATE TABLE " + TABLE + "( " +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            KEY_TERM + " TEXT, " +
            KEY_DEF + " TEXT )";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_VOCAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insetVocab(Term term) {
        //1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TERM, term.term);
        values.put(KEY_DEF, term.def);

        //3. insert
        db.insert(TABLE, //table
                null,   //nullcolumnHack
                values);// key/values -> keys = column names/ values = column values

        //4. close
        db.close();
    }

    public ArrayList<Term> getListTerm() {
        ArrayList<Term> terms = new ArrayList<Term>();

        // 1. build the query
//        String query = "SELECT * FROM " + TABLE + " ORDER BY " + KEY_ID + " DESC";
        String query = "SELECT * FROM " + TABLE;

        //2. get reference to write DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build word and add it to list
        Term term = null;
        if (cursor.moveToFirst()) {
            do {
                term = new Term();
                term.id = cursor.getInt(0);
                term.term = cursor.getString(1);
                term.def = cursor.getString(2);

                terms.add(term);

            } while (cursor.moveToNext());
        }
        // 5. close database
        db.close();
        //4. return words

        return terms;
    }
}
