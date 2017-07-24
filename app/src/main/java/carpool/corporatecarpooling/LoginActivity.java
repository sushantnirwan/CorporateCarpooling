package carpool.corporatecarpooling;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.telephony.TelephonyManager;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import android.Manifest.permission.*;
import android.widget.TextView;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    public int REQUEST_CODE_READ_PHONE_STATE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = (Button) findViewById(R.id.loginButton);
        EditText mobileNum=(EditText) findViewById(R.id.mobileNumber);
        EditText password=(EditText) findViewById(R.id.password);
        TextView txtView = (TextView) findViewById(R.id.link_backtoregister);
        txtView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent linkIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(linkIntent);
            }
        });

        TextView txtForgotPassword = (TextView) findViewById(R.id.link_forgotpassword);
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent linkIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(linkIntent);
            }
        });
        ActivityCompat.requestPermissions(LoginActivity.this,
                new String[]{android.Manifest.permission.READ_PHONE_STATE},
                REQUEST_CODE_READ_PHONE_STATE);
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String yourNumber = mTelephonyMgr.getLine1Number();
        mobileNum.setText(yourNumber);

    }

    public void openForgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

}
