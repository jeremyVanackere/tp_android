package com.example.cineenigma.data;

import android.provider.BaseColumns;

public class ColTableNote {
    public static final class ColTableNoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NOTE_TITRE_FILM = "titreFilm";
        public static final String COLUMN_NOTE_SENARIO = "noteSenario";
        public static final String COLUMN_NOTE_REALISATION = "noteRealisation";
        public static final String COLUMN_NOTE_MUSQIUE = "noteMusique";
        public static final String COLUMN_COMMENTAIRE = "commentaire";
    }
}
