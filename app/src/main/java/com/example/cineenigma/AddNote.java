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

import dto.MailBodyDTO;

public class AddNote extends AppCompatActivity {

    private EditText mTitreEditText, mNoteSenarioEditText, mNoteRealisationEditText, mNoteMusiqueEditText, mCommentaireEditText, mMailEditText;
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
        this.mMailEditText = (EditText) findViewById(R.id.mail_edit_text);

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
        final ValueInput valueInput = getValue();

        ContentValues cv = new ContentValues();
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_TITRE_FILM, valueInput.titre);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_SENARIO, valueInput.note_senario);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_REALISATION, valueInput.note_realisation);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_MUSQIUE, valueInput.note_musique);
        cv.put(ColTableNote.ColTableNoteEntry.COLUMN_COMMENTAIRE, valueInput.commentaire);

        return mDb.insert(ColTableNote.ColTableNoteEntry.TABLE_NAME, null, cv);
    }

    public ValueInput getValue() {
        final ValueInput valueInput = new ValueInput();
        valueInput.titre = mTitreEditText.getText().toString();
        valueInput.note_senario = Integer.parseInt(mNoteSenarioEditText.getText().toString());
        valueInput.note_realisation = Integer.parseInt(mNoteRealisationEditText.getText().toString());
        valueInput.note_musique = Integer.parseInt(mNoteMusiqueEditText.getText().toString());
        valueInput.commentaire = mCommentaireEditText.getText().toString();

        return valueInput;
    }

    public void sendMAil(View view) {
        if(checkNoValidInput()) {
            Toast.makeText(getApplicationContext(), "Il faut remplir tout les champs !", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mMailEditText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "veuillez saisir une addresse mail de destination !", Toast.LENGTH_SHORT).show();
            return;
        }

        final ValueInput valueInput = getValue();

        final MailBodyDTO mailBodyDTO = new MailBodyDTO();
        final StringBuilder bodyText = new StringBuilder();

        // build le corps du mail
        bodyText.append("Titre : ").append(valueInput.titre).append("\n ");
        bodyText.append("Note sénario : ").append(valueInput.note_senario).append(" / 9").append("\n ");
        bodyText.append("Note réalisation : ").append(valueInput.note_realisation).append(" / 9").append("\n ");
        bodyText.append("Note musique : ").append(valueInput.note_musique).append(" / 9").append("\n ");
        bodyText.append("Commentaire : ").append(valueInput.commentaire).append("\n ");

        mailBodyDTO.setBodyText(bodyText.toString());
        mailBodyDTO.setSubject("Note ciné enigma");
        mailBodyDTO.setMailTo(mMailEditText.getText().toString());

        Mail.sendMail(mailBodyDTO, this);
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

    private class ValueInput {
        String titre;
        int note_senario;
        int note_realisation;
        int note_musique;
        String commentaire;
    }
}