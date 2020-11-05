package dto;

/**
 * dto a la rache pas le temps
 */
public class NoteDto {
    public String titre;
    public int note_senario;
    public int note_realisation;
    public int note_musique;
    public String commentaire;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNote_senario() {
        return note_senario;
    }

    public void setNote_senario(int note_senario) {
        this.note_senario = note_senario;
    }

    public int getNote_realisation() {
        return note_realisation;
    }

    public void setNote_realisation(int note_realisation) {
        this.note_realisation = note_realisation;
    }

    public int getNote_musique() {
        return note_musique;
    }

    public void setNote_musique(int note_musique) {
        this.note_musique = note_musique;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
