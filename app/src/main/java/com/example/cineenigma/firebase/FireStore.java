package com.example.cineenigma.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cineenigma.DataFireStore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.NoteDto;

/**
 * singleton connexion fireStore
 */
public class FireStore {

    private FirebaseFirestore db;
    private final static String projectId = "cineenig-fbffb";

    /** Constructeur privé */
    private FireStore()
    {

    }

    /** Instance unique pré-initialisée */
    private static FireStore INSTANCE = new FireStore();

    /** Point d'accès pour l'instance unique du singleton */
    public static FireStore getInstance()
    {
        return INSTANCE;
    }

    /**
     * initialise la connexion avec firestore
     */
   public void initConnxeion() {
       db = FirebaseFirestore.getInstance();
    }

    /**
     * akopute une note sur le cloud
     * @param noteDto
     */
    public void addNote(final NoteDto noteDto) {
        Map<String, Object> note = new HashMap<>();
        note.put("titre", noteDto.titre);
        note.put("note_senario", noteDto.note_senario);
        note.put("note_realisation", noteDto.note_realisation);
        note.put("note_musique", noteDto.note_musique);
        note.put("commentaire", noteDto.commentaire);

        // Add a new document with a generated ID
        db.collection("notes")
                .add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void getNotes(final DataFireStore dataFireStore) {
        db.collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            final List<NoteDto> noteDtoList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                final NoteDto noteDto = document.toObject(NoteDto.class);
                                noteDtoList.add(noteDto);
                            }
                            dataFireStore.callbackData(noteDtoList);
                        } else {
                            Toast.makeText(dataFireStore, "Echec recupération des données", Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public void setDb(FirebaseFirestore db) {
        this.db = db;
    }
}
