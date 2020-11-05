package com.example.cineenigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cineenigma.data.ColTableNote;
import com.example.cineenigma.data.TableNoteBdHelper;

public class AddNote extends AppCompatActivity {

    private EditText mTitreEditText, mNoteSenarioEditText, mNoteRealisationEditText, mNoteMusiqueEditText, mCommentaireEditText;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        this.mTitreEditText = (EditText) findViewById(R.id.titre_film_edit_text);
        this.mNoteSenarioEditText = (EditText) findViewById(R.id.note_senario_edit_text);
        this.mNoteRealisationEditText = (EditText) findViewById(R.id.note_realisation_edit_text);
        this.mNoteMusiqueEditText = (EditText) findViewById(R.id.note_musique_edit_text);
        this.mCommentaireEditText = (EditText) findViewById(R.id.commentaire_edit_text);

        final TableNoteBdHelper dbHelper = new TableNoteBdHelper(this);

        mDb = dbHelper.getWritableDatabase();

    }

    public void addNote(View view) {
        if(checkNoValidInput()) {
            Toast.makeText(getApplicationContext(), "Il faut remplir tout les champs !", Toast.LENGTH_SHORT).show();
            return;
        }

        saveNote();
        mDb.close();
        Toast.makeText(getApplicationContext(), "note enregisttré avec succés", Toast.LENGTH_SHORT).show();
        finish();
    }

    public long saveNote() {
        final String titre = mTitreEditText.getText().toString();
        final int note_senario = Integer.parseInt(mNoteSenarioEditText.getText().toString());
        final int note_realisation = Integer.parseInt(mNoteRealisationEditText.getText().toString());
        final int note_musique = Integer.parseInt(mNoteMusiqueEditText.getText().toString());
        final String commentaire = mCommentaireEditText.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_TITRE_FILM, titre);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_SENARIO, note_senario);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_REALISATION, note_realisation);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_MUSQIUE, note_musique);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_COMMENTAIRE, commentaire);

        return mDb.insert(ColTableNote.ColTableNoteEntry.TABLE_NAME, null, cv);
    }

    /**
     * test si au moins un input n'est pas valide
     * @return
     */
    public boolean checkNoValidInput() {
        return mTitreEditText.getText().toString().isEmpty() ||
                mNoteSenarioEditText.getText().toString().isEmpty() ||
                mNoteRealisationEditText.getText().toString().isEmpty() ||
                mNoteMusiqueEditText.getText().toString().isEmpty() ||
                mCommentaireEditText.getText().toString().isEmpty();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
        finish(); // etre sur que ca kill l'activité
    }
}