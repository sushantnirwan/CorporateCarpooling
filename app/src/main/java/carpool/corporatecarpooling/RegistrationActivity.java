package carpool.corporatecarpooling;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import java.util.Properties;
import android.telephony.TelephonyManager;

import carpool.corporatecarpooling.controllers.SendMailTask;

public class RegistrationActivity extends AppCompatActivity {


    private static final String subject = "CarPool Verification Pin Code";
    private static final String username = "corporatecarpoolapp@gmail.com";
    private static final String password = "carpool2017";
    private static  String message = "";
    private final int REQUEST_CODE_READ_PHONE_STATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button btnsendOTP = (Button) findViewById(R.id.button_sendOTP);
        btnsendOTP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                emailValidation();
            }
        });

        TextView txtView = (TextView) findViewById(R.id.link_login);
        txtView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent linkIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(linkIntent);
            }
        });
        RadioGroup rGroup = (RadioGroup)findViewById(R.id.radioSex);
        RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group,int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
            }
        });
        EditText mobileNum=(EditText) findViewById(R.id.editText_Mobile);
        ActivityCompat.requestPermissions(RegistrationActivity.this,
                new String[]{android.Manifest.permission.READ_PHONE_STATE},
                REQUEST_CODE_READ_PHONE_STATE);
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String yourNumber = mTelephonyMgr.getLine1Number();
        mobileNum.setText(yourNumber);
        mobileNum.setText(yourNumber);
        if (yourNumber != null && !yourNumber.equals("")) {
            mobileNum.setText(yourNumber);
        }
        else
        {
            mobileNum.setText(yourNumber);
            mobileNum.setEnabled(true);
        }

    }

    public void emailValidation() {
        EditText et = (EditText) findViewById(R.id.editText_email);
        String email = et.getText().toString();
        if (email.contains("@in.ibm.com")) {
            int randomPIN = (int) (Math.random() * 9000) + 1000;
            message = "Pin code for verification of CarPoolApplication is:" + randomPIN;
            //  sendMail(email, subject, message);
            SendMailTask mailTask = new SendMailTask(RegistrationActivity.this,email,subject,message);
            mailTask.execute();
        } else
        {
            Toast.makeText(RegistrationActivity.this, "Please enter valid IBM Email Address", Toast.LENGTH_SHORT).show();
        }


    }
}