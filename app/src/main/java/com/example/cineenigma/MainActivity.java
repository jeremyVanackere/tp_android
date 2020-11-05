package com.example.cineenigma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cineenigma.data.ColTableNote;
import com.example.cineenigma.data.TableNoteBdHelper;

public class MainActivity extends AppCompatActivity {

    private NoteListAdapter mAdapter;
    private SQLiteDatabase mDb;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView listNoteRecyclerView;

        listNoteRecyclerView = (RecyclerView) this.findViewById(R.id.all_notes_list_view);

        listNoteRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        final TableNoteBdHelper dbHelper = new TableNoteBdHelper(this);

        mDb = dbHelper.getWritableDatabase();

        final Cursor cursor = this.getAllNotes();

        mAdapter = new NoteListAdapter(this, cursor);

        listNoteRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TableNoteBdHelper dbHelper = new TableNoteBdHelper(this);
        mDb = dbHelper.getWritableDatabase();

        final Cursor cursor = this.getAllNotes();

        mAdapter.swapCursor(cursor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDb.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
    }

    public void goToAddNote(View view) {
        mDb.close();
        final Intent intent = new Intent(this, AddNote.class);
        startActivity(intent);
    }


    public Cursor getAllNotes() {
        return mDb.query(
                ColTableNote.ColTableNoteEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ColTableNote.ColTableNoteEntry._ID
        );
    }

    public SQLiteDatabase getmDb() {
        return mDb;
    }
}