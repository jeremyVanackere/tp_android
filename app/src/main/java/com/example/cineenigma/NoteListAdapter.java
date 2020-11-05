package com.example.cineenigma;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineenigma.data.ColTableNote;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.GuestViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public NoteListAdapter(Context context, final Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position))
            return;

        final String titre = mCursor.getString(mCursor.getColumnIndex(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_TITRE_FILM));
        final int note_senario = mCursor.getInt(mCursor.getColumnIndex(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_SENARIO));
        final int note_realisation = mCursor.getInt(mCursor.getColumnIndex(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_REALISATION));
        final int note_musique = mCursor.getInt(mCursor.getColumnIndex(ColTableNote.ColTableNoteEntry.COLUMN_NOTE_MUSQIUE));
        final String commentaire = mCursor.getString(mCursor.getColumnIndex(ColTableNote.ColTableNoteEntry.COLUMN_COMMENTAIRE));

        final long id = mCursor.getInt(mCursor.getColumnIndex(ColTableNote.ColTableNoteEntry._ID));

        holder.mTitre.setText(titre);
        holder.mNoteSenario.setText(note_senario + " / 9");
        holder.mNoteRealisation.setText(note_realisation + " / 9");
        holder.mNoteMusique.setText(note_musique + " / 9");
        holder.mCommentaire.setText(commentaire);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(final Cursor newCursor) {
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder {

        TextView mNoteSenario, mNoteRealisation, mNoteMusique, mCommentaire, mTitre;

        public GuestViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitre = (TextView) itemView.findViewById(R.id.titre_film);
            mNoteSenario = (TextView) itemView.findViewById(R.id.note_senario);
            mNoteRealisation = (TextView) itemView.findViewById(R.id.note_realisation);
            mNoteMusique = (TextView) itemView.findViewById(R.id.note_musique);
            mCommentaire = (TextView) itemView.findViewById(R.id.note_commentaire);
        }
    }
}
