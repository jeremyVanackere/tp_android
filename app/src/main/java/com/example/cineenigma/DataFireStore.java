package com.example.cineenigma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.example.cineenigma.firebase.FireStore;

import java.util.List;

import dto.NoteDto;

public class DataFireStore extends AppCompatActivity {

    private TextView mDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_fire_store);

        mDataText = (TextView) findViewById(R.id.data_fireStore);

        FireStore.getInstance().getNotes(this);
    }

    public void callbackData(List<NoteDto> noteDtoList) {
        for(NoteDto noteDto : noteDtoList) {
            final StringBuilder sb = new StringBuilder();
            sb.append(noteDto.titre).append("\n");
            sb.append(noteDto.note_senario).append("\n");
            sb.append(noteDto.note_realisation).append("\n");
            sb.append(noteDto.note_musique).append("\n");
            sb.append(noteDto.commentaire).append("\n");
            sb.append("-------------------------------------------------").append("\n");
            mDataText.append(sb.toString());
        }
    }
}