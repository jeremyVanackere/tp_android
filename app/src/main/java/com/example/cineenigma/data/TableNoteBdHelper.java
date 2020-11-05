package com.example.cineenigma.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TableNoteBdHelper extends SQLiteOpenHelper {


    // The database name
    private static final String DATABASE_NAME = "note.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public TableNoteBdHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE ").append(ColTableNote.ColTableNoteEntry.TABLE_NAME).append(" (");
        sb.append(ColTableNote.ColTableNoteEntry._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_TITRE_FILM).append(" TEXT NOT NULL, ");
        sb.append(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_SENARIO).append(" INTEGER NOT NULL, ");
        sb.append(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_REALISATION).append(" INTEGER NOT NULL, ");
        sb.append(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_MUSQIUE).append(" INTEGER NOT NULL,");
        sb.append(ColTableNote.ColTableNoteEntry.COLUMN_COMMENTAIRE).append(" TEXT NOT NULL" + ")");

        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ColTableNote.ColTableNoteEntry.TABLE_NAME);
        onCreate(db);
    }
}
