package carpool.corporatecarpooling.controllers;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import android.app.ProgressDialog;
import android.widget.Toast;

import carpool.corporatecarpooling.ForgotPasswordActivity;
import carpool.corporatecarpooling.LoginActivity;

/**
 * Created by ibm_admin on 21/07/2017.
 */
public class SendMailTask extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progressDialog;
    private String email;
    private String subject;
    private String message;
    private Session session;
    private Context context;
    private static final String username = "corporatecarpoolapp@gmail.com";
    private static final String password = "carpool2017";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"Please wait", "Sending mail", true, false);

    }
    public SendMailTask(Context context, String email, String subject, String message){
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context,"Message Sent",Toast.LENGTH_LONG).show();

    }

    protected Void doInBackground(Void... messages) {
        Properties props = new Properties();
        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(username));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);
            //Adding message
            mm.setText(message);

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }

}