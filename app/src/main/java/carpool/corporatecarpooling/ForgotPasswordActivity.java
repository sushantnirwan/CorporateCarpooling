package carpool.corporatecarpooling;
import carpool.corporatecarpooling.controllers.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    String email="";
    boolean flag=false;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Button btnOtp=(Button) findViewById(R.id.getOTPButton);
        btnOtp.setOnClickListener(this);
        TextView txtView = (TextView) findViewById(R.id.link_backtologin);
        txtView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent linkIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(linkIntent);
            }
        });
       /* FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://myfirstfirebase-ad7b8.firebaseio.com/");
*/
    }

    private void sendEmail() {
        //Getting content for email
        EditText et = (EditText)findViewById(R.id.editText_emailId);
        email=et.getText().toString().trim();
        String mailRecipient = email;
        String subject = "PIN";
        String message = "123";

        flag =  emailValidation(mailRecipient);
        if(flag) {
            SendMailTask sm=new SendMailTask(ForgotPasswordActivity.this,mailRecipient, subject, message);
            sm.execute(); //Executing sendmailtask to send email

        }
        else
        {
            Toast.makeText(ForgotPasswordActivity.this,"Enter valid Email ID ",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }

    public boolean emailValidation(String mailTo)
    {
        if(mailTo.contains("in.ibm.com")){
            return true;
        }
        else
        {
            return false;
        }
    }



}
