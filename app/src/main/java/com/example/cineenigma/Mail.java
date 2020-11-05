package com.example.cineenigma;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import dto.MailBodyDTO;

public class Mail {

    /**
     * permet d'envoyer un mail
     * @param mailBodyDTO dto qui contient les infos
     * @param context le context de l'application
     */
    public static void sendMail(final MailBodyDTO mailBodyDTO, final Context context) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailBodyDTO.getMailTo()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mailBodyDTO.getSubject());
        emailIntent.putExtra(Intent.EXTRA_TEXT, mailBodyDTO.getBodyText());

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
